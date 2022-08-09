package com.shiro.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Description: TODO
 * CreateTime: 2022/8/8 09:53
 *
 * @author: dxz
 */
@Data
public class BaseEntity {
    /**
     * id 生成策略为UUID
     */ @TableId(type = IdType.ASSIGN_UUID)
    private String id;
    /**
     * 创建者 填充策略为插入自动填充
     */
    @TableField(fill = FieldFill.INSERT)
    private String createBy;
    /**
     * 创建时间 填充策略为插入自动填充
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
    /**
     * 更新者 填充策略为更新自动填充
     */
    @TableField(fill = FieldFill.UPDATE)
    private String updateBy;
    /**
     * 更新时间 填充策略为更新自动填充
     */
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;
}
