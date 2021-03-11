package com.example.ComprehensiveThree.DAO;

import com.example.ComprehensiveThree.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<User,String> {
    User findUserByUserIdAndPassword(String userId, String password);
    int deleteUserByUserId(String userId);
    User findUserByUserName(String userName);
    User findUserByUserId(String userId);

}
