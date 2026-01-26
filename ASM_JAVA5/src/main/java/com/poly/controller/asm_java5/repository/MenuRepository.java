package com.poly.controller.asm_java5.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.controller.asm_java5.entity.Menu;

public interface MenuRepository extends JpaRepository<Menu, Integer> {

    List<Menu> findByIsActiveTrue();

}
