package com.mqtt.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @description:
 * @author: dxz
 * @date: 2021/9/15 16:27
 */
@Data
@Accessors(chain = true)
public class Device implements Serializable {

    private String username;

    private String ts;

}
