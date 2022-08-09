package com.mqtt.service.impl;

import com.mqtt.mapper.DeviceMapper;
import com.mqtt.pojo.Device;
import com.mqtt.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: dxz
 * @date: 2021/9/15 16:35
 */
@Service
@Component
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceMapper deviceMapper;

    @Override
    public boolean updateDeviceStatus(Device param) {
        return deviceMapper.updateDeviceStatus(param);
    }
}
