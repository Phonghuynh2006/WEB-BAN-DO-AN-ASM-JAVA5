package com.poly.controller.asm_java5.controller;

import com.poly.controller.asm_java5.entity.User;
import com.poly.controller.asm_java5.service.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("/login")
    public String loginForm() {
        return "auth/auth";
    }

    @PostMapping("/login")
    public String login(
            @RequestParam String emailOrPhone,
            @RequestParam String password,
            HttpSession session,
            Model model
    ) {
        User user = authService.login(emailOrPhone, password);

        if (user != null) {
            session.setAttribute("user", user);
            return "redirect:/home";
        }

        model.addAttribute("error", "Sai tài khoản hoặc mật khẩu");
        return "auth/auth";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/auth/login";
    }
}
