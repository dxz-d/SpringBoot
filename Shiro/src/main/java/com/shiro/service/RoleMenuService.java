package com.shiro.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shiro.entity.RoleMenu;

import java.util.List;

/**
 *
 * 角色菜单关系表 服务类
 *
 *
 * @author FANG
 * @since 2021-01-29
 */
public interface RoleMenuService extends IService<RoleMenu> {


    /**
     * 批量保存角色菜单信息
     * @param menuIds
     * @return
     */
    void saveRoleMenuBatch(List<String> menuIds, String roleId);

    /**
     * 根据角色ID批量删除
     * @param roleId
     */
    void delBatch(String roleId);
}
