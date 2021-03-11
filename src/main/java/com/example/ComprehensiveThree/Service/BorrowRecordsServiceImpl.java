package com.example.ComprehensiveThree.Service;


import com.example.ComprehensiveThree.DAO.BorrowRecordsDAO;
import com.example.ComprehensiveThree.Domain.BorrowRecords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowRecordsServiceImpl implements BorrowRecordsService{
    @Autowired
    private BorrowRecordsDAO borrowRecordsDAO;

    @Override
    public BorrowRecords addBorrowRecords(BorrowRecords borrowRecords) {
        return borrowRecordsDAO.save(borrowRecords);
    }

    @Override
    public BorrowRecords updateBorrowRecords(BorrowRecords borrowRecords) {
        return borrowRecordsDAO.save(borrowRecords);
    }

    @Override
    public List<BorrowRecords> findBorrowRecordsOnBorrowing(String userId,int state) {
        return borrowRecordsDAO.findBorrowRecordsByUserIdAndState(userId,state);
    }

    @Override
    public List<BorrowRecords> findBorrowRecordsOnBorrowed(String userId) {
        return borrowRecordsDAO.findBorrowRecordsByUserId(userId);
    }

    @Override
    public BorrowRecords findBorrowRecordsByBookId(String bookId) {
        return borrowRecordsDAO.findBorrowRecordsByBookId(bookId);
    }

    @Override
    public List<BorrowRecords> findBorrowRecordsByBookName(String bookName) {
        return borrowRecordsDAO.findBorrowRecordsByBookName(bookName);

    }
}
