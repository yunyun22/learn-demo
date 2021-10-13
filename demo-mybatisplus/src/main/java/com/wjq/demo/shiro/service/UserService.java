package com.wjq.demo.shiro.service;

import com.wjq.demo.shiro.entity.User;
import com.wjq.demo.shiro.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;


    public List<User> getUsers() {
        return userMapper.selectList(null);
    }

    public void  add(User user){
        userMapper.insert(user);
    }

}
