package com.poly.controller.asm_java5.repository;

import com.poly.controller.asm_java5.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuItemRepository extends JpaRepository<MenuItem, Integer> {

    List<MenuItem> findByMenu_MenuId(Integer menuId);

    List<MenuItem> findByStatusTrue();

    List<MenuItem> findByMenu_MenuIdAndStatusTrue(Integer menuId);
}
