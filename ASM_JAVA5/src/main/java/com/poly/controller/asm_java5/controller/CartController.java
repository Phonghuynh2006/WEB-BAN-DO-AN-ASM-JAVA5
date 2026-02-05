package com.poly.controller.asm_java5.controller;

import com.poly.controller.asm_java5.entity.MenuItem;
import com.poly.controller.asm_java5.service.CartService;
import com.poly.controller.asm_java5.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private MenuService menuService;

    @GetMapping
    public String viewCart(Model model) {
        model.addAttribute("cart", cartService.getCart());
        return "cart/cart";
    }

    @PostMapping("/add")
    public String add(@RequestParam Integer itemId,
                      @RequestParam(defaultValue = "1") int quantity) {

        MenuItem item = menuService.findItemById(itemId);
        cartService.addItem(item, quantity);
        return "redirect:/cart";
    }

    @GetMapping("/remove/{itemId}")
    public String remove(@PathVariable Integer itemId) {
        cartService.removeItem(itemId);
        return "redirect:/cart";
    }
}
