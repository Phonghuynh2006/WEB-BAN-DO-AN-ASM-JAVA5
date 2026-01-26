package com.poly.controller.asm_java5.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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


    @GetMapping("/login")
    public String login() {
        return "auth/auth"; // templates/auth/auth.html

        @Autowired
        private AuthService authService;

        /* ===== HIỂN THỊ TRANG AUTH (LOGIN + REGISTER) ===== */
        @GetMapping("/login")
        public String showAuthPage () {
            return "auth/auth"; // 1 file auth.html chứa 2 tab
        }

        /* ===== LOGIN ===== */
        @PostMapping("/login")
        public String login (
                @RequestParam("username") String username,
                @RequestParam("password") String password,
                HttpSession session,
                Model model
    ){
            User user = authService.login(username, password);

            if (user == null) {
                model.addAttribute("loginError", "Sai tài khoản hoặc mật khẩu");
                return "auth/auth"; // quay lại đúng tab login
            }

            session.setAttribute("user", user);
            return "redirect:/";
        }

        /* ===== REGISTER ===== */
        @PostMapping("/register")
        public String register (
                @ModelAttribute User user,
                Model model
    ){
            boolean success = authService.register(user);

            if (!success) {
                model.addAttribute("registerError", "Email hoặc SĐT đã tồn tại");
                return "auth/auth"; // quay lại đúng tab register
            }

            return "redirect:/auth/login";
        }

        /* ===== LOGOUT ===== */
        @GetMapping("/logout")
        public String logout (HttpSession session){
            session.invalidate();
            return "redirect:/auth/login";

        }
    }

}
