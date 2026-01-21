package com.poly.controller.asm_java5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")

public class DetailController {


        @GetMapping("/detail")
        public String detail() {
            return "product/detail"; // templates/auth/auth.html
        }




}
