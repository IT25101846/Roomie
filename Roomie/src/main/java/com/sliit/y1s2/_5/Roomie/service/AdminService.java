package com.sliit.y1s2._5.Roomie.service;

import com.sliit.y1s2._5.Roomie.model.Admin;
import com.sliit.y1s2._5.Roomie.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired private AdminRepository adminRepository;

    public Optional<Admin> login(String username, String password) {
        return adminRepository.findByUsername(username)
                .filter(a -> a.getPassword().equals(password));
    }

    public String createAdmin(Admin admin) {
        if (adminRepository.findByUsername(admin.getUsername()).isPresent())
            return "ERROR: Username already exists.";
        admin.setAdminId(adminRepository.generateId());
        admin.setCreatedAt(LocalDate.now().toString());
        adminRepository.save(admin);
        return "SUCCESS";
    }

    public List<Admin> getAllAdmins()              { return adminRepository.findAll(); }
    public Optional<Admin> getById(String id)      { return adminRepository.findById(id); }

    public String updateAdmin(Admin admin) {
        if (adminRepository.findById(admin.getAdminId()).isEmpty()) return "ERROR: Admin not found.";
        adminRepository.update(admin);
        return "SUCCESS";
    }

    public String deleteAdmin(String id) {
        if (adminRepository.findById(id).isEmpty()) return "ERROR: Admin not found.";
        adminRepository.delete(id);
        return "SUCCESS";
    }
}
