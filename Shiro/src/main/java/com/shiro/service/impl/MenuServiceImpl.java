package com.shiro.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.shiro.dao.MenuDao;
import com.shiro.entity.Menu;
import com.shiro.entity.RoleMenu;
import com.shiro.result.ResResult;
import com.shiro.service.MenuService;
import com.shiro.service.RoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @description 菜单表 服务实现类
 * @author FANG
 * @since 2021-02-07
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MenuServiceImpl extends ServiceImpl<MenuDao, Menu>implements MenuService {

    @Autowired
    private RoleMenuService roleMenuService;

    @Override
    public ResResult saveMenu(Menu menu){
        // 判断菜单编码是否重复
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("code", menu.getCode());
        List<Menu> list = this.list(queryWrapper);
        if(list != null && list.size() > 0) {
            return ResResult.failure("菜单编码已存在！");
        }
        // 保存菜单
        this.save(menu);

        return ResResult.success();
    }


    @Override
    public ResResult updateMenu(Menu menu){
        // 判断菜单编码是否重复
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("code", menu.getCode());
        Menu existMenu = this.getOne(queryWrapper);
        if(existMenu != null && !existMenu.getId().equals(menu.getId())) {
            return ResResult.failure("菜单编码已存在！");
        }
        this.updateById(menu);

        return ResResult.success();
    }

    @Override
    public ResResult deleteById(String id){
        Menu menu = this.getById(id);
        List<Menu> result = new ArrayList<>();
        if (menu != null) {
            // 查询当前所有下级
            result.add(menu);
            findNextMenu(id, result);
        }
        // 删除
        for (Menu item : result) {
            // 菜单
            this.removeById(item.getId());
            // 删除角色菜单关系
            this.roleMenuService.remove(new QueryWrapper<RoleMenu>().eq("menu_id", item.getId()));
        }

        return ResResult.success();
    }

    /**
     * 递归遍历
     * @param id
     * @return
     */
    private void findNextMenu(String id, List<Menu> result) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("parent_id", id);
        List<Menu> list = this.list(queryWrapper);
        if (list.size() > 0) {
            result.addAll(list);
            for (Menu menu : list) {
                findNextMenu(menu.getId(), result);
            }
        }
    }


    @Override
    public ResResult getMenuById(String id){
        return ResResult.success(this.getBaseMapper().getMenuById(id));
    }


    /**
     * 查询菜单树结构
     * @return
     */
    @Override
    public ResResult listMenuTree() {
        List<Menu> menus = this.list();
        //转换器
        List<Tree<String>> treeNodes = this.menuToTree(menus);
        return ResResult.success(treeNodes);
    }

    @Override
    public List<Tree<String>> findMenuByUser(String userId) {
        List<Menu> list = this.getBaseMapper().findMenuByUser(userId);
        //转换器
        List<Tree<String>> treeNodes = this.menuToTree(list);
        return treeNodes;
    }

    /**
     * 查询用户权限
     * @param userId
     * @return
     */
    @Override
    public List<String> findPremByUser(String userId) {
        return this.getBaseMapper().findPremByUser(userId);
    }

    /**
     * 菜单list转树形结构
     * @param menus
     * @return
     */
    private List<Tree<String>> menuToTree(List<Menu> menus){
        // 构建node列表
        List<TreeNode<String>> nodeList = CollUtil.newArrayList();
        menus.forEach(e -> {
            TreeNode treeNode = new TreeNode<>(e.getId(), e.getParentId(), e.getName(), e.getSort());
            Map<String, Object> extra = CollUtil.newHashMap();
            extra.put("code", e.getCode());
            extra.put("permission", e.getPermission());
            extra.put("type", e.getType());
            extra.put("icon", e.getIcon());
            extra.put("path", e.getPath());
            extra.put("component", e.getComponent());
            extra.put("forbidden", e.getForbidden());
            extra.put("hidden", e.getHidden());
            extra.put("createTime", e.getCreateTime());
            extra.put("updateTime", e.getUpdateTime());
            treeNode.setExtra(extra);
            nodeList.add(treeNode);
        });

        TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
        treeNodeConfig.setParentIdKey("pId");
        //转换器
        List<Tree<String>> treeNodes = TreeUtil.build(nodeList, "0", treeNodeConfig,
                (treeNode, tree) -> {
                    tree.setId(treeNode.getId());
                    tree.setParentId(treeNode.getParentId());
                    tree.setWeight(treeNode.getWeight());
                    tree.setName(treeNode.getName());
                    tree.putAll(treeNode.getExtra());
                });

        return treeNodes;
    }
}
