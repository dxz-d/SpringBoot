package springboot.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Description: TODO
 * CreateTime: 2022/4/26 14:13
 * @author: dxz
 */
@Data
@TableName("t_role_permission")
public class RolePermission {

    @TableId
    private Long id;

    @TableField("role_id")
    private Long roleId;

    @TableField("permission_id")
    private Long permissionId;
}

