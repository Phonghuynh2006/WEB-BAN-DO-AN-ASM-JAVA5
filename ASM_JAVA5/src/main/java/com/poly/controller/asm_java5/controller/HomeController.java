package com.poly.controller.asm_java5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;


@Controller
public class HomeController {

//    @GetMapping("/")
//    public String home() {
//        return "home/index"; // trỏ tới templates/home/index.html
//    }



        @Autowired
        private MenuItemService menuItemService;

        @GetMapping("/")
        public String home(Model model) {
            model.addAttribute("bestSellers", menuItemService.getBestSeller());
            return "home/index";
        }


}
