package com.poly.controller.asm_java5.service;

import com.poly.controller.asm_java5.entity.OrderItem;
import com.poly.controller.asm_java5.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderQueryService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    public List<OrderItem> findItemsByOrder(Integer orderId) {
        return orderItemRepository.findByOrder_OrderId(orderId);
    }
}
