package com.doan.shop.controller;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import jakarta.servlet.http.HttpSession;
import com.doan.shop.model.Menu;
import com.doan.shop.model.User;
import com.doan.shop.repository.MenuRepository;
import com.doan.shop.repository.UserRepository;

@Controller
public class Base {
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private UserRepository userRepository;

    @ModelAttribute("userLogin")
    public User userLogin(HttpSession session) {
        Long userID = (Long) session.getAttribute("userID");
        if (userID == null) {
            userID = -1L;
        }
        User user = userRepository.findById(userID).orElse(null);
        return user;
    }

    @ModelAttribute("menus")
    public List<Menu> getMenus() {
        return menuRepository.findAll();
    }

    public User getUserLogin(HttpSession session) {
        Long userID = (Long) session.getAttribute("userID");
        if (userID == null) {
            userID = -1L;
        }
        User user = userRepository.findById(userID).orElse(null);
        return user;
    }

    

    public String getPriceStr(Long price) {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        return numberFormat.format(price);
    }
}
