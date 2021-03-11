package com.example.ComprehensiveThree.Service;


import com.example.ComprehensiveThree.DAO.SystemAdminDAO;
import com.example.ComprehensiveThree.Domain.SystemAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemAdminServiceImpl implements SystemAdminService{
    @Autowired
    private SystemAdminDAO systemAdminDAO;

    @Override
    public SystemAdmin findSystemAdminByAdminIdAndPassword(String adminId, String password) {

        return systemAdminDAO.findSystemAdminByAdminIdAndAdminPassword(adminId,password);
    }

    @Override
    public SystemAdmin findSystemAdminByAdminId(String adminId) {
        return systemAdminDAO.findSystemAdminByAdminId(adminId);
    }

    @Override
    public SystemAdmin updateSystemAdmin(SystemAdmin systemAdmin) {
        return systemAdminDAO.save(systemAdmin);
    }
}
