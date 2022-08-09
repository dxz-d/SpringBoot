package com.shiro.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.shiro.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 菜单表
 * </p>
 *
 * @author FANG
 * @since 2021-02-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_menu")
@ApiModel(value="Menu对象", description="菜单表")
public class Menu extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "父级菜单ID")
    @TableField("parent_id")
    private String parentId;

    @ApiModelProperty(value = "菜单编码")
    @TableField("code")
    private String code;

    @ApiModelProperty(value = "菜单名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "菜单类型 0：菜单 | 1：按钮（权限）")
    @TableField("type")
    private String type;

    @ApiModelProperty(value = "权限标识")
    @TableField("permission")
    private String permission;

    @ApiModelProperty(value = "图标")
    @TableField("icon")
    private String icon;

    @ApiModelProperty(value = "路由地址")
    @TableField("path")
    private String path;

    @ApiModelProperty(value = "组件路径")
    @TableField("component")
    private String component;

    @ApiModelProperty(value = "排序")
    @TableField("sort")
    private String sort;

    @ApiModelProperty(value = "是否禁用 0：正常 | 1：禁用")
    @TableField("forbidden")
    private String forbidden;

    @ApiModelProperty(value = "是否显示 0：显示 | 1：隐藏")
    @TableField("hidden")
    private String hidden;

}
