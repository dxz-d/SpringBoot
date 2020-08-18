package com.kuang.config;

import com.kuang.pojo.User;
import com.kuang.service.UserService;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * @description  自定义的UserRealm
 * @author diaoxiuze
 * @date 2020/8/7 15:51
 */

public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;


    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了=>>授权doGetAuthorizationInfo");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermission("user:add");

        // 拿到当前登录的这个对象
        Subject subject = SecurityUtils.getSubject();
        // 拿到User对象
        User currentUser = (User) subject.getPrincipal();

        //设置当前用户权限
        info.addStringPermission(currentUser.getPerms());
        return info;
    }

    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行了=>>认证doGetAuthenticationInfo");

        UsernamePasswordToken userToken = (UsernamePasswordToken) token;

        // 连接数据库
        User user = userService.queryUserByName(userToken.getUsername());

        // 如果没有这个用户
        if (user == null) {
            // 抛出异常 UnknownAccountException
            return null;
        }

        Subject currentSubject = SecurityUtils.getSubject();
        Session session = currentSubject.getSession();
        session.setAttribute("loginUser",user);

        // 可以多种加密  MD5  MD5盐值加密
        // 密码认证，shiro做
        return new SimpleAuthenticationInfo(user,user.getPwd(),"");
    }
}
