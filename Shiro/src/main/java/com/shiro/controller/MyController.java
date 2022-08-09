package com.shiro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description
 * @author diaoxiuze
 * @date 2020/8/7 15:02
 */
@Controller
public class MyController {

    @ResponseBody
    @RequestMapping({ "/index"})
    public String toIndex() {
        return "index";
    }
}
