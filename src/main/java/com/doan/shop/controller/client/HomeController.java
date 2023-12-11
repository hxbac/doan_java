package com.doan.shop.controller.client;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.doan.shop.controller.Base;
import com.doan.shop.model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;


@Controller
@RequestMapping(path = "/")
public class HomeController extends Base {
    @RequestMapping("/")
    public String index(Model model, HttpSession session) {
        model.addAttribute("role", session.getAttribute("role"));
        return "client/home/index";
    }

    @RequestMapping("/my")
    public String my(HttpSession session, Model model) {
        User user = this.getUserLogin(session);
        model.addAttribute("fullname", user.getName());
        return "client/home/index";
    }
}
