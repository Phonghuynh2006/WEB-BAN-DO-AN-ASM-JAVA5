package com.poly.controller.asm_java5.service;

import com.poly.controller.asm_java5.entity.User;
import com.poly.controller.asm_java5.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    // ===== LOGIN =====
    public User login(String emailOrPhone, String password) {
        Optional<User> userOpt =
                userRepository.findByEmailOrPhone(emailOrPhone, emailOrPhone);

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    // ===== REGISTER =====
    public void register(String fullName, String email, String phone, String password) {

        if (userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("Email đã tồn tại");
        }

<<<<<<< HEAD
        if (userRepository.findByPhone(phone).isPresent()) {
            throw new RuntimeException("Số điện thoại đã tồn tại");
        }
=======
        return user.orElse(null);
    }

    // REGISTER
    public boolean register(User user) {
        if (user.getEmail() != null && repo.existsByEmail((String) user.getEmail())) return false;
        if (user.getPhone() != null && repo.existsByPhone((String) user.getPhone())) return false;
>>>>>>> origin/AD

        User user = new User();
        user.setFullName(fullName);
        user.setEmail(email);
        user.setPhone(phone);
        user.setPassword(password);
        user.setRole("USER");

        userRepository.save(user);
    }
}
