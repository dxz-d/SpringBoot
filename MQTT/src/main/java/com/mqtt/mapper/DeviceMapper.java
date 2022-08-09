package com.mqtt.mapper;

import com.mqtt.pojo.Device;
import org.springframework.stereotype.Repository;

/**
 * @description:
 * @author: dxz
 * @date: 2021/9/15 17:12
 */
@Repository
public interface DeviceMapper {

    boolean updateDeviceStatus(Device param);
}
