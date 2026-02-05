package com.poly.controller.asm_java5.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Order_Customers")
public class OrderCustomer {

    @Id
    @Column(name = "order_id")
    private Integer orderId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "order_id")
    private Order order;

    private String customerName;
    private String phone;
    private String address;

    // getters & setters
}
