package com.shiro.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.shiro.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 代码生成表
 * </p>
 *
 * @author ify
 * @since 2021-01-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_gen")
public class Gen extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 表名
     */
    private String tableName;

    /**
     * 表名前缀 填写前缀，生成实体不包含前缀
     */
    private String tablePrefix;

    /**
     * 模块名称 模块名称用来分类
     */
    private String module;

    /**
     * 功能描述
     */
    private String description;

    /**
     * 作者
     */
    private String author;


}
