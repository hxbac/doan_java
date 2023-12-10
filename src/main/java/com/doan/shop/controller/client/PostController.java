package com.doan.shop.controller.client;

import java.util.List;

import com.doan.shop.controller.Base;
import com.doan.shop.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import com.doan.shop.repository.PostRepository;

@Controller
@RequestMapping(path = "/post")
public class PostController extends Base {
    @Autowired
    private PostRepository postRepository;

    @GetMapping("/index")
    public String index(Model model) {
        List<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts); 
        return "client/post/index";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Post post = postRepository.findById(id).orElse(null);
        model.addAttribute("post", post); 
        return "client/post/detail";
    }
}
