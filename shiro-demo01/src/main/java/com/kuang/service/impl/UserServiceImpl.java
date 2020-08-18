package com.kuang.service.impl;

import com.kuang.mapper.UserMapper;
import com.kuang.pojo.User;
import com.kuang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description
 * @author diaoxiuze
 * @date 2020/8/7 18:55
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User queryUserByName(String name) {
        return userMapper.queryUserByName(name);
    }
}
