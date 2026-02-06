package com.poly.controller.asm_java5.controller;

import com.poly.controller.asm_java5.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

//    @Autowired
//    private MenuService menuService;

//    @GetMapping({"/", "/home"})
//    public String home(Model model) {
//        model.addAttribute("menus", menuService.findAllMenus());
//        return "home/index";
//    }


    @Autowired
    private MenuService menuService;

    @GetMapping({"/", "/home"})
    public String home(Model model) {

        // menu
        model.addAttribute("menus", menuService.findAllMenus());

        // 🔥 QUAN TRỌNG: sản phẩm bán chạy
        model.addAttribute("bestSellers", menuService.findBestSellers());

        return "home/index";
    }


}
