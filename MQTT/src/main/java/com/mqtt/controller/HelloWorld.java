package com.mqtt.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: dxz
 * @date: 2021/9/14 10:49
 */
@RestController
public class HelloWorld {

    @RequestMapping("/hello")
    public String helloWorld() {
        System.out.println("hello, world");
        return "hello, world";
    }
}
