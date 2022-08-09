package com.shiro.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.shiro.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Description: TODO
 * CreateTime: 2022/8/8 10:08
 *
 * @author: dxz
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_user")
public class User extends BaseEntity {
    private static final long serialVersionUID = 1L;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 性别 0 女| 1 男
     */
    private String sex;
    /**
     * 是否锁住 0 否 | 1 是
     */
    private String locked;
}
