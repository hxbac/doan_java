package com.doan.shop.controller.admin;

import java.util.List;
import com.doan.shop.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import com.doan.shop.repository.CategoryRepository;

@Controller
@RequestMapping(path = "/admin/category")
public class AdminCategory {
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/index")
    public String index(Model model) {
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);
        return "admin/category/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("category", new Category()); 
        return "admin/category/create";
    }

    @PostMapping(value = "/store")
    public String store(@ModelAttribute Category form) {
        categoryRepository.save(form);
        return "redirect:/admin/category/index";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Không tồn tại bản ghi"));
        model.addAttribute("category", category);
        return "admin/category/edit";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, @ModelAttribute Category category) {
        Category categoryUpdate = categoryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Không tồn tại bản ghi"));
        categoryUpdate.setName(category.getName());
        categoryRepository.save(categoryUpdate);
        return "redirect:/admin/category/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        categoryRepository.deleteById(id);
        return "redirect:/admin/category/index";
    }
}
