package com.shiro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @description
 * @author diaoxiuze
 * @date 2020/8/7 15:02
 */
@Controller
public class MyController {

    @RequestMapping({"/", "/index"})
    public String toIndex(Model model) {
        model.addAttribute("msg", "Hello,Shiro");
        return "index";
    }
}
