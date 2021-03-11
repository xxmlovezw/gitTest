package com.example.ComprehensiveThree.Domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "borrowrecords")
public class BorrowRecords implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "borrowid")
    private int borrowId;// 借阅记录ID
    @Column(name = "userid",nullable = false)
    private String userId;// 用户ID
    @Column(name = "bookid",nullable = false)
    private String bookId;//图书ID
    @Column(name = "bookname",nullable = false)
    private String bookName;//书名
    @Column(name = "borrowtime",nullable = false)
    private Date borrowTime;//  结束时间
    @Column(name = "shouldtime",nullable = false)
    private Date shouldTime;// 预归还时间
    @Column(name = "returntime")
    private Date returnTime;//  实际归还时间
    @Column(name = "state",nullable = false)
    private int state;//  借阅状态 0 借出  1  归还

    public int getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(int borrowId) {
        this.borrowId = borrowId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

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

    public Date getBorrowTime() {
        return borrowTime;
    }

    public void setBorrowTime(Date borrowTime) {
        this.borrowTime = borrowTime;
    }

    public Date getShouldTime() {
        return shouldTime;
    }

    public void setShouldTime(Date shouldTime) {
        this.shouldTime = shouldTime;
    }

    public Date getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(Date returnTime) {
        this.returnTime = returnTime;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
