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

        @Column(name = "description")
        private String description;

        @Column(name = "image")
        private String image;

        @Column(name = "size")
        private String size;

        @Column(name = "price")
        private Double price;

        @Column(name = "status")
        private Boolean status;

        // ===== GETTERS & SETTERS =====

        public Integer getItemId() {
                return itemId;
        }

        public void setItemId(Integer itemId) {
                this.itemId = itemId;
        }

        public Menu getMenu() {
                return menu;
        }

        public void setMenu(Menu menu) {
                this.menu = menu;
        }

        public String getItemName() {
                return itemName;
        }

        public void setItemName(String itemName) {
                this.itemName = itemName;
        }

        public String getDescription() {
                return description;
        }

        public void setDescription(String description) {
                this.description = description;
        }

        public String getImage() {
                return image;
        }

        public void setImage(String image) {
                this.image = image;
        }

        public String getSize() {
                return size;
        }

        public void setSize(String size) {
                this.size = size;
        }

        public Double getPrice() {
                return price;
        }

        public void setPrice(Double price) {
                this.price = price;
        }

        public Boolean getStatus() {
                return status;
        }

        public void setStatus(Boolean status) {
                this.status = status;
        }
}
