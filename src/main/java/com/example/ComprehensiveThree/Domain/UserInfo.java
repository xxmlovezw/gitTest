package com.example.ComprehensiveThree.Domain;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "userinfo")
public class UserInfo implements Serializable {
    @Id
    @Column(name = "userid")
    private String userId;//用户ID (学号)
    @Column(name = "Departments",nullable = false)
    private String departments;//  院系
    @Column(name = "Major",nullable = false)
    private String major;//  专业
    @Column(name = "Phone")
    private String phone;// 电话
    @Column(name = "Email")
    private String email;//  邮件
    @Column(name = "Max",nullable = false)
    private int max;//可借最大数量
    @Column(name = "Time",nullable = false)
    private int time;//可借期限
    @Column(name = "Lendednum",nullable = false)
    private int lendNum;// 可借数量

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDepartments() {
        return departments;
    }

    public void setDepartments(String departments) {
        this.departments = departments;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getLendNum() {
        return lendNum;
    }

    public void setLendNum(int lendNum) {
        this.lendNum = lendNum;
    }
}
