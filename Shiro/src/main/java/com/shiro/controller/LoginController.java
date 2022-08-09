package com.shiro.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.xiaoymin.knife4j.annotations.DynamicParameter;
import com.github.xiaoymin.knife4j.annotations.DynamicParameters;
import com.shiro.entity.User;
import com.shiro.result.ResResult;
import com.shiro.service.LoginService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description: TODO
 * CreateTime: 2022/8/8 11:17
 *
 * @author: dxz
 */
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    @ApiOperation(value = "登陆")
    @DynamicParameters(properties = {
            @DynamicParameter(name = "username",value = "用户名",required = true),
            @DynamicParameter(name = "password",value = "密码",required = true),
    })
    public ResResult login(@RequestBody JSONObject params) {
        return loginService.doLogin(params.toJavaObject(User.class));
    }
}
