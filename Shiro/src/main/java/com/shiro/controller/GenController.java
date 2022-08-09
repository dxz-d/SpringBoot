package com.shiro.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.xiaoymin.knife4j.annotations.DynamicParameter;
import com.github.xiaoymin.knife4j.annotations.DynamicParameters;
import com.shiro.base.BaseController;
import com.shiro.entity.Gen;
import com.shiro.result.ResResult;
import com.shiro.service.GenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description: TODO
 * CreateTime: 2022/8/9 11:06
 *
 * @author: dxz
 */
@RestController
@RequestMapping("/sys")
@Api(tags = "GenApi(代码生成表)", value = "代码生成表")
public class GenController extends BaseController {

    @Autowired
    private GenService genService;

    @PostMapping(value = "/gen/code")
    @ApiOperation(value = "代码生成", notes = "接口描述")
    @DynamicParameters(name = "generatorCode", properties = {
            @DynamicParameter(name = "tableName", value = "表名", example = "", required = true, dataTypeClass = String.class),
            @DynamicParameter(name = "tablePrefix", value = "表名前缀 填写前缀，生成实体不包含前缀", example = "", required = false, dataTypeClass = String.class),
            @DynamicParameter(name = "module", value = "模块名称 模块名称用来分类", example = "", required = true, dataTypeClass = String.class),
            @DynamicParameter(name = "description", value = "功能描述", example = "", required = false, dataTypeClass = String.class),
            @DynamicParameter(name = "author", value = "作者", example = "", required = false, dataTypeClass = String.class),
    })
    public ResResult generatorCode(@RequestBody JSONObject param) {
        this.genService.codeGenerator(param.toJavaObject(Gen.class));
        return ResResult.success();
    }
}
