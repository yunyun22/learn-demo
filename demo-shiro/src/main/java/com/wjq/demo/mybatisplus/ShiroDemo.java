package com.wjq.demo.mybatisplus;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;

/**
 * @author wjq
 * @since 2021-10-09
 */
public class ShiroDemo {
    public static void main(String[] args) {
        //1.创建SecurityManagerFactory
        IniSecurityManagerFactory factory =
                new IniSecurityManagerFactory("classpath:shiro.ini");
        //2.获取SecurityManager,绑定到SecurityUtils中
        SecurityManager instance = factory.getInstance();
        SecurityUtils.setSecurityManager(instance);
        //3.获取一个用户识别信息
        Subject currentUser = SecurityUtils.getSubject();
        //4.判断是否已经身份验证
        if (!currentUser.isAuthenticated()) {
            // 4.1把用户名和密码封装为 UsernamePasswordToken 对象
            UsernamePasswordToken token = new UsernamePasswordToken("guest", "guest");
            // 4.2设置rememberme
            token.setRememberMe(true);
            try {
                // 4.3登录.
                currentUser.login(token);
            }
            catch (UnknownAccountException uae) { //用户不存在异常
                return;
            }
            catch (IncorrectCredentialsException ice) {// 密码不匹配异常
                return;
            }
            catch (LockedAccountException lae) {// 用户被锁定
            }
            catch (AuthenticationException ae) { // 其他异常，认证异常的父类
            }
        }

        // 5.权限测试：
        //5.1判断用户是否有某个角色
        if (currentUser.hasRole("guest")) {
            System.out.println("****---->用户拥有角色guest!");
        } else {
            System.out.println("****---->用户没有拥有角色guest");
            return;
        }
        //5.2判断用户是否执行某个操作的权限
        if (currentUser.isPermitted("see")) {
            System.out.println("****----> 用户拥有执行此功能的权限");
        } else {
            System.out.println("****---->用户没有拥有执行此功能的权限");
        }

        //6.退出
        System.out.println("****---->" + currentUser.isAuthenticated());
        currentUser.logout();
        System.out.println("****---->" + currentUser.isAuthenticated());

    }
}
