package com.poly.controller.asm_java5.service;

import com.poly.controller.asm_java5.entity.User;
import com.poly.controller.asm_java5.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository repo;

    // LOGIN
    public User login(String username, String password) {
        Optional<User> user;

        if (username.contains("@")) {
            user = repo.findByEmailAndPassword(username, password);
        } else {
            user = repo.findByPhoneAndPassword(username, password);
        }

        return user.orElse(null);
    }

    // REGISTER
    public boolean register(User user) {
        if (user.getEmail() != null && repo.existsByEmail((String) user.getEmail())) return false;
        if (user.getPhone() != null && repo.existsByPhone((String) user.getPhone())) return false;

        user.setRole("USER");
        repo.save(user);
        return true;
    }
}
