package com.doan.shop.controller.admin;

import java.util.List;

import com.doan.shop.controller.BaseAdmin;
import com.doan.shop.model.Product;
import com.doan.shop.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.ui.Model;

import com.doan.shop.repository.ProductRepository;
import com.doan.shop.repository.CategoryRepository;

@Controller
@RequestMapping(path = "/admin/product")
public class AdminProduct extends BaseAdmin {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/index")
    public String index(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products); 
        return "admin/product/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("product", new Product()); 
        model.addAttribute("categories", categories); 
        return "admin/product/create";
    }

    @PostMapping(value = "/store")
    public String store(@ModelAttribute Product form, @RequestParam("inputImage") MultipartFile file, @RequestParam("categoryID") Long categoryID) {
        form.setImage("");
        if (!file.isEmpty()) {
            form.setImage(this.uploadFile(file));
        }
        Category category = categoryRepository.findById(categoryID).orElse(null);
        form.setCategory(category);
        productRepository.save(form);
        return "redirect:/admin/product/index";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Product product = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Không tồn tại bản ghi"));
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("product", product);
        model.addAttribute("categories", categories);
        return "admin/product/edit";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, @ModelAttribute Product product, @RequestParam("inputImage") MultipartFile file, @RequestParam("categoryID") Long categoryID) {
        Product productUpdate = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Không tồn tại bản ghi"));
        productUpdate.setName(product.getName());
        if (!file.isEmpty()) {
            productUpdate.setImage(this.uploadFile(file));
        }
        Category category = categoryRepository.findById(categoryID).orElse(null);
        productUpdate.setCategory(category);
        productUpdate.setPrice(product.getPrice());
        productUpdate.setDescription(product.getDescription());
        productRepository.save(productUpdate);
        return "redirect:/admin/product/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        productRepository.deleteById(id);
        return "redirect:/admin/product/index";
    }
}
