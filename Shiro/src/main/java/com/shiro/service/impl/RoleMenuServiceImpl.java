package com.shiro.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shiro.dao.RoleMenuDao;
import com.shiro.entity.RoleMenu;
import com.shiro.service.RoleMenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 * @description 角色菜单关系表 服务实现类
 * @author FANG
 * @since 2021-01-29
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuDao, RoleMenu>implements RoleMenuService {

    /**
     * 批量保存
     * @param menuIds
     * @param roleId
     */
    @Override
    public void saveRoleMenuBatch(List<String> menuIds, String roleId){
        //先删除 角色菜单
        this.delBatch(roleId);
        // 菜单不为空保存
        if (!menuIds.isEmpty()) {
            List<RoleMenu> saveList = CollUtil.newArrayList();
            menuIds.forEach(id -> {
                RoleMenu roleMenu = new RoleMenu();
                roleMenu.setRoleId(roleId);
                roleMenu.setMenuId(id);
                saveList.add(roleMenu);
            });
            this.saveBatch(saveList);
        }
    }


    @Override
    public void delBatch(String roleId) {
        QueryWrapper<RoleMenu> del = new QueryWrapper();
        del.eq("role_id", roleId);
        this.remove(del);
    }
}
