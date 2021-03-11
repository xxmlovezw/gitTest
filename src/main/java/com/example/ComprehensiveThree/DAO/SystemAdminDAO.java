package com.example.ComprehensiveThree.DAO;

import com.example.ComprehensiveThree.Domain.SystemAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemAdminDAO extends JpaRepository<SystemAdmin,String> {
    SystemAdmin findSystemAdminByAdminIdAndAdminPassword(String adminId,String password);
    SystemAdmin findSystemAdminByAdminId(String adminId);
}
