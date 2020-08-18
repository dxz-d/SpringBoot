package com.kuang.mapper;

import com.kuang.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @description
 * @author diaoxiuze
 * @date 2020/8/7 18:47
 */
@Repository
@Mapper
public interface UserMapper {

    /**
     * 通过名字查询用户
     * @param name
     * @return
     */
    User queryUserByName(String name);
}
