package com.doan.shop.controller.client;

import java.util.List;

import com.doan.shop.controller.Base;
import com.doan.shop.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import com.doan.shop.repository.ProductRepository;

@Controller
@RequestMapping(path = "/shop")
public class ShopController extends Base {
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/index")
    public String index(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("products", products); 
        return "client/shop/index";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Product product = productRepository.findById(id).orElse(null);
        model.addAttribute("product", product); 
        return "client/shop/detail";
    }
}
