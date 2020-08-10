package com.redis.dao;

import java.util.List;

import com.redis.entity.Area;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 对区域操作的dao�?
 * @author zhu
 *
 */
@Repository
@Mapper
public interface AreaDao {
    /**
     * 查询�?有区�?
     * @return
     */
    List<Area> queryArea();

}

