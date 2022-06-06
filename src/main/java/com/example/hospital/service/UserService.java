package com.example.hospital.service;

import com.example.hospital.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.hospital.vo.Result;
import com.example.hospital.vo.params.UserPasswordParam;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2022-05-24
 */
public interface UserService extends IService<User> {

    User findUserByUserName(String userName);

    public void addUser(User user);

    User findUserByPassword(String userName, String password);

    Result showNowUser();

    Result updateUserPassword(UserPasswordParam userPasswordParam);
}
