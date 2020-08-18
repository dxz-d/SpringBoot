package com.kuang.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @description
 * @author diaoxiuze
 * @date 2020/8/7 15:50
 */
@Configuration
public class ShiroConfig {


    /**
     * @description  不能简写
     * @author diaoxiuze
     * @date 2020/8/7 19:56
     * @param
     * @return com.kuang.config.UserRealm
     */
    @Bean(name = "userRealm")
    public UserRealm userRealm(){
        return new UserRealm();
    }


    /**
     * DefaultWebSecurityManage
     * @return
     */
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManage() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 关联UserRealm  创建Realm对象 需要自定义类
        securityManager.setRealm(userRealm());
        return securityManager;
    }

    /**
     * ShiroFilterFactoryBean
     * @param defaultWebSecurityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        // 设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);

        // 添加shiro的内置过滤器
        /**
         * anno：无需认证就可以访问
         * anthc：必须认证了才能访问
         * user：必须拥有 记住我 功能才能用
         * perms：拥有对某个资源的权限才能访问
         * role：拥有某个角色权限才能访问
         *
         * /*filterMap.put("/user/add", "authc");
         * filterMap.put("/user/update", "authc");
         */
        // 拦截
        Map<String, String> filterMap= new LinkedHashMap<>();

        // 授权,正常情况下，没有授权会跳转到未授权页面
        filterMap.put("/user/add", "perms[user:add]");
        filterMap.put("/user/update", "perms[user:update]");

        filterMap.put("/user/*", "authc");
        bean.setFilterChainDefinitionMap(filterMap);

        // 设置登录的请求
        bean.setLoginUrl("/toLogin");
        // 未授权页面
        bean.setUnauthorizedUrl("/noauth");

        return bean;
    }

    /**
     * 整合ShiroDialect：用来整合shiro thymeleaf
     * @return
     */
    @Bean
    public ShiroDialect getShiroDialect() {
        return new ShiroDialect();
    }
}
