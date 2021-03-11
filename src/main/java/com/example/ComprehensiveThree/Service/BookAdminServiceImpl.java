package com.example.ComprehensiveThree.Service;


import com.example.ComprehensiveThree.DAO.BookAdminDAO;
import com.example.ComprehensiveThree.Domain.BookAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BookAdminServiceImpl  implements BookAdminService{
   @Autowired
    private BookAdminDAO bookAdminDAO;

    @Override
    public BookAdmin updateBookAdmin(BookAdmin bookAdmin) {

        return bookAdminDAO.save(bookAdmin);
    }

    @Override
    public BookAdmin AddBookAdmin(BookAdmin bookAdmin) {
        return bookAdminDAO.save(bookAdmin);
    }

    @Transactional
    @Override
    public boolean deleteBookAdminByAdId(String adminId) {
        if (bookAdminDAO.deleteBookAdminByAdId(adminId) == 1)
            return true;
        return false;
    }

    @Override
    public BookAdmin findBookAdminByAdIdAndAdPassword(String adminId,String password) {
        return bookAdminDAO.findBookAdminByAdIdAndAdPassword(adminId,password);
    }

    @Override
    public List<BookAdmin> findBookAdminsByAdName(String adName) {
        return bookAdminDAO.findBookAdminsByAdName(adName);
    }
}
