package com.example.ComprehensiveThree.Service;

import com.example.ComprehensiveThree.Domain.BookInfo;

import java.util.List;

public interface BookInfoService {

    BookInfo addBook(BookInfo bookInfo);
    BookInfo updateBook(BookInfo bookInfo);
    boolean deleteBookInfoByBookId(String bookId);
    BookInfo findBookInfoByBookId(String bookId);
    List<BookInfo> findBookInfosByBookNameAndState(String bookName,int state);
    List<BookInfo> findBookInfosByAuthorAndState(String author, int state);
    List<BookInfo> findBookInfosByBookName(String bookName);
}
