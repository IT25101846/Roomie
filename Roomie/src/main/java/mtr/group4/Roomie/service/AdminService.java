package mtr.group4.Roomie.service;

import mtr.group4.Roomie.model.Admin;
import mtr.group4.Roomie.repository.AdminRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {

    private final AdminRepository adminRepo;

    public AdminService(AdminRepository adminRepo) {
        this.adminRepo = adminRepo;
    }

    public Optional<Admin> login(String email, String password) {
        Optional<Admin> opt = adminRepo.findByEmail(email);
        if (opt.isPresent() && opt.get().authenticate(password)) return opt;
        return Optional.empty();
    }
}
