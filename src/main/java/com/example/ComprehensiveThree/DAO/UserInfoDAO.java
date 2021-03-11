package com.example.ComprehensiveThree.DAO;

import com.example.ComprehensiveThree.Domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoDAO extends JpaRepository<UserInfo,String> {
    UserInfo findUserInfoByUserId(String userId);
    int deleteUserInfoByUserId(String userId);
    boolean existsUserInfoByUserId(String userId);


}
