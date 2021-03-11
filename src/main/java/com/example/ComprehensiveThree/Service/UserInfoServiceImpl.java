package com.example.ComprehensiveThree.Service;

import com.example.ComprehensiveThree.DAO.UserInfoDAO;
import com.example.ComprehensiveThree.Domain.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoDAO userInfoDAO;

    @Override
    public UserInfo findById(String userId) {
         return userInfoDAO.findById(userId).get();
    }

    @Override
    public UserInfo addUserInfo(UserInfo userInfo) {
       return userInfoDAO.save(userInfo);
    }

    @Override
    public UserInfo updateUserInfo(UserInfo userInfo) {
        return  userInfoDAO.save(userInfo);
    }

    @Transactional
    @Override
    public boolean deleteUserInfoByUserId(String userId) {
        if(userInfoDAO.deleteUserInfoByUserId(userId) == 1)
            return true;
        return false;
    }

    @Override
    public boolean exists(String userId) {
        return userInfoDAO.existsUserInfoByUserId(userId);
    }

    @Override
    public List<UserInfo> findUserInfosByIdOrNameOrMajor(String query) {

        return null;
    }
}
