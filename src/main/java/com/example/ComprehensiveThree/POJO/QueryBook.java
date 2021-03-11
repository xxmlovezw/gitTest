package com.example.ComprehensiveThree.POJO;

public class QueryBook {
   private String bookName;
   private String author;
   private String publishCompany;
   private int numOnLibrary;

    public QueryBook() {
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

    public String getPublishCompany() {
        return publishCompany;
    }

    public void setPublishCompany(String publishCompany) {
        this.publishCompany = publishCompany;
    }

    public int getNumOnLibrary() {
        return numOnLibrary;
    }

    public void setNumOnLibrary(int numOnLibrary) {
        this.numOnLibrary = numOnLibrary;
    }
}
