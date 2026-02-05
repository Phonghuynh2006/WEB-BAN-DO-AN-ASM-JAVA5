//package com.poly.controller.asm_java5.repository;
//
//import com.poly.controller.asm_java5.entity.MenuItem;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import java.util.List;
//
////public interface MenuItemRepository extends JpaRepository<MenuItem, Integer> {
////
////    // load sản phẩm còn bán
////    List<MenuItem> findByIsAvailableTrue();
////}
//public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
//
//
//        List<MenuItem> findByIsAvailableTrue();
//
//        List<MenuItem> findByMenuId(Long menuId);
//
//
//}
package com.poly.controller.asm_java5.repository;

import com.poly.controller.asm_java5.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuItemRepository extends JpaRepository<MenuItem, Integer> {

    List<MenuItem> findByIsAvailableTrue();

    // 🔥 load sản phẩm theo MENU (qua menuType → menu)
    List<MenuItem> findByMenuType_Menu_Id(Integer menuId);

    // load theo menu type
    List<MenuItem> findByMenuType_Id(Integer menuTypeId);
}
