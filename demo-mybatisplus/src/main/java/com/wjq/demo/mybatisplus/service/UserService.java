package com.wjq.demo.mybatisplus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wjq.demo.mybatisplus.entity.User;

import java.util.List;

/**
 * @author wjq
 * @since 2021-12-01
 */
public interface UserService extends IService<User> {
    void  add(User user);

    List<User> getUsers();

    void batch(int i);
}
