package com.shiro.shiro;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shiro.entity.User;
import com.shiro.result.Constant;
import com.shiro.service.MenuService;
import com.shiro.service.UserService;
import com.shiro.until.JwtUtil;
import com.shiro.until.RedisUtil;
import com.shiro.until.UserUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author FANG
 * @create 2021-01-26-9:58
 */
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 授权方法
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        String username = JwtUtil.getUsername(UserUtil.getTokenWithoutPrefix(principalCollection.toString()));
        User user = this.userService.getOne(new QueryWrapper<User>().eq("username", username));
        if (user != null) {
            // 获取当前用户拥有的权限
            List<String> perms = this.menuService.findPremByUser(user.getId());
            for (String perm : perms) {
                authorizationInfo.addStringPermission(perm);
            }
        }

        return authorizationInfo;
    }

    /**
     * 认证方法
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 获取身份信息
        BearerToken bearerToken = (BearerToken) authenticationToken;
        // 去掉token前缀
        String token = bearerToken.getToken();
        if (StrUtil.isEmpty(token)) {
            throw new AuthenticationException("token is empty");
        }
        token = token.replace(Constant.TOKEN_PREFIX, "").trim();
        // jwt解析token 获取用户名
        String username = JwtUtil.getUsername(token);
        if (StrUtil.isEmpty(username)) {
            throw new AuthenticationException("token invalid");
        }
        // 查询数据库用户是否存在
        QueryWrapper queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq("username", username);
        User user = userService.getOne(queryWrapper);
        if (user == null) {
            throw new AuthenticationException("User didn't existed!");
        }
        //验证token是否合法
        if (!JwtUtil.verify(token, username, user.getPassword())) {
            throw new AuthenticationException("Username or password error");
        }
        // 验证token 是否过期
        String cacheToken = (String) redisUtil.get(Constant.LOGIN_PREFIX + user.getId());
        if (StrUtil.isEmpty(cacheToken) || !StrUtil.equals(cacheToken, token)) {
            throw new AuthenticationException("cacheToken is empty or token incorrect");
        }
        //验证通过刷新token 时间
        redisUtil.expire(Constant.LOGIN_PREFIX + user.getId(), Constant.TOKEN_EXPIRE);

        return new SimpleAuthenticationInfo(bearerToken.getToken(), bearerToken.getToken(), "shiroRealm");

    }
}
