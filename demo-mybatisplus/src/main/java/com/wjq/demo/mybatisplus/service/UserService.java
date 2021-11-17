package com.wjq.demo.mybatisplus.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.wjq.demo.mybatisplus.entity.User;
import com.wjq.demo.mybatisplus.mapper.UserMapper;
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

    @DS("a")
    public void  add(User user){
        userMapper.insert(user);
    }

}
