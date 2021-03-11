package com.example.ComprehensiveThree.Service;

import com.example.ComprehensiveThree.Domain.BorrowRecords;

import java.util.List;

public interface BorrowRecordsService {
    BorrowRecords addBorrowRecords(BorrowRecords borrowRecords);
    BorrowRecords updateBorrowRecords(BorrowRecords borrowRecords);
    List<BorrowRecords> findBorrowRecordsOnBorrowing(String userId, int state);
    List<BorrowRecords> findBorrowRecordsOnBorrowed(String userId);
    BorrowRecords findBorrowRecordsByBookId(String bookId);
    List<BorrowRecords> findBorrowRecordsByBookName(String bookName);
}
