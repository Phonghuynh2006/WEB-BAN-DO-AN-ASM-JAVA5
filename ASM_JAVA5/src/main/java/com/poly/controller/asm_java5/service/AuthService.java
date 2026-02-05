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
}
