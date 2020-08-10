package com.redis.entity;


import java.io.Serializable;
import java.util.Date;
/**
 * 区域实体�?
 * @author zhu
 *
 */
public class Area implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 4317293124884395131L;

    //区域id
    private Long areaId;
    //区域�?
    private String areaName;
    //优先�?
    private Integer priority;
    //创建时间
    private Date createTime;
    //�?后修改时�?
    private Date lastEditTime;

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastEditTime() {
        return lastEditTime;
    }

    public void setLastEditTime(Date lastEditTime) {
        this.lastEditTime = lastEditTime;
    }




}

