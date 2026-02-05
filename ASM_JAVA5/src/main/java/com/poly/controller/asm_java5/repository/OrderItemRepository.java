package com.poly.controller.asm_java5.repository;

import com.poly.controller.asm_java5.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

    List<OrderItem> findByOrder_OrderId(Integer orderId);
}
