package com.wjq.demo.springsecurity;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;


@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .passwordEncoder(new BCryptPasswordEncoder())
                .withUser("user")
                .password(new BCryptPasswordEncoder().encode("password"))
                .roles("USER");
    }


    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.formLogin() // 表单登录。跳转到security默认的登录表单页
                // http.httpBasic() //basic登录
                .and()
                // 对请求授权
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                //允许所有人访问/anonymous/**
                .antMatchers("/anonymous/**").permitAll()
                // 任何请求
                .anyRequest()
                // 需要身份认证
                .authenticated();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.httpFirewall(httpFirewall());
    }

    private HttpFirewall httpFirewall(){
        StrictHttpFirewall strictHttpFirewall=new StrictHttpFirewall();
//        Set<String> allowedHttpMethods = new HashSet<>();
//        allowedHttpMethods.add(HttpMethod.POST.name());
//        strictHttpFirewall.setAllowedHttpMethods(allowedHttpMethods);
        strictHttpFirewall.setAllowedHostnames((s)-> "localhost".equals(s));
        return strictHttpFirewall;
    }
}
