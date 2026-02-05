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

        public Integer getItemId() {
                return itemId;
        }

        private String description;
        private String image;

        public void setItemId(Integer itemId) {
                this.itemId = itemId;
        }

        private String size;

        public void setMenu(Menu menu) {
                this.menu = menu;
        }

        public Menu getMenu() {
                return menu;
        }

        private Double price;

        private Boolean status;

        // getters & setters
}
