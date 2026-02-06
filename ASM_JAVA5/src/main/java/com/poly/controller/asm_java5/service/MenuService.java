package com.poly.controller.asm_java5.service;

import com.poly.controller.asm_java5.entity.Menu;
import com.poly.controller.asm_java5.entity.MenuItem;
import com.poly.controller.asm_java5.repository.MenuItemRepository;
import com.poly.controller.asm_java5.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    // load menu
    public List<Menu> findAllMenus() {
        return menuRepository.findAll();
    }

    // load item theo menu
    public List<MenuItem> findItemsByMenu(Integer menuId) {
        return menuItemRepository.findByMenu_MenuIdAndStatusTrue(menuId);
    }

    // chi tiết sản phẩm
    public MenuItem findItemById(Integer itemId) {
        return menuItemRepository.findById(itemId).orElse(null);
    }

    // ✅ FIX CHÍNH Ở ĐÂY – LOAD SẢN PHẨM TRANG HOME
    public List<MenuItem> findBestSellers() {
        return menuItemRepository.findByStatusTrue();
    }
}
