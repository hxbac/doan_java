package com.doan.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import com.doan.shop.model.User;
import com.doan.shop.repository.UserRepository;
import com.doan.shop.util.HashPassword;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(path = "/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String login() {
        return "client/auth/login";
    }

    @PostMapping("/login")
    public String handleLogin(@RequestParam("email") String email, @RequestParam("password") String password, HttpSession session, Model model) {
        String passwordHash = HashPassword.hashMD5(password);
        List<User> userLogin = userRepository.findAllByEmailAndPassword(email, passwordHash);
        if (userLogin.size() == 0) {
            model.addAttribute("error", "Tài khoản hoặc mật khẩu không chính xác!");
            return "client/auth/login";
        }
        session.setAttribute("userID", userLogin.get(0).getId());
        return "redirect:/";
    }

    @GetMapping("/register")
    public String register() {
        return "client/auth/register";
    }

    @PostMapping("/register")
    public String handleRegister(@ModelAttribute User user, HttpSession session, Model model) {
        List<User> check = userRepository.findAllByEmail(user.getEmail());
        if (check.size() != 0) {
            model.addAttribute("error", "Email đã được sử dụng!");
            return "client/auth/register";
        }
        user.setRole("USER");
        user.setPassword(HashPassword.hashMD5(user.getPassword()));
        userRepository.save(user);
        Long userID = user.getId();
        session.setAttribute("userID", userID);
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("userID");
        return "redirect:/auth/login";
    }
}
