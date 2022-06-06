package com.example.hospital.controller;


import com.example.hospital.service.UserService;
import com.example.hospital.vo.Result;
import com.example.hospital.vo.params.UserPasswordParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author author
 * @since 2022-05-24
 */
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public Result showNowUser(){
        return userService.showNowUser();
    }

    @PostMapping("password")
    public Result updatePassword(@RequestBody UserPasswordParam userPasswordParam){
        return userService.updateUserPassword(userPasswordParam);
    }

}
