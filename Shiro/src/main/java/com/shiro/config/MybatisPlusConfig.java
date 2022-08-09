package com.shiro.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.shiro.handler.MpMetaObjectHandler;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description: TODO
 * CreateTime: 2022/8/8 09:57
 *
 * @author: dxz
 */
@Configuration
public class MybatisPlusConfig {
    /**
     * 相当于顶部的：@MapperScan("com.ify.sample.module.*.dao")
     * {@code @MapperScan("com.ify.sample.module.*.dao")}
     * 这里可以扩展，比如使用配置文件来配置扫描Mapper的路径
     */
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer scannerConfigurer = new MapperScannerConfigurer();
        scannerConfigurer.setBasePackage("com.ify.sampleAdmin.web.dao");
        return scannerConfigurer;
    }
    /**
     * 新的分页插件,一缓和二缓遵循mybatis的规则,需要设置 MybatisConfiguration#useDeprecatedExecutor = false 避免缓存出现问题(该属性会在旧插件移除后一同移除)
     */ @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }
    /**
     * 公共字段自动填充策略
     *
     * @return
     */
    @Bean
    public MpMetaObjectHandler mpMetaObjectHandler() {
        return new MpMetaObjectHandler();
    }
}
