package com.example.ComprehensiveThree.Controller;

import com.example.ComprehensiveThree.Domain.BookAdmin;
import com.example.ComprehensiveThree.Domain.SystemAdmin;
import com.example.ComprehensiveThree.Domain.User;
import com.example.ComprehensiveThree.Service.BookAdminService;
import com.example.ComprehensiveThree.Service.SystemAdminService;
import com.example.ComprehensiveThree.Service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/login")
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private BookAdminService bookAdminService;
    @Autowired
    private SystemAdminService systemAdminService;
    @Autowired
    private ObjectMapper objectMapper;
    @RequestMapping
    @ResponseBody
    public String login(String type, String id, String password)throws Exception{
        System.out.println(id+"---"+password);
        String answer = null;
        if ("1".equals(type)){
            final User exists = userService.exists(id, password);
            answer = objectMapper.writeValueAsString(exists);
        }else if ("2".equals(type)){
            final BookAdmin bookAdminByAdIdAndPassword = bookAdminService.findBookAdminByAdIdAndAdPassword(id, password);
            answer = objectMapper.writeValueAsString(bookAdminByAdIdAndPassword);
        }else if("3".equals(type)){
            final SystemAdmin systemAdminByAdminIdAndPassword = systemAdminService.findSystemAdminByAdminIdAndPassword(id, password);
            answer = objectMapper.writeValueAsString(systemAdminByAdminIdAndPassword);
        }
        System.out.println(answer);
        return answer;
    }
}
