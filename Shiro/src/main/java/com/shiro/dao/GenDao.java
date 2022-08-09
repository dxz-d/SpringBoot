package com.shiro.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shiro.entity.Gen;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 代码生成表  Mapper 接口
 * </p>
 *
 * @author ify
 * @since 2021-01-28
 */
public interface GenDao extends BaseMapper<Gen> {

    /**
     * 获取数据库表名
     * @return
     */
    @Select("show tables")
    List<String> listTableName();
}
