package com.redis.service;

import java.util.List;

import com.redis.entity.Area;

/**
 * 区域相关操作service接口
 *
 * @author zhu
 *
 */
public interface AreaService {

    List<Area> getAreaList();

}
