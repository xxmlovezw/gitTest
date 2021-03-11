package com.example.ComprehensiveThree.Domain;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "bookadmin")
public class BookAdmin implements Serializable {
    @Id
    @Column(name = "adid")
    private String adId;
    @Column(name = "adname",nullable = false)
    private String adName;
    @Column(name = "adpassword",nullable = false)
    private String adPassword;
    @Column(name = "adphone",nullable = false)
    private String adPhone;
    @Column(name = "ademail")
    private String adEmail;

    public String getAdId() {
        return adId;
    }

    public void setAdId(String adId) {
        this.adId = adId;
    }

    public String getAdName() {
        return adName;
    }

    public void setAdName(String adName) {
        this.adName = adName;
    }

    public String getAdPassword() {
        return adPassword;
    }

    public void setAdPassword(String adPassword) {
        this.adPassword = adPassword;
    }

    public String getAdPhone() {
        return adPhone;
    }

    public void setAdPhone(String adPhone) {
        this.adPhone = adPhone;
    }

    public String getAdEmail() {
        return adEmail;
    }

    public void setAdEmail(String adEmail) {
        this.adEmail = adEmail;
    }
}
