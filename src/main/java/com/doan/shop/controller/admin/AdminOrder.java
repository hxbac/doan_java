package com.doan.shop.controller.admin;

import java.util.List;
import com.doan.shop.model.Orders;
import com.doan.shop.dto.OrderDetailDTO;
import com.doan.shop.dto.OrderUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import com.doan.shop.repository.OrderRepository;
import com.doan.shop.repository.OrderDetailRepository;

@Controller
@RequestMapping(path = "/admin/order")
public class AdminOrder {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @GetMapping("/index")
    public String index(Model model) {
        List<OrderUserDTO> orders = orderRepository.findAllWithUser();
        model.addAttribute("orders", orders);
        return "admin/order/index";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Long id ,Model model) {
        Orders order = orderRepository.findById(id).orElse(null);
        List<OrderDetailDTO> ods = orderDetailRepository.findAllByOrderIDWithProduct(id);
        model.addAttribute("orders", ods);
        model.addAttribute("order", order);
        return "admin/order/detail";
    }

    @GetMapping("/accept/{id}") 
    public String accept(@PathVariable Long id) {
        Orders order = orderRepository.findById(id).orElse(null);
        if (order == null) {
            return "redirect:/admin/order/index";
        }
        order.setStatus(1);
        order.setMessage("Admin đã xác nhận đơn đặt hàng");
        orderRepository.save(order);
        return "redirect:/admin/order/detail/" + id;
    }

    @GetMapping("/cancel/{id}") 
    public String cancel(@PathVariable Long id) {
        Orders order = orderRepository.findById(id).orElse(null);
        if (order == null) {
            return "redirect:/admin/order/index";
        }
        order.setStatus(3);
        order.setMessage("Admin đã hủy đơn đặt hàng");
        orderRepository.save(order);
        return "redirect:/admin/order/detail/" + id;
    }

    @GetMapping("/success/{id}") 
    public String success(@PathVariable Long id) {
        Orders order = orderRepository.findById(id).orElse(null);
        if (order == null) {
            return "redirect:/admin/order/index";
        }
        order.setStatus(2);
        order.setMessage("Admin đã xác nhận giao hàng thành công");
        orderRepository.save(order);
        return "redirect:/admin/order/detail/" + id;
    }
}
