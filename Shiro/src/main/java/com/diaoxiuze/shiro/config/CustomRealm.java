package com.diaoxiuze.shiro.config;

import com.diaoxiuze.shiro.poject.User;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: shiro的自定义realm
 * Realm：域，Shiro从从Realm获取安全数据（如用户、角色、权限），就是说SecurityManager要验证用户身份，那么它需要从Realm获取相应的用户进行比较以确定用户身份是否合法；也需要从Realm得到用户相应的角色/权限进行验证用户是否能进行操作；可以把Realm看成DataSource，即安全数据源。
 * @Author 张小黑的猫
 * @data 2019-05-22 17:51
 */
public class CustomRealm extends AuthorizingRealm {
    @Override
    /**
     * 认证
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //1.获取用户输入的账号
        String username = (String)token.getPrincipal();
        //2.通过username从数据库中查找到user实体
        User user = getUserByUserName(username);
        if(user == null){
            return null;
        }
        //3.通过SimpleAuthenticationInfo做身份处理
        SimpleAuthenticationInfo simpleAuthenticationInfo =
                new SimpleAuthenticationInfo(user,user.getPassword(),getName());
        //4.用户账号状态验证等其他业务操作
        if(!user.getAvailable()){
            throw new AuthenticationException("该账号已经被禁用");
        }
        //5.返回身份处理对象
        return simpleAuthenticationInfo;
    }

    @Override
    /**
     * 授权
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        return null;
    }

    /**
     * 模拟通过username从数据库中查找到user实体
     * @param username
     * @return
     */
    private User getUserByUserName(String username){
        List<User> users = getUsers();
        for(User user : users){
            if(user.getUsername().equals(username)){
                return user;
            }
        }
        return null;
    }

    /**
     * 模拟数据库数据
     * @return
     */
    private List<User> getUsers(){
        List<User> users = new ArrayList<>();
        users.add(new User("张小黑的猫","123qwe",true));
        users.add(new User("张小黑的狗","123qwe",false));
        return users;
    }

}
