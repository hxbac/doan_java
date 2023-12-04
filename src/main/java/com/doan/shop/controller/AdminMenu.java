package com.doan.shop.controller;

import java.util.List;
import com.doan.shop.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import com.doan.shop.repository.MenuRepository;

@Controller
@RequestMapping(path = "/admin/menu")
public class AdminMenu {
    @Autowired
    private MenuRepository menuRepository;

    @GetMapping("/index")
    public String index(Model model) {
        List<Menu> menus = menuRepository.findAll();
        model.addAttribute("menus", menus); 
        return "admin/menu/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("menu", new Menu()); 
        return "admin/menu/create";
    }

    @PostMapping(value = "/store")
    public String store(@ModelAttribute Menu form) {
        menuRepository.save(form);
        return "redirect:/admin/menu/index";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Menu menu = menuRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Không tồn tại bản ghi"));
        model.addAttribute("menu", menu);
        return "admin/menu/edit";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, @ModelAttribute Menu menu) {
        Menu menuUpdate = menuRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Không tồn tại bản ghi"));
        menuUpdate.setName(menu.getName());
        menuUpdate.setLink(menu.getLink());
        menuUpdate.setOrderNum(menu.getOrderNum());
        menuRepository.save(menuUpdate);
        return "redirect:/admin/menu/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        menuRepository.deleteById(id);
        return "redirect:/admin/menu/index";
    }
}
