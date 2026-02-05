package com.poly.controller.asm_java5.controller;

import com.poly.controller.asm_java5.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/product")
public class DetailController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        model.addAttribute("item", menuService.findItemById(id));
        return "product/detail";
    }
}
