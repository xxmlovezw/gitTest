package com.example.ComprehensiveThree.Service;


import com.example.ComprehensiveThree.DAO.UserDAO;
import com.example.ComprehensiveThree.Domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDAO userDAO;

    @Override
    public User exists(String userId, String password) {
        return userDAO.findUserByUserIdAndPassword(userId,password);
    }

    @Transactional
    @Override
    public boolean deleteUserByUserId(String userId) {
        if (userDAO.deleteUserByUserId(userId) == 1)
            return true;
        return false;

    }

    @Override
    public User addUser(User user) {
        return userDAO.save(user);
    }

    @Override
    public User updatePassword(User user) {
        return userDAO.save(user);
    }

    @Override
    public User findUserByUserName(String userName) {
        return userDAO.findUserByUserName(userName);
    }

    @Override
    public User findUserByUserId(String userId) {
        return userDAO.findUserByUserId(userId);
    }
}
