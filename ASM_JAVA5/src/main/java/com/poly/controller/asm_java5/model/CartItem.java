package com.poly.controller.asm_java5.model;

import com.poly.controller.asm_java5.entity.MenuItem;

public class CartItem {

    private MenuItem item;
    private int quantity;

    public CartItem(MenuItem item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public MenuItem getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // ✅ THÊM CÁI NÀY
    public double getTotalPrice() {
        return item.getPrice() * quantity;
    }
}
