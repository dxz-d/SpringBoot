package com.diaoxiuze.shiro.config;


import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.apache.shiro.mgt.SecurityManager;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Description springboot中的Shiro配置类
 * @Author 张小黑的猫
 * @data 2019-05-22 17:17
 */
@Configuration
public class ShiroConfig {


   /* public SecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //将自定义的realm交给SecurityManager管理
        securityManager.setRealm(new CustomRealm());
        return securityManager;
    }


    *//*
    @Bean
    public ShiroFilterFactoryBean webFilter(){

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置securityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager());

        //配置拦截链 使用LinkedHashMap,因为LinkedHashMap是有序的，shiro会根据添加的顺序进行拦截
        // Map<K,V> K指的是拦截的url V值的是该url是否拦截
        Map<String,String> filterChainMap = new LinkedHashMap<String,String>(16);
        //配置退出过滤器logout，由shiro实现
        filterChainMap.put("/logout","logout");
        //authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问,先配置anon再配置authc。
        filterChainMap.put("/login","anon");
        filterChainMap.put("/**", "authc");

        //设置默认登录的URL.
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainMap);
        return shiroFilterFactoryBean;
    }*/


    @Bean
    public static LifecycleBeanPostProcessor getLifecycleBeanProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 配置Shiro的Web过滤器，拦截浏览器请求并交给SecurityManager处理
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        return shiroFilterFactoryBean;
    }

    /**
     * 配置Shiro核心 安全管理器 SecurityManager
     * SecurityManager安全管理器：所有与安全有关的操作都会与SecurityManager交互；且它管理着所有Subject；
     * 负责与后边介绍的其他组件进行交互。（类似于SpringMVC中的DispatcherServlet控制器）
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //将自定义的realm交给SecurityManager管理
        securityManager.setRealm(new CustomRealm());
        return securityManager;
    }


    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}