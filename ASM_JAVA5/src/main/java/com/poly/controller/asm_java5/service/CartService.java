package com.poly.controller.asm_java5.service;

import com.poly.controller.asm_java5.entity.MenuItem;
import com.poly.controller.asm_java5.model.CartItem;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CartService {

    private static final String CART_SESSION_KEY = "cart";

    @SuppressWarnings("unchecked")
    public Map<Integer, CartItem> getCart(HttpSession session) {
        Map<Integer, CartItem> cart =
                (Map<Integer, CartItem>) session.getAttribute(CART_SESSION_KEY);

        if (cart == null) {
            cart = new HashMap<>();
            session.setAttribute(CART_SESSION_KEY, cart);
        }
        return cart;
    }

    public void addItem(HttpSession session, MenuItem item, int quantity) {
        Map<Integer, CartItem> cart = getCart(session);
        Integer itemId = item.getItemId(); // ✅ FIX

        if (cart.containsKey(itemId)) {
            CartItem cartItem = cart.get(itemId);
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        } else {
            cart.put(itemId, new CartItem(item, quantity));
        }
    }

    public void removeItem(HttpSession session, Integer itemId) {
        getCart(session).remove(itemId);
    }

    public void clear(HttpSession session) {
        session.removeAttribute(CART_SESSION_KEY);
    }

    public double getTotalAmount(HttpSession session) {
        return getCart(session).values()
                .stream()
                .mapToDouble(CartItem::getTotalPrice)
                .sum();
    }
}
