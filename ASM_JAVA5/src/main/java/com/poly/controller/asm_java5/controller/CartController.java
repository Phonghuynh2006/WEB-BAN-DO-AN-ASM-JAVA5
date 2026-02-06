package com.poly.controller.asm_java5.controller;

import com.poly.controller.asm_java5.entity.MenuItem;
import com.poly.controller.asm_java5.service.CartService;
import com.poly.controller.asm_java5.service.MenuService;
import jakarta.servlet.http.HttpSession;
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
    public String viewCart(HttpSession session, Model model) {
        model.addAttribute("cart", cartService.getCart(session));
        model.addAttribute("total", cartService.getTotalAmount(session));
        return "cart/cart";
    }

    @PostMapping("/add")
    public String add(HttpSession session,
                      @RequestParam Integer itemId,
                      @RequestParam(defaultValue = "1") int quantity) {

        MenuItem item = menuService.findItemById(itemId);
        cartService.addItem(session, item, quantity);
        return "redirect:/cart";
    }

    @GetMapping("/remove/{itemId}")
    public String remove(HttpSession session,
                         @PathVariable Integer itemId) {

        cartService.removeItem(session, itemId);
        return "redirect:/cart";
    }
}
