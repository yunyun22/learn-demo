package com.wjq.demo.mybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wjq.demo.mybatisplus.entity.User;
import com.wjq.demo.mybatisplus.mapper.UserMapper;
import com.wjq.demo.mybatisplus.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;


    @Override
    public List<User> getUsers() {
        return userMapper.selectList(null);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batch(int i) {
        List<User> users = getUsers();
        users.forEach(user -> user.setAge(i));
        updateBatchById(users);
        users.get(0).setAge(1);
        updateById(users.get(0));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchUpdate(List<Integer> list,Integer age) {
         return userMapper.batchUpdate(list,age);
    }

    @Override
    public void add(User user) {
        userMapper.insert(user);
    }


}
