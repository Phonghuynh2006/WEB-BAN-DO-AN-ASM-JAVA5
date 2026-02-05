package com.poly.controller.asm_java5.controller;

import com.poly.controller.asm_java5.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public String checkoutForm() {
        return "checkout/checkout";
    }

    @PostMapping
    public String checkout() {
        // xử lý lưu Order (sẽ gắn chi tiết theo form HTML của bạn)
        return "redirect:/home";
    }
}
