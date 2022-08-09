package com.mqtt.service;

import com.mqtt.pojo.Device;
import org.springframework.stereotype.Repository;

/**
 * @description:
 * @author: dxz
 * @date: 2021/9/15 16:26
 */
public interface DeviceService {

    boolean updateDeviceStatus(Device param);
}
