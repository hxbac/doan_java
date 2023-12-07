package com.doan.shop.controller;

import java.util.List;
import com.doan.shop.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.ui.Model;

import com.doan.shop.repository.PostRepository;

@Controller
@RequestMapping(path = "/admin/post")
public class AdminPost extends BaseAdmin {
    @Autowired
    private PostRepository postRepository;

    @GetMapping("/index")
    public String index(Model model) {
        List<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts); 
        return "admin/post/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("post", new Post()); 
        return "admin/post/create";
    }

    @PostMapping(value = "/store")
    public String store(@ModelAttribute Post form, @RequestParam("inputImage") MultipartFile file) {
        form.setImage("");
        if (!file.isEmpty()) {
            form.setImage(this.uploadFile(file));
        }
        postRepository.save(form);
        return "redirect:/admin/post/index";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Không tồn tại bản ghi"));
        model.addAttribute("post", post);
        return "admin/post/edit";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, @ModelAttribute Post post, @RequestParam("inputImage") MultipartFile file) {
        Post postUpdate = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Không tồn tại bản ghi"));
        postUpdate.setTitle(post.getTitle());
        postUpdate.setContent(post.getContent());
        if (!file.isEmpty()) {
            postUpdate.setImage(this.uploadFile(file));
        }
        postUpdate.setAuthor(post.getAuthor());
        postRepository.save(postUpdate);
        return "redirect:/admin/post/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        postRepository.deleteById(id);
        return "redirect:/admin/post/index";
    }
}
