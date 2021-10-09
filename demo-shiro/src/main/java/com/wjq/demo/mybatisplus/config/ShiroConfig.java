package com.wjq.demo.mybatisplus.config;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;

/**
 * @author wjq
 * @since 2021-10-09
 */
@Configuration
public class ShiroConfig {

    @Bean
    public Realm realm() {
        MyRealm myRealm = new MyRealm();
        myRealm.setCredentialsMatcher(new SimpleCredentialsMatcher());
        return myRealm;
    }

    private static class MyRealm extends AuthorizingRealm {
        @Override
        protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
            System.out.println("MyRealm认证中---->用户：" + token.getPrincipal());
            // 可以从token中获取用户名来从数据库中查询数据
            UsernamePasswordToken upToken = (UsernamePasswordToken) token;
            String password = "123456";// 假设这是从数据库中查询到的用户密码
            // 创建一个SimpleAuthenticationInfo，第一个参数是用户名，第二个是验证密码，第三个是当前realm的className
            // 验证密码会与用户提交的密码进行比对
            return new SimpleAuthenticationInfo(upToken.getUsername(), password, this.getName());
        }

        @Override
        protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
            if (principals == null) {
                throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
            }
            //null usernames are invalid
            Object primaryPrincipal = principals.getPrimaryPrincipal();
            System.out.println("principals=================" + primaryPrincipal);

            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            HashSet<String> roles = new HashSet<>();
            roles.add("admin");
            info.setRoles(roles);
            HashSet<String> permissions = new HashSet<>();
            permissions.add("document:read");
            info.setStringPermissions(permissions);
            return info;
        }
    }


    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, String> map = new HashMap<>();
        //登出
        map.put("/admin/**", "authc, roles[admin]");
        map.put("/docs/**", "authc, perms[document:read]");
        //对所有用户认证
        map.put("/**", "authc");
        //登录
        shiroFilterFactoryBean.setLoginUrl("/login");
        //首页
        shiroFilterFactoryBean.setSuccessUrl("/index");
        //错误页面，认证不通过跳转
        shiroFilterFactoryBean.setUnauthorizedUrl("/error");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }


    @Bean
    public static DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        /**
         * setUsePrefix(false)用于解决一个奇怪的bug。在引入spring aop的情况下。
         * 在@Controller注解的类的方法中加入@RequiresRole等shiro注解，会导致该方法无法映射请求，导致返回404。
         * 加入这项配置能解决这个bug
         */
        defaultAdvisorAutoProxyCreator.setUsePrefix(true);
        return defaultAdvisorAutoProxyCreator;
    }
}
