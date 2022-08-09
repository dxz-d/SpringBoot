package com.mqtt.mqtt;

/**
 * @description:
 * @author: dxz
 * @date: 2021/9/14 11:02
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mqtt.pojo.Device;
import com.mqtt.service.DeviceService;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.jfunc.json.Json;

import java.lang.reflect.Type;

/**
 * 常规MQTT回调函数
 *
 * @author Mr.Qu
 * @since 2020/1/9 16:26
 */
@Slf4j
@Component
public class Callback implements MqttCallback {

    @Autowired(required = false)
    private DeviceService deviceService;

    /**
     * MQTT 断开连接会执行此方法
     */
    @Override
    public void connectionLost(Throwable throwable) {
        log.info("断开了MQTT连接 ：{}", throwable.getMessage());
        log.error(throwable.getMessage(), throwable);
    }

    /**
     * publish发布成功后会执行到这里
     */
    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        log.info("发布消息成功");
    }

    /**
     * subscribe订阅后得到的消息会执行到这里
     */
    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        log.info("[{}] : {}", topic, new String(message.getPayload()));
        //  TODO    此处可以将订阅得到的消息进行业务处理、数据存储
        String data = new String(message.getPayload());
        Device device  =  JSON.parseObject(data, Device.class);
        deviceService.updateDeviceStatus(device);

        /*try {
          JSONObject jsonObject = JSON.parseObject(msg);
          String clientId = String.valueOf(jsonObject.get("clientid"));
          if (topic.endsWith("/disconnected")) {
            log.info("客户端已掉线：{}", clientId);
          } else {
            log.info("客户端已上线：{}", clientId);
          }
        } catch (JSONException e) {
          log.error("JSON Format Parsing Exception : {}", msg);
        }*/
    }
}
