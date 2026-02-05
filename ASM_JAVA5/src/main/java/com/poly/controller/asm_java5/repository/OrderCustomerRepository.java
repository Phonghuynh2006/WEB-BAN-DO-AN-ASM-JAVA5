package com.poly.controller.asm_java5.repository;

import com.poly.controller.asm_java5.entity.OrderCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderCustomerRepository extends JpaRepository<OrderCustomer, Integer> {
}
