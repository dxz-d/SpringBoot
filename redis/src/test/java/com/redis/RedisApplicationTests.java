package com.redis;

import com.redis.entity.Area;
import com.redis.service.AreaService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class RedisApplicationTests {

    @Autowired
    private AreaService areaService;

    @Test
    public void test() {
        List<Area> areaList = areaService.getAreaList();
        for(Area area : areaList) {
            System.out.println(area.getAreaName());
        }
    }

}
