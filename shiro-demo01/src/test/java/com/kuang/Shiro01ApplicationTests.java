package com.kuang;

import com.kuang.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Shiro01ApplicationTests {

    @Autowired
    UserService userService;

    @Test
    void contextLoads() {
        System.out.println(userService.queryUserByName("6666"));
    }

}
