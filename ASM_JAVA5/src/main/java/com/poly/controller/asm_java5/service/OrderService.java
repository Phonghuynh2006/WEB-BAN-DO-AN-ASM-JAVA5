package com.poly.controller.asm_java5.service;

import com.poly.controller.asm_java5.entity.*;
import com.poly.controller.asm_java5.model.CartItem;
import com.poly.controller.asm_java5.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderCustomerRepository orderCustomerRepository;

    // ✅ METHOD CHUẨN – KHỚP CheckoutController
    public Order createOrderFromCart(
            Map<Integer, CartItem> cart,
            String customerName,
            String phone,
            String address
    ) {

        // 1️⃣ tạo Order
        Order order = new Order();
        order.setOrderType("ONLINE");
        order.setPaymentMethod("COD");
        order.setNote("");
        order.setCreatedAt(LocalDateTime.now());

        double total = cart.values()
                .stream()
                .mapToDouble(CartItem::getTotalPrice)
                .sum();

        order.setTotalAmount(total);
        order = orderRepository.save(order);

        // 2️⃣ OrderCustomer (KHÔNG dùng User)
        OrderCustomer customer = new OrderCustomer();
        customer.setOrder(order);
        customer.setCustomerName(customerName);
        customer.setPhone(phone);
        customer.setAddress(address);
        orderCustomerRepository.save(customer);

        // 3️⃣ OrderItem
        for (CartItem cartItem : cart.values()) {
            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setMenuItem(cartItem.getItem());
            item.setQuantity(cartItem.getQuantity());
            item.setPrice(cartItem.getItem().getPrice());
            orderItemRepository.save(item);
        }

        return order;
    }
}
