package com.example.ComprehensiveThree.Domain;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "bookinfo")
@ApiModel(value = "书籍信息")
public class BookInfo implements Serializable {
    @Id
    @Column(name = "bookid",nullable = false)
    @ApiModelProperty(value = "书籍ID")
   private String bookId;
    @Column(name = "bookname",nullable = false)
   private String bookName;
    @Column(name = "author",nullable = false)
   private String author;
    @Column(name = "translator")
   private String translator;
    @Column(name = "price",nullable = false)
   private int price;
   @Column(name = "isbncode",nullable =false)
    private String ISBNCode;
   @Column(name = "comeuptime",nullable = false)
    private Date comeUpTime;
   @Column(name = "publishcompany",nullable = false)
    private String publishCompany;
   @Column(name = "state",nullable = false)
    private int state; //  0  借出  1   在库
   @Column(name = "enteringmen",nullable = false)
    private String enteringMen;
   @Column(name = "enteringdate",nullable = false)
   private Date enteringDate;

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTranslator() {
        return translator;
    }

    public void setTranslator(String translator) {
        this.translator = translator;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getISBNCode() {
        return ISBNCode;
    }

    public void setISBNCode(String ISBNCode) {
        this.ISBNCode = ISBNCode;
    }

    public Date getComeUpTime() {
        return comeUpTime;
    }

    public void setComeUpTime(Date comeUpTime) {
        this.comeUpTime = comeUpTime;
    }

    public String getPublishCompany() {
        return publishCompany;
    }

    public void setPublishCompany(String publishCompany) {
        this.publishCompany = publishCompany;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getEnteringMen() {
        return enteringMen;
    }

    public void setEnteringMen(String enteringMen) {
        this.enteringMen = enteringMen;
    }

    public Date getEnteringDate() {
        return enteringDate;
    }

    public void setEnteringDate(Date enteringDate) {
        this.enteringDate = enteringDate;
    }
}
