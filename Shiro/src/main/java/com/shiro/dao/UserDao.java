package com.shiro.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shiro.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * Description: TODO
 * CreateTime: 2022/8/8 10:09
 *
 * @author: dxz
 */
@Mapper
public interface UserDao extends BaseMapper<User> {
}
