package com.example.ComprehensiveThree.Domain;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user")
public class User implements Serializable {
    @Id
    @Column(name = "userid",nullable = false)
   private String userId;//  用户ID（学号）
    @Column(name = "username",nullable = false)
   private String userName;//  用户名
    @Column(name = "password",nullable = false)
   private String password;// 密码

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
