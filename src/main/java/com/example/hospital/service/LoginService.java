package com.example.hospital.service;

import com.example.hospital.entity.User;
import com.example.hospital.vo.Result;
import com.example.hospital.vo.params.LoginParam;

public interface LoginService {
    Result logout(String token);

    Result login(LoginParam loginParam);

    Result register(LoginParam loginParam);

    public User getUserbyToken(String token);


}
