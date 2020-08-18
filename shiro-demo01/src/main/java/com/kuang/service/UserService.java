package com.kuang.service;

import com.kuang.pojo.User;

/**
 * @description
 * @author diaoxiuze
 * @date 2020/8/7 18:53
 */
public interface UserService {

    /**
     * 通过名字查询用户
     * @param name
     * @return
     */
    User queryUserByName(String name);
}
