package com.poly.controller.asm_java5.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.controller.asm_java5.repository.MenuRepository;

@Controller
@RequestMapping("/product")
public class ListController {

        @GetMapping("/list")
        public String list() {
            return "product/list"; // templates/auth/auth.html
        }


    @Autowired
    MenuRepository menuRepo;

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("menus", menuRepo.findByIsActiveTrue());
        return "product/list";
    }

}
