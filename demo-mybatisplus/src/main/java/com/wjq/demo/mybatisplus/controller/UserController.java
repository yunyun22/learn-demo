package com.wjq.demo.mybatisplus.controller;

import com.wjq.demo.mybatisplus.entity.User;
import com.wjq.demo.mybatisplus.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController()
@RequestMapping("/users")
public class UserController {


    @Resource
    private UserService userService;

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @PostMapping
    public void add(@RequestBody User user) {
        userService.add(user);
    }


    @GetMapping("/batch/{age}")
    public void batch(@PathVariable(name = "age") int age) {
        userService.batch(age);
    }

}
