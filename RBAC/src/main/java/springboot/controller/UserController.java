package springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import springboot.annotations.LoginRequired;
import springboot.annotations.PermissionRequired;
import springboot.annotations.RequiresPermission;
import springboot.entity.Permission;
import springboot.entity.RolePermission;
import springboot.entity.User;
import springboot.entity.UserRole;
import springboot.enu.Logical;
import springboot.enu.UserType;
import springboot.enu.WebConstant;
import springboot.mapper.*;
import springboot.result.Result;
import springboot.util.ConvertUtil;

import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Description: TODO
 * CreateTime: 2022/8/9 17:55
 * @author: dxz
 */
@RestController
@RequestMapping("/user")
public class UserController {

    /**
     * 由于要查询用户权限，五张表都来了
     */
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private HttpSession session;

    @PostMapping("/register")
    public Result<User> register(@RequestBody User userInfo) {
        int rows = userMapper.insert(userInfo);
        if (rows > 0) {
            return Result.success(userInfo);
        }

        return Result.error("插入失败");
    }

//    @PostMapping("/login")
//    public Result<User> login(@RequestBody User loginInfo) {
//        // 用的是MyBatis-Plus
//        LambdaQueryWrapper<User> lambdaQuery = Wrappers.lambdaQuery();
//        lambdaQuery.eq(User::getName, loginInfo.getName());
//        lambdaQuery.eq(User::getPassword, loginInfo.getPassword());
//
//        User user = userMapper.selectOne(lambdaQuery);
//        if (user == null) {
//            return Result.error("用户名或密码错误");
//        }
//
//        session.setAttribute(WebConstant.CURRENT_USER_IN_SESSION, user);
//        return Result.success(user);
//    }

    @LoginRequired
    @GetMapping("/needLogin")
    public Result<String> needLogin() {
        return Result.success("if you see this, you are logged in.");
    }

    @GetMapping("/needNotLogin")
    public Result<String> needNotLogin() {
        return Result.success("if you see this, you are logged in.");
    }

//    @PermissionRequired(userType = {UserType.ADMIN,UserType.TEACHER}, logical = Logical.OR)
    @RequiresPermission()
    @GetMapping("/needPermission")
    public Result<String> needPermission() {
        return Result.success("if you see this, you has the permission.");
    }

    @PostMapping("/login")
    public Result<User> login(@RequestBody User loginInfo) {
        LambdaQueryWrapper<User> lambdaQuery = Wrappers.lambdaQuery();
        lambdaQuery.eq(User::getName, loginInfo.getName());
        lambdaQuery.eq(User::getPassword, loginInfo.getPassword());

        User user = userMapper.selectOne(lambdaQuery);
        if (user == null) {
            return Result.error("用户名或密码错误");
        }

        // 1.Session记录登录状态
        session.setAttribute(WebConstant.CURRENT_USER_IN_SESSION, user);
        // 2.Session缓存用户拥有的权限
        session.setAttribute(WebConstant.USER_PERMISSIONS, getUserPermissions(user.getId()));

        return Result.success(user);
    }

    private Set<String> getUserPermissions(Long uid) {
        // 用户拥有的角色
        LambdaQueryWrapper<UserRole> userRoleQuery = Wrappers.lambdaQuery();
        userRoleQuery.eq(UserRole::getUserId, uid);
        List<UserRole> userRoles = userRoleMapper.selectList(userRoleQuery);
        List<Long> roleIds = ConvertUtil.resultToList(userRoles, UserRole::getRoleId);
        if (CollectionUtils.isEmpty(roleIds)) {
            // 没有角色，所有没有权限
            return new HashSet<>();
        }

        // 角色拥有的权限
        List<Long> permissionIds = new ArrayList<>();
        rolePermissionMapper.selectBatchIds(roleIds);
        roleIds.forEach(roleId -> {
            LambdaQueryWrapper<RolePermission> rolePermissionQuery = Wrappers.lambdaQuery();
            rolePermissionQuery.eq(RolePermission::getRoleId, roleId);
            List<RolePermission> rolePermissions = rolePermissionMapper.selectList(rolePermissionQuery);
            permissionIds.addAll(ConvertUtil.resultToList(rolePermissions, RolePermission::getPermissionId));
        });
        if (CollectionUtils.isEmpty(permissionIds)) {
            // 角色都没有分配权限
            return new HashSet<>();
        }

        // 查询权限对应的method
        return Optional.ofNullable(permissionMapper.selectBatchIds(permissionIds))
                .map(permissionList -> permissionList.stream().map(Permission::getMethod).collect(Collectors.toSet()))
                .orElse(Collections.emptySet());

    }

    @RequiresPermission()
    @GetMapping("/add")
    public String add() {
        return "add";
    }

}
