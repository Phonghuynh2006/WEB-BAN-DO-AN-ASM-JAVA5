package com.poly.controller.asm_java5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cart")

public class CartController {


        @GetMapping("/cart")
        public String cart() {
            return "cart/cart"; // templates/auth/auth.html
        }



}
