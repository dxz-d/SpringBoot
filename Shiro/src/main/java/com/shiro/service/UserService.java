package com.shiro.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shiro.entity.User;
import com.shiro.result.ResResult;

/**
 * Description: TODO
 * CreateTime: 2022/8/8 10:10
 *
 * @author: dxz
 */
public interface UserService extends IService<User> {

    /**
     * 获取当前用户信息
     * @return
     */
    ResResult getUserInfo();
}
