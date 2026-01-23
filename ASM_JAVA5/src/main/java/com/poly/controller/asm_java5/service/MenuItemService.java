package com.poly.controller.asm_java5.service;

import com.poly.controller.asm_java5.entity.MenuItem;
import com.poly.controller.asm_java5.repository.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuItemService {

    @Autowired
    private MenuItemRepository repo;

    public List<MenuItem> getBestSeller() {
        // tạm thời load hết, sau này mới lọc bán chạy
        return repo.findByIsAvailableTrue();
    }

}
