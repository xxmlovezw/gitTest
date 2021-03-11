package com.example.ComprehensiveThree.Domain;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "systemadmin")
public class SystemAdmin implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "adminid",nullable = false)
    private String adminId;//  系统管理员ID
    @Column(name = "adminname",nullable = false)
    private String adminName;// 系统管理员名
    @Column(name = "adminpassword",nullable = false)
    private String adminPassword;// 用户密码
    @Column(name = "adminphone",nullable = false)
    private String adminPhone;//  联系电话
    @Column(name = "adminemail")
    private String adminEmail;//  邮箱

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public String getAdminPhone() {
        return adminPhone;
    }

    public void setAdminPhone(String adminPhone) {
        this.adminPhone = adminPhone;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }
}
