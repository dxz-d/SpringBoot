package com.shiro.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shiro.dao.UserDao;
import com.shiro.entity.User;
import com.shiro.result.ResResult;
import com.shiro.service.MenuService;
import com.shiro.service.UserService;
import com.shiro.until.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Description: TODO
 * CreateTime: 2022/8/8 10:11
 *
 * @author: dxz
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

    @Autowired
    private MenuService menuService;

    @Override
    public ResResult getUserInfo() {
        Map<String, Object> rs = CollUtil.newHashMap();
        String username = UserUtil.getUsername();
        User user = this.getOne(new QueryWrapper<User>().eq("username", username));
        if (user == null) {
            return ResResult.failure("获取用户信息失败！");
        }
        List list = this.menuService.findMenuByUser(user.getId());
        rs.put("name", username);
        rs.put("menus", list);

        return ResResult.success(rs);
    }
}
