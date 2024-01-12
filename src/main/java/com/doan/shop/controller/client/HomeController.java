package com.doan.shop.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.doan.shop.controller.Base;
import com.doan.shop.model.Post;
import com.doan.shop.model.Product;
import com.doan.shop.repository.PostRepository;
import com.doan.shop.repository.ProductRepository;

import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;


@Controller
@RequestMapping(path = "/")
public class HomeController extends Base {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private PostRepository postRepository;

    @RequestMapping("/")
    public String index(Model model, HttpSession session) {
        List<Product> products = productRepository.findTop8ByOrderByIdDesc();
        model.addAttribute("products", products);
        List<Post> posts = postRepository.findTop4ByOrderByIdDesc();
        model.addAttribute("posts", posts);
        return "client/home/index";
    }
}
