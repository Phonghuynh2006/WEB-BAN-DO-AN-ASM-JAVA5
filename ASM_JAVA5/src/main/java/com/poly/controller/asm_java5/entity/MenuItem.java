package com.poly.controller.asm_java5.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Menu_Items")
public class MenuItem {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "item_id")
        private Integer itemId;

        @ManyToOne
        @JoinColumn(name = "menu_id", nullable = false)
        private Menu menu;

        @Column(name = "item_name", nullable = false)
        private String itemName;

        private String description;
        private String image;

        private String size;
        private Double price;

        private Boolean status;

        // getters & setters
}
