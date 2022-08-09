package com.shiro.result;

/**
 * Description: TODO
 * CreateTime: 2022/8/8 15:32
 *
 * @author: dxz
 */
public class Constant {
    /**
     * token过期时间 15分钟
     */
    public final static long TOKEN_EXPIRE = 60 * 3;
    /**
     * 存放token的header名称
     */
    public final static String TOKEN_HERDER_KEY = "Authorization";
    /**
     * token的前缀
     */
    public final static String TOKEN_PREFIX = "Bearer";
    /**
     * redis存储token的key前缀
     */
    public final static String LOGIN_PREFIX = "LOGIN_";
}
