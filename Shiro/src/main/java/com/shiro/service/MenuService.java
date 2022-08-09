package com.shiro.service;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shiro.entity.Menu;
import com.shiro.result.ResResult;

import java.util.List;

/**
 *
 * 菜单表 服务类
 *
 *
 * @author FANG
 * @since 2021-02-07
 */
public interface MenuService extends IService<Menu> {

    /**
     * 新增菜单
     * @param menu
     * @return
     */
    ResResult saveMenu(Menu menu);

    /**
     * 编辑菜单
     * @param menu
     * @return
     */
    ResResult updateMenu(Menu menu);

    /**
     * 根据ID删除菜单
     * @param id
     * @return
     */
    ResResult deleteById(String id);

    /**
     * 根据ID获取菜单
     * @param id
     * @return
     */
    ResResult getMenuById(String id);


    /**
     * 查询菜单树结构
     * @return
     */
    ResResult listMenuTree();

    /**
     * 根据用户查询归属菜单
     *
     * @param userId
     * @return
     */
    List<Tree<String>> findMenuByUser(String userId);

    /**
     * 查询用户权限
     * @param userId
     * @return
     */
    List<String> findPremByUser(String userId);
}
