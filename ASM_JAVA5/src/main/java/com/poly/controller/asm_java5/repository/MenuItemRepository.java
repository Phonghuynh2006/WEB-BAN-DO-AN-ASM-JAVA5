package com.poly.controller.asm_java5.repository;

import com.poly.controller.asm_java5.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuItemRepository extends JpaRepository<MenuItem, Integer> {

    // load sản phẩm còn bán
    List<MenuItem> findByIsAvailableTrue();
}
