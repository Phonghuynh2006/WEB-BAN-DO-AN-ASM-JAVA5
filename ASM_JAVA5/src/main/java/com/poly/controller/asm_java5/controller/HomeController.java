package com.poly.controller.asm_java5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home/index"; // trỏ tới templates/home/index.html
    }
}
