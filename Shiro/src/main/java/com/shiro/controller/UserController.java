package com.shiro.controller;

import com.alibaba.fastjson.JSONObject;
import com.shiro.base.BaseController;
import com.shiro.entity.User;
import com.shiro.result.ResResult;
import com.shiro.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Description: TODO
 * CreateTime: 2022/8/8 10:12
 *
 * @author: dxz
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户模块")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "列表")
    @GetMapping("/list")
    public String list() {
        List<User> userList = this.userService.list();
        return JSONObject.toJSONString(userList);
    }

    @GetMapping("/user")
    @ApiOperation(value = "获取根据ID获取用户")
    @ApiImplicitParam(name = "id", value = "用户id", required = true)
    public ResResult getUser(@RequestParam(value = "id") String id) {
        return ResResult.success();
    }

    @GetMapping(value = "/info")
    @ApiOperation(value = "获取用户相关信息", notes = "接口描述")
    public ResResult getUserInfo(){
        return this.userService.getUserInfo();
    }
}
