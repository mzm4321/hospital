package com.example.hospital.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.hospital.entity.User;
import com.example.hospital.service.LoginService;
import com.example.hospital.service.UserService;
import com.example.hospital.utils.JWTUtils;
import com.example.hospital.utils.PasswordUtils;
import com.example.hospital.utils.UserThreadLocal;
import com.example.hospital.vo.ErrorCode;
import com.example.hospital.vo.Result;
import com.example.hospital.vo.params.LoginParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Map;
import java.util.concurrent.TimeUnit;
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public Result logout(String token) {
        redisTemplate.delete("TOKEN_" + token);
        return Result.success(null);
    }


    @Override
    public Result register(LoginParam loginParam) {
        String userName = loginParam.getUserName();
        String password = loginParam.getPassword();
        String nickname = loginParam.getNickname();
        if (StringUtils.isBlank(userName)
                || StringUtils.isBlank(password)
                || StringUtils.isBlank(nickname)
        ) {
            return Result.fail(ErrorCode.PARAMS_ERROR.getCode(), ErrorCode.PARAMS_ERROR.getMsg());
        }
        User user = this.userService.findUserByUserName(userName);
        if (user != null) {
            return Result.fail(ErrorCode.ACCOUNT_EXIST.getCode(), ErrorCode.ACCOUNT_EXIST.getMsg());
        }
        user = new User();
        user.setNickname(nickname);
        user.setUserName(userName);
        user.setPassword(PasswordUtils.getRealPassword(password));
        user.setAvatar("http://localhost:8081/p1.jpg");
        user.setRole("admin"); //1 为true
        this.userService.save(user);

        String token = JWTUtils.createToken(user.getId());

        redisTemplate.opsForValue().set("TOKEN_" + token, JSON.toJSONString(user), 1, TimeUnit.DAYS);
        return Result.success(token);

    }

    @Override
    public User getUserbyToken(String token) {
        if (StringUtils.isBlank(token)) {
            return null;
        }
        if (StringUtils.split(token, '.').length < 3) {
            return null;
        }
        System.out.println(token);
        Map<String, Object> stringObjectMap = JWTUtils.checkToken(token);
        if (stringObjectMap == null) {
            return null;
        }
        String userJson = redisTemplate.opsForValue().get("TOKEN_" + token);
        if (StringUtils.isBlank(userJson)) {
            return null;
        }
        User user = JSON.parseObject(userJson, User.class);
        return user;
    }

    @Override
    public Result login(LoginParam loginParam) {
        String userName = loginParam.getUserName();
        String password = loginParam.getPassword();
        if (StringUtils.isBlank(userName) || StringUtils.isBlank(password)) {
            return Result.fail(ErrorCode.PARAMS_ERROR.getCode(), ErrorCode.PARAMS_ERROR.getMsg());
        }
        password= PasswordUtils.getRealPassword(password);
        User user = userService.findUserByUserName(userName);
        if (user == null) {
            return Result.fail(ErrorCode.NO_USER.getCode(), ErrorCode.NO_USER.getMsg());
        }
        User user2 = userService.findUserByPassword(userName, password);
        if (user2 == null) {
            return Result.fail(ErrorCode.ERROR_PASSWORD.getCode(), ErrorCode.ERROR_PASSWORD.getMsg());
        }
        String token = JWTUtils.createToken(user.getId());
        redisTemplate.opsForValue().set("TOKEN_" + token, JSON.toJSONString(user));
        return Result.success(token);
    }


}
