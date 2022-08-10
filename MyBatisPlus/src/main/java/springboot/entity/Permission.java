package springboot.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Date;

/**
 * Description: TODO
 * CreateTime: 2022/4/26 14:13
 * @author: dxz
 */
@Data
@TableName("t_permission")
public class Permission {

    @TableId
    private Long id;

    @TableField("module")
    private String module;

    @TableField("name")
    private String name;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;

    @TableLogic
    @TableField("deleted")
    private Integer deleted;

    @TableField("method")
    private String method;
}

