package com.example.ComprehensiveThree.Service;

import com.example.ComprehensiveThree.Domain.UserInfo;

import java.util.List;

//  用户信息有三件事
public interface UserInfoService {
    //  通过ID  查找用户信息
    UserInfo findById(String userId);
    //添加用户信息
    UserInfo addUserInfo(UserInfo userInfo);
    //修改用户信息
    UserInfo updateUserInfo(UserInfo userInfo);
    //  删除用户信息
    boolean deleteUserInfoByUserId(String userId);

    // 查看用户是否存在

    boolean exists(String userId);

    List<UserInfo> findUserInfosByIdOrNameOrMajor(String query);
}
