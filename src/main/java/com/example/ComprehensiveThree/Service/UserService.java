package com.example.ComprehensiveThree.Service;

import com.example.ComprehensiveThree.Domain.User;


public interface UserService {
    //   查询id  和密码是否匹配
    User exists(String userId, String password);
    //   通过id  删除用户
    boolean deleteUserByUserId(String userId);
    //   添加新读者
    User addUser(User user);
    //   修改密码
    User updatePassword(User user);
    User findUserByUserName(String userName);
    User findUserByUserId(String userId);
}
