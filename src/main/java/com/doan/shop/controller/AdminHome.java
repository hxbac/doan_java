package com.doan.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/admin")
public class AdminHome {

    @GetMapping("/")
    public String index() {
        return "admin/home/index";
    }
}
