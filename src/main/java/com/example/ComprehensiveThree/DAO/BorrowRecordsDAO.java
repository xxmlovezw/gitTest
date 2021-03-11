package com.example.ComprehensiveThree.DAO;

import com.example.ComprehensiveThree.Domain.BorrowRecords;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BorrowRecordsDAO extends JpaRepository<BorrowRecords,Integer> {
    BorrowRecords findBorrowRecordsByBookId(String bookId);
    List<BorrowRecords> findBorrowRecordsByUserIdAndState(String userId, int state);
    List<BorrowRecords> findBorrowRecordsByUserId(String userId);
    List<BorrowRecords> findBorrowRecordsByBookName(String bookName);
}
