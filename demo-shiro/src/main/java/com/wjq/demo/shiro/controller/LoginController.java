package com.wjq.demo.shiro.controller;

import com.wjq.demo.shiro.annotation.RequestMappingAuth;
import com.wjq.demo.shiro.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @author wjq
 * @since 2021-10-09
 */
@RestController
@RequestMapping("/test")
public class LoginController {


    @GetMapping("/error")
    public String error() {
        throw new RuntimeException();
    }


    @GetMapping("/error2")
    public String error2() throws Exception {
        throw new Exception();
    }


    @RequestMapping(value = "/alias/{id}", method = RequestMethod.GET)
    @GetMapping("/hello/{id}")
    public String sayHello(@PathVariable String id) {
        Object principal = SecurityUtils.getSubject().getPrincipal();
        System.out.println("===============" + principal);
        return "say hello";
    }


    @GetMapping("/world/{id}")
    @RequiresRoles("admin")
    public String world(@PathVariable String id) {
        return "say hello";
    }


    @GetMapping("logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();

        return "logout";
    }

    @GetMapping("/login")
    public String login(User user) {
        if (StringUtils.isEmpty(user.getUserName()) || StringUtils.isEmpty(user.getPassword())) {
            return "请输入用户名和密码！";
        }
        //用户认证信息
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken =
                new UsernamePasswordToken(user.getUserName(), user.getPassword());
        try {
            //进行验证，这里可以捕获异常，然后返回对应信息
            subject.login(usernamePasswordToken);
        } catch (UnknownAccountException e) {
            return "用户名不存在！";
        } catch (AuthenticationException e) {
            return "账号或密码错误！";
        } catch (AuthorizationException e) {
            return "没有权限";
        }
        return "login success";
    }

}
