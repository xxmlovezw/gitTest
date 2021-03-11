package com.example.ComprehensiveThree.Controller;


import com.example.ComprehensiveThree.Domain.SystemAdmin;
import com.example.ComprehensiveThree.Service.SystemAdminService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/systemAdmin")
public class SystemAdminController {
    @Autowired
    private SystemAdminService systemAdminService;
    @Autowired
    private ObjectMapper objectMapper;
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public String updateSystemAdmin(SystemAdmin systemAdmin, String password){
        final SystemAdmin systemAdminByAdminIdAndPassword = systemAdminService.findSystemAdminByAdminIdAndPassword(systemAdmin.getAdminId(), systemAdmin.getAdminPassword());
        if (systemAdminByAdminIdAndPassword == null)
            return "error";
        final SystemAdmin systemAdmin1 = compareAndRepeat(systemAdminByAdminIdAndPassword, systemAdmin);
        System.out.println(password != null ? 0:1);
        if(password != null)
            systemAdmin1.setAdminPassword(password);
        systemAdminService.updateSystemAdmin(systemAdmin1);
        return "ok";
    }

    private SystemAdmin compareAndRepeat(SystemAdmin old,SystemAdmin now){
        if (now.getAdminEmail() != null)
            old.setAdminEmail(now.getAdminEmail());
        if (now.getAdminPhone() != null)
            old.setAdminPhone(now.getAdminPhone());
        return old;
    }
}
