package com.doan.shop.controller.admin;

import java.util.List;

import com.doan.shop.controller.BaseAdmin;
import com.doan.shop.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.ui.Model;

import com.doan.shop.repository.ProductRepository;

@Controller
@RequestMapping(path = "/admin/product")
public class AdminProduct extends BaseAdmin {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/index")
    public String index(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products); 
        return "admin/product/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("product", new Product()); 
        return "admin/product/create";
    }

    @PostMapping(value = "/store")
    public String store(@ModelAttribute Product form, @RequestParam("inputImage") MultipartFile file) {
        form.setImage("");
        if (!file.isEmpty()) {
            form.setImage(this.uploadFile(file));
        }
        productRepository.save(form);
        return "redirect:/admin/product/index";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Product product = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Không tồn tại bản ghi"));
        model.addAttribute("product", product);
        return "admin/product/edit";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, @ModelAttribute Product product, @RequestParam("inputImage") MultipartFile file) {
        Product productUpdate = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Không tồn tại bản ghi"));
        productUpdate.setName(product.getName());
        if (!file.isEmpty()) {
            productUpdate.setImage(this.uploadFile(file));
        }
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
