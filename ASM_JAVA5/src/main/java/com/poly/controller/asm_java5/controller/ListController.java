package com.poly.controller.asm_java5.controller;

import com.poly.controller.asm_java5.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/product")
public class ListController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/list/{menuId}")
    public String list(@PathVariable Integer menuId, Model model) {
        model.addAttribute("items", menuService.findItemsByMenu(menuId));
        return "product/list";
    }
}
