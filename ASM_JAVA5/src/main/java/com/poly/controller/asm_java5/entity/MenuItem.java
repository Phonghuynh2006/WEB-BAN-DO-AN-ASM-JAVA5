package com.poly.controller.asm_java5.entity;

import jakarta.persistence.*;   // 👈 IMPORT ĐỦ TẤT CẢ JPA
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "menu_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuItem {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @Column(name = "menu_type_id")
        private Integer menuTypeId;

        private String name;

        @Column(name = "image_url")
        private String imageUrl;

        private String size;

        private String description;

        private BigDecimal price;

        @Column(name = "is_available")
        private Boolean isAvailable;
}
