package com.example.hospital.controller;

import com.example.hospital.service.LoginService;
import com.example.hospital.vo.Result;
import com.example.hospital.vo.params.LoginParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("login")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping()
    public Result login(@RequestBody LoginParam loginParam){
        return loginService.login(loginParam);
    }

}
