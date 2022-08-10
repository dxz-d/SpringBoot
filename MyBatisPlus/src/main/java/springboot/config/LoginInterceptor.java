package springboot.config;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import springboot.annotations.LoginRequired;
import springboot.entity.User;
import springboot.enu.ExceptionCodeEnum;
import springboot.enu.WebConstant;
import springboot.exception.BizException;
import springboot.util.ThreadLocalUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;

/**
 * Description: TODO
 * CreateTime: 2022/8/9 17:51
 * @author: dxz
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 不拦截跨域请求相关
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true;
        }
        // 如果方法上没有加@LoginRequired，无需登录，直接放行
        if (isLoginFree(handler)) {
            return true;
        }
        // 登录成功，把用户信息存入ThreadLocal
        User user = handleLogin(request, response);
        ThreadLocalUtil.put(WebConstant.USER_INFO, user);
        // 放行到Controller
        return super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 及时移除，避免ThreadLocal内存泄漏
        ThreadLocalUtil.remove(WebConstant.USER_INFO);
        super.afterCompletion(request, response, handler, ex);
    }

    /**
     * 接口是否免登录
     *
     * @param handler
     * @return
     */
    private boolean isLoginFree(Object handler) {
        // 判断是否支持免登录
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            // AnnotationUtils是Spring提供的注解工具类，还有很多其他便利的方法
            LoginRequired loginRequiredAnnotation = AnnotationUtils.getAnnotation(method, LoginRequired.class);
            // 没有加@LoginRequired，不需要登录
            return loginRequiredAnnotation == null;
        }
        return true;
    }

    /**
     * 登录校验
     * @param request
     * @param response
     * @return
     */
    private User handleLogin(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute(WebConstant.CURRENT_USER_IN_SESSION);
        if (currentUser == null) {
            // 抛异常，请先登录（还有一种方式，就是利用response直接write返回JSON，但不推荐）
            throw new BizException(ExceptionCodeEnum.NEED_LOGIN);
        }
        return currentUser;
    }
}
