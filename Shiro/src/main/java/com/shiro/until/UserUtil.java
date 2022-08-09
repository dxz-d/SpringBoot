package com.shiro.until;

import com.shiro.result.Constant;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author FANG
 * @create 2021-02-22-15:41
 */
public class UserUtil {

    /**
     * 获取当前登陆用户名
     * @return
     */
    public static String getUsername() {
        return JwtUtil.getUsername(getToken());
    }

    /**
     * 去掉token前缀
     * @param token
     * @return
     */
    public static String getTokenWithoutPrefix(String token) {
        if (token != null) {
            token = token.replace(Constant.TOKEN_PREFIX, "").trim();
        }
        return token;
    }

    /**
     * 获取请求token
     * @return
     */
    public static String getToken() {
        String token = null;
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        token = request.getHeader(Constant.TOKEN_HERDER_KEY);
        // 去掉前缀和空格
        token = token.replace(Constant.TOKEN_PREFIX, "").trim();

        return token;
    }
}
