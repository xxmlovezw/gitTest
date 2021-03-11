package com.example.ComprehensiveThree.Service;

import com.example.ComprehensiveThree.Domain.BookAdmin;

import java.util.List;

public interface BookAdminService {
    BookAdmin updateBookAdmin(BookAdmin bookAdmin);
    BookAdmin AddBookAdmin(BookAdmin bookAdmin);
    boolean deleteBookAdminByAdId(String adminId);
    BookAdmin findBookAdminByAdIdAndAdPassword(String adminId, String password);
    List<BookAdmin> findBookAdminsByAdName(String adName);

}
