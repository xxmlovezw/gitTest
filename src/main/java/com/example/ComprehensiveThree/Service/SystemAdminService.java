package com.example.ComprehensiveThree.Service;

import com.example.ComprehensiveThree.Domain.SystemAdmin;

public interface SystemAdminService {

    SystemAdmin findSystemAdminByAdminIdAndPassword(String adminId,String password);
    SystemAdmin findSystemAdminByAdminId(String adminId);
    SystemAdmin updateSystemAdmin(SystemAdmin systemAdmin);
}
