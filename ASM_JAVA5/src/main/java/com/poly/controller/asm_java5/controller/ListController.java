//package com.poly.controller.asm_java5.controller;
//
//import com.poly.controller.asm_java5.entity.Menu;
//import com.poly.controller.asm_java5.entity.MenuItem;
//import com.poly.controller.asm_java5.repository.MenuItemRepository;
//import com.poly.controller.asm_java5.repository.MenuRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.util.List;
//
//@Controller
//@RequestMapping("/product")
//public class ListController {
//
//    @Autowired
//    private MenuRepository menuRepo;
//
//    @Autowired
//    private MenuItemRepository menuItemRepo;
//
//    @GetMapping("/list")
//    public String list(
//            @RequestParam(name = "menuId", required = false) Long menuId,
//            Model model
//    ) {
//        // Load menu
//        List<Menu> menus = menuRepo.findAll();
//        model.addAttribute("menus", menus);
//
//        // Load sản phẩm theo menu
////        List<MenuItem> items;
////        if (menuId != null) {
//////            items = menuItemRepo.findByMenuId(menuId);
////            items = menuItemRepo.findByMenu_Id(menuId);
////
////        } else {
////            items = menuItemRepo.findAll();
////        }
//
//        List<MenuItem> items;
//        if (menuId != null) {
//            items = menuItemRepo.findByMenuId(menuId);
//        } else {
//            items = menuItemRepo.findByIsAvailableTrue();
//        }
//
//
//        model.addAttribute("items", items);
//        model.addAttribute("menuId", menuId);
//
//        return "product/list";
//    }
//}
package com.poly.controller.asm_java5.controller;

import com.poly.controller.asm_java5.entity.Menu;
import com.poly.controller.asm_java5.entity.MenuItem;
import com.poly.controller.asm_java5.repository.MenuItemRepository;
import com.poly.controller.asm_java5.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ListController {

    @Autowired
    private MenuRepository menuRepo;

    @Autowired
    private MenuItemRepository menuItemRepo;

    @GetMapping("/list")
    public String list(
            @RequestParam(name = "menuId", required = false) Integer menuId,
            Model model
    ) {

        // 1️⃣ Load danh sách menu (bên trái)
        List<Menu> menus = menuRepo.findAll();
        model.addAttribute("menus", menus);

        // 2️⃣ Load danh sách sản phẩm
        List<MenuItem> items;
        if (menuId != null) {
            // load sản phẩm theo menu
            items = menuItemRepo.findByMenuType_Menu_Id(menuId);
        } else {
            // load tất cả sản phẩm đang bán
            items = menuItemRepo.findByIsAvailableTrue();
        }

        // 3️⃣ Đưa dữ liệu ra view
        model.addAttribute("items", items);
        model.addAttribute("menuId", menuId); // dùng để active menu

        return "product/list";
    }
}
