package com.shiro.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shiro.result.ResResult;
import com.shiro.entity.Gen;

/**
 * <p>
 * 代码生成表  服务类
 * </p>
 *
 * @author ify
 * @since 2021-01-28
 */
public interface GenService extends IService<Gen> {


    /**
     * 生成代码
     * @param gen
     */
    void codeGenerator(Gen gen);

    /**
     * 新增/更新
     * @param gen
     * @return
     */
    ResResult saveOrUpdateGen (Gen gen);

    /**
     * 根据ID删除
     * @param id
     * @return
     */
    ResResult delGenById(String id);

    /**
     * 数据库表
     * @return
     */
    ResResult listTable();

}
