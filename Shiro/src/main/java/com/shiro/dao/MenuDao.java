package com.shiro.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shiro.entity.Menu;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author FANG
 * @since 2021-02-07
 */
public interface MenuDao extends BaseMapper<Menu> {

    /**
     * 根据ID查询菜单
     * @param id
     * @return
     */
    Map getMenuById(String id);

    /**
     * 查询用户拥有的菜单权限
     * @param id
     * @return
     */
    List<Menu> findMenuByUser(String id);


    /**
     * 查询用户权限
     * @param userId
     * @return
     */
    List<String> findPremByUser(String userId);
}
