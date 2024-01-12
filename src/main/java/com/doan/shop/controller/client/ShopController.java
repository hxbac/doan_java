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
    public String index(
        Model model,
        @RequestParam(required = false) Long categoryid,
        @RequestParam(required = false) String ram,
        @RequestParam(required = false) String price,
        @RequestParam(required = false) String memory,
        @RequestParam(required = false) String search
    ) {
        List<Product> products = productRepository.findProductsByQuery(ram, memory, price, search, categoryid);
        model.addAttribute("products", products);
        model.addAttribute("ram", ram);
        model.addAttribute("price", price);
        model.addAttribute("memory", memory);
        model.addAttribute("search", search);
        return "client/shop/index";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Product product = productRepository.findById(id).orElse(null);
        List<Product> productsRelate = productRepository.findProductsByCategory(product.getCategory().getId());
        model.addAttribute("product", product); 
        model.addAttribute("productsRelate", productsRelate);
        return "client/shop/detail";
    }
}
