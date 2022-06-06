package com.example.hospital.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.hospital.entity.User;
import com.example.hospital.mapper.UserMapper;
import com.example.hospital.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.hospital.utils.PasswordUtils;
import com.example.hospital.utils.UserThreadLocal;
import com.example.hospital.vo.ErrorCode;
import com.example.hospital.vo.Result;
import com.example.hospital.vo.UserVo;
import com.example.hospital.vo.params.UserPasswordParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author author
 * @since 2022-05-24
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User findUserByUserName(String userName) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUserName,userName);
        lambdaQueryWrapper.select(User::getUserName, User::getId,User::getAvatar,User::getNickname,User::getRole);
        return userMapper.selectOne(lambdaQueryWrapper);
    }


    @Override
    public void addUser(User user) {
        userMapper.insert(user);
    }

    @Override
    public User findUserByPassword(String userName, String password) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUserName,userName);
        lambdaQueryWrapper.eq(User::getPassword,password);
        return userMapper.selectOne(lambdaQueryWrapper);

    }

    @Override
    public Result showNowUser() {
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(UserThreadLocal.get(),userVo);
        return Result.success(userVo);
    }

    @Override
    public Result updateUserPassword(UserPasswordParam userPasswordParam) {
        User user = UserThreadLocal.get();
        String oldPassword= PasswordUtils.getRealPassword(userPasswordParam.getOldPassword());
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getId,user.getId()).eq(User::getPassword,oldPassword);
        user=userMapper.selectOne(lambdaQueryWrapper);
        if(user==null){
            return Result.fail(ErrorCode.ERROR_PASSWORD.getCode(),ErrorCode.ERROR_PASSWORD.getMsg());
        }
        String newPassword= PasswordUtils.getRealPassword(userPasswordParam.getNewPassword());
        user.setPassword(newPassword);
        userMapper.updateById(user);
        return Result.success(null);
    }

    public UserVo findUserVoById(Long id) {
        User user=userMapper.selectById(id);
        UserVo userVo=new UserVo();
        BeanUtils.copyProperties(user,userVo);
        return userVo;
    }


}
