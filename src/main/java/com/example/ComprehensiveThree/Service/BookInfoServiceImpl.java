package com.example.ComprehensiveThree.Service;

import com.example.ComprehensiveThree.DAO.BookInfoDAO;
import com.example.ComprehensiveThree.Domain.BookInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BookInfoServiceImpl implements BookInfoService {
   @Autowired
    private BookInfoDAO bookInfoDAO;


    @Override
    public BookInfo addBook(BookInfo bookInfo) {
        return bookInfoDAO.save(bookInfo);
    }

    @Override
    public BookInfo updateBook(BookInfo bookInfo) {
        return bookInfoDAO.save(bookInfo);
    }

    @Transactional
    @Override
    public boolean deleteBookInfoByBookId(String bookId) {
        if (bookInfoDAO.deleteBookInfoByBookId(bookId) == 1)
            return true;
        return false;
    }

    @Override
    public BookInfo findBookInfoByBookId(String bookId) {
        return bookInfoDAO.findBookInfoByBookId(bookId);
    }

    @Override
    public List<BookInfo> findBookInfosByBookNameAndState(String bookName, int state) {

        return bookInfoDAO.findBookInfosByBookNameAndState(bookName,state);
    }

    @Override
    public List<BookInfo> findBookInfosByAuthorAndState(String author, int state) {
        return bookInfoDAO.findBookInfosByAuthorAndState(author,state);
    }

    @Override
    public List<BookInfo> findBookInfosByBookName(String bookName) {
        return bookInfoDAO.findBookInfosByBookName(bookName);
    }
}
