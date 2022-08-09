package com.shiro.config;

import com.shiro.shiro.BearerTokenFilter;
import com.shiro.shiro.ShiroRealm;
import org.apache.shiro.authc.BearerToken;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

/**
 * Description: TODO
 * CreateTime: 2022/8/8 10:49
 *
 * @author: dxz
 */
@Configuration
public class ShiroConfig {
    /**
     * 注入ShiroRealm,自定义的realm 后面的认证和授权全在这里编写
     * @return
     */
    @Bean
    public ShiroRealm shiroRealm() {
        return new ShiroRealm();
    }
    /**
     * 创建SecurityManager
     * @return
     */
    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(ShiroRealm shiroRealm) {
        /**
         * securityManager对象，shiroRealm进行托管
         */
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置支持的AuthenticationToken
        shiroRealm.setAuthenticationTokenClass(BearerToken.class);
        securityManager.setRealm(shiroRealm());
        /**
         * 禁用session
         */
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        securityManager.setSubjectDAO(subjectDAO);
        return securityManager;
    }
    /**
     * 过滤器配置
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(securityManager);
        factoryBean.setLoginUrl("/login");
        // 添加自定义过滤器
        Map<String, Filter> filterMap = new HashMap<>(16);
        filterMap.put("tokenFilter", new BearerTokenFilter());
        factoryBean.setFilters(filterMap);
        /**
         * 自定义拦截规则
         */
        Map<String, String> filterRuleMap = new HashMap<>(16);
        // 对swagger相关url请求不进行拦截
        filterRuleMap.put("/doc.html", "anon");
        filterRuleMap.put("/swagger-resources/**", "anon");
        filterRuleMap.put("/v2/**", "anon");
        filterRuleMap.put("/webjars/**", "anon");
        // 其余请求都要经过BearerTokenFilter自定义拦截器
        filterRuleMap.put("/**", "tokenFilter");
        factoryBean.setFilterChainDefinitionMap(filterRuleMap);
        return factoryBean;
    }
}
