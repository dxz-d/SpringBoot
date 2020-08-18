package com.diaoxiuze.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description 登录
 * @Author 张小黑的猫
 * @data 2019-05-22 18:17
 */
@RestController
public class LoginController {

    @GetMapping("/login")
    public String login(){
        return "getLoginSuccess";
    }



    @PostMapping("/login")
    public String login(String username, String password, Model model,HttpServletResponse response) throws IOException {

        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        Subject currentUser = SecurityUtils.getSubject();

        try {
            //主体提交登录请求到SecurityManager
            currentUser.login(token);
        }catch (IncorrectCredentialsException ice){
            model.addAttribute("msg","密码不正确");
        }catch(UnknownAccountException uae){
            model.addAttribute("msg","账号不存在");
        }catch(AuthenticationException ae){
            model.addAttribute("msg","状态不正常");
        }
        if(currentUser.isAuthenticated()){
            System.out.println("认证成功");
            model.addAttribute("username",username);
            response.sendRedirect("success.html");
            return "success";
        }else{
            token.clear();
            response.sendRedirect("account.html");
            return "login";
        }
    }
}