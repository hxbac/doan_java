package com.doan.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(path = "/")
public class HomeController extends Base {
    @RequestMapping("/")
    public String index() {
        return "client/home/index";
    }
}
