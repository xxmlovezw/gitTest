package com.example.ComprehensiveThree.DAO;

import com.example.ComprehensiveThree.Domain.BookInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookInfoDAO extends JpaRepository<BookInfo,String> {

    int deleteBookInfoByBookId(String bookId);
    BookInfo findBookInfoByBookId(String bookId);
    List<BookInfo> findBookInfosByBookNameAndState(String bookName, int state);
    List<BookInfo> findBookInfosByAuthorAndState(String author, int state);
    List<BookInfo> findBookInfosByBookName(String bookName);
}
