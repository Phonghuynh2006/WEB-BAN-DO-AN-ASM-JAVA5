package com.poly.controller.asm_java5.service;

import com.poly.controller.asm_java5.entity.*;
import com.poly.controller.asm_java5.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderCustomerRepository orderCustomerRepository;

    public Order createOrder(
            String orderType,
            String paymentMethod,
            String note,
            Double totalAmount,
            OrderCustomer customer,
            List<OrderItem> items
    ) {

        Order order = new Order();
        order.setOrderType(orderType);
        order.setPaymentMethod(paymentMethod);
        order.setNote(note);
        order.setTotalAmount(totalAmount);
        order.setCreatedAt(new Date());

        order = orderRepository.save(order);

        // customer
        customer.setOrder(order);
        orderCustomerRepository.save(customer);

        // items
        for (OrderItem item : items) {
            item.setOrder(order);
            orderItemRepository.save(item);
        }

        return order;
    }
}
