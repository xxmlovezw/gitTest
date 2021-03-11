package com.example.ComprehensiveThree.DAO;

import com.example.ComprehensiveThree.Domain.BookAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookAdminDAO extends JpaRepository<BookAdmin,String> {
    int deleteBookAdminByAdId(String adminId);
    BookAdmin findBookAdminByAdIdAndAdPassword(String adminId, String password);
    List<BookAdmin> findBookAdminsByAdName(String adName);
}
