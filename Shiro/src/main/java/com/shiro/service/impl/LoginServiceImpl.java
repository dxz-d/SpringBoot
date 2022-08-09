package com.shiro.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shiro.entity.User;
import com.shiro.result.Constant;
import com.shiro.result.GlobalEnum;
import com.shiro.result.ResResult;
import com.shiro.result.ResResultCode;
import com.shiro.service.LoginService;
import com.shiro.service.UserService;
import com.shiro.until.JwtUtil;
import com.shiro.until.RedisUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: TODO
 * CreateTime: 2022/8/8 11:15
 *
 * @author: dxz
 */
@Service
@Log4j2
public class LoginServiceImpl implements LoginService {

    @Value("${privateKey}")
    private String privateKey;

    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtil redisUtil;


    @Override
    public ResResult doLogin(User user) {
        if (StrUtil.isEmpty(user.getUsername()) || StrUtil.isEmpty(user.getPassword())) {
            return ResResult.failure(ResResultCode.USERNAME_OR_PASSWORD_ERROR);
        }
        // 验证 用户
        RSA rsa = new RSA(privateKey, null);
        String userName = rsa.decryptStr(user.getUsername(), KeyType.PrivateKey);
        QueryWrapper queryWrapper = new QueryWrapper<User>();
        queryWrapper.eq("username", userName);
        User userTemp = this.userService.getOne(queryWrapper);
        if (userTemp == null) {
            return ResResult.failure(ResResultCode.USERNAME_OR_PASSWORD_ERROR);
        }
        //验证 密码
        // 1、私钥解密rsa 加密的密码
        String password = rsa.decryptStr(user.getPassword(), KeyType.PrivateKey);
        // 2、不可逆加密密码，比较数据库密码
        if (!DigestUtil.bcryptCheck(password, userTemp.getPassword())) {
            return ResResult.failure(ResResultCode.USERNAME_OR_PASSWORD_ERROR);
        }
        // 判断用户是否被禁用
        if (userTemp.getLocked().equals(GlobalEnum.STATUS_LOCK.getCode())) {
            return ResResult.failure("该账号不已被禁用！");
        }
        // 验证成功，生成token存进redis
        String token = JwtUtil.sign(userTemp.getUsername(), userTemp.getPassword());
        redisUtil.set(Constant.LOGIN_PREFIX + userTemp.getId(), token, Constant.TOKEN_EXPIRE);
        Map<String, Object> rs = new HashMap<String, Object>(16);
        rs.put("token", token);
        return ResResult.success(rs);
    }
}
