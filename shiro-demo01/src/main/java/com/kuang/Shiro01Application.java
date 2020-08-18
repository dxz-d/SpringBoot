package com.kuang;

import com.kuang.config.UserRealm;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 12057
 */
@SpringBootApplication
public class Shiro01Application {

    public static void main(String[] args) {
        SpringApplication.run(Shiro01Application.class, args);
    }

}
