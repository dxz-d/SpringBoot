package com.diaoxiuze.shiro.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping("/home")
    public String home(){
        return "Hello, Zbook!";
    }

    public static void hello() {
        System.out.println("module2 中的方法");
    }

}
