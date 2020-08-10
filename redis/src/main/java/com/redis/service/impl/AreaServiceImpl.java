package com.redis.service.impl;

import java.util.List;

import com.redis.dao.AreaDao;
import com.redis.entity.Area;
import com.redis.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaDao areaDao;


    @CacheEvict(value="common") //清除缓存
    //@Cacheable(value="common") //加入缓存
    @Override
    @Transactional
    public List<Area> getAreaList() {
        return areaDao.queryArea();
    }

}
