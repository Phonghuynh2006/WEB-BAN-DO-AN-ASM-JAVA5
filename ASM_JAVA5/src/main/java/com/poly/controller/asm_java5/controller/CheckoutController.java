package com.poly.controller.asm_java5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {


        @GetMapping("/checkout")
        public String checkout() {
            return "checkout/checkout"; // templates/auth/auth.html
        }



}
