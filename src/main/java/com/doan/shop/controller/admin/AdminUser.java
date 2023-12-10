package com.doan.shop.controller.admin;

import java.util.List;
import com.doan.shop.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import com.doan.shop.repository.UserRepository;
import com.doan.shop.util.HashPassword;

@Controller
@RequestMapping(path = "/admin/user")
public class AdminUser {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/index")
    public String index(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users); 
        return "admin/user/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("user", new User()); 
        return "admin/user/create";
    }

    @PostMapping(value = "/store")
    public String store(@ModelAttribute User form) {
        String hashPass = HashPassword.hashMD5(form.getPassword());
        form.setPassword(hashPass);
        userRepository.save(form);
        return "redirect:/admin/user/index";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Không tồn tại bản ghi"));
        model.addAttribute("user", user);
        return "admin/user/edit";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id, @ModelAttribute User user) {
        User userUpdate = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));
        String hashPass = HashPassword.hashMD5(user.getPassword());
        user.setPassword(hashPass);
        userUpdate.setName(user.getName());
        userUpdate.setEmail(user.getEmail());
        userUpdate.setPassword(user.getPassword());
        userUpdate.setAddress(user.getAddress());
        userUpdate.setRole(user.getRole());
        userRepository.save(userUpdate);
        return "redirect:/admin/user/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        userRepository.deleteById(id);
        return "redirect:/admin/product/index";
    }
}
