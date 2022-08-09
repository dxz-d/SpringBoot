package com.shiro.service;

import com.shiro.entity.User;
import com.shiro.result.ResResult;

/**
 * Description: TODO
 * CreateTime: 2022/8/8 11:15
 *
 * @author: dxz
 */
public interface LoginService {

    /**
     * 用户登陆
     * @param user
     * @return
     */
    ResResult doLogin(User user);
}
