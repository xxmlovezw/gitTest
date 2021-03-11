package com.example.ComprehensiveThree.Controller;


import com.example.ComprehensiveThree.Domain.BookAdmin;
import com.example.ComprehensiveThree.Service.BookAdminService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping(path = "/bookAdmin")
@Controller

public class BookAdminController {
    @Autowired
    private BookAdminService bookAdminService;
    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String addBookAdmin( BookAdmin bookAdmin){
        System.out.println(bookAdmin.getAdId());
        System.out.println(bookAdmin.getAdEmail());
        System.out.println(bookAdmin.getAdName());
        bookAdminService.AddBookAdmin(bookAdmin);
        return "ok";
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteBookAdmin(String adminId){
        bookAdminService.deleteBookAdminByAdId(adminId);
        return "ok";
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public String updateBookAdmin(BookAdmin bookAdmin){
        final BookAdmin bookAdminByAdIdAndPassword = bookAdminService.findBookAdminByAdIdAndAdPassword(bookAdmin.getAdId(), bookAdmin.getAdPassword());
        if (bookAdminByAdIdAndPassword == null)
            return "error";
        final BookAdmin bookAdmin1 = compareAndRepeat(bookAdminByAdIdAndPassword, bookAdmin);
        bookAdminService.updateBookAdmin(bookAdmin1);
        return "ok";
    }

    @RequestMapping(path = "/alterPassword",method = RequestMethod.PUT)
    @ResponseBody
    public String updateBookAdminBySystemAdmin(BookAdmin bookAdmin,String password){
        final BookAdmin admin = bookAdminService.findBookAdminByAdIdAndAdPassword(bookAdmin.getAdId(), bookAdmin.getAdPassword());
        if (admin == null)
            return "error";
        final BookAdmin bookAdmin1 = compareAndRepeat(admin, bookAdmin);
        if (password != null)
            bookAdmin1.setAdPassword(password);
        bookAdminService.updateBookAdmin(bookAdmin1);
        return "ok";
    }

    @RequestMapping(method = RequestMethod.GET,path = "/{adName}")
    @ResponseBody
    public List<BookAdmin> getBookAdmins(@PathVariable String adName)throws Exception{
        final List<BookAdmin> bookAdminsByAdName = bookAdminService.findBookAdminsByAdName(adName);
       return bookAdminsByAdName;

    }

    private BookAdmin compareAndRepeat(BookAdmin old, BookAdmin now){
        if (now.getAdName() != null)
            old.setAdName(now.getAdName());
        if (now.getAdEmail() != null)
            old.setAdEmail(now.getAdEmail());
        if (now.getAdPhone() != null)
            old.setAdPhone(now.getAdPhone());
        return old;
    }
}
