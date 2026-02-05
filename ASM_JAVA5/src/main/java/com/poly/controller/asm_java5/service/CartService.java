package com.poly.controller.asm_java5.service;

import com.poly.controller.asm_java5.entity.MenuItem;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CartService {

    private Map<Integer, Integer> cart = new HashMap<>();

    public void addItem(MenuItem item, int quantity) {
        cart.put(item.getItemId(),
                cart.getOrDefault(item.getItemId(), 0) + quantity);
    }

    public void removeItem(Integer itemId) {
        cart.remove(itemId);
    }

    public Map<Integer, Integer> getCart() {
        return cart;
    }

    public void clear() {
        cart.clear();
    }
}
