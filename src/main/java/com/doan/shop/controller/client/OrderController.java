package com.doan.shop.controller.client;


import java.util.List;
import com.doan.shop.controller.Base;
import com.doan.shop.dto.OrderDetailDTO;
import com.doan.shop.model.Orders;
import com.doan.shop.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import com.doan.shop.repository.OrderDetailRepository;
import com.doan.shop.repository.OrderRepository;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(path = "/order")
public class OrderController extends Base {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @GetMapping("/index")
    public String index(HttpSession session, Model model) {
        User userLogin = this.getUserLogin(session);
        List<Orders> orders = orderRepository.findAllOrdersByUserId(userLogin.getId());
        model.addAttribute("orders", orders);
        return "client/order/index";
    }

    @GetMapping("/detail/{id}")
    public String detail(HttpSession session, Model model, @PathVariable Long id) {
        User userLogin = this.getUserLogin(session);
        Orders order = orderRepository.findById(id).orElse(null);
        if (order == null || order.getUser().getId() != userLogin.getId()) {
            return "redirect:/";
        }
        List<OrderDetailDTO> ods = orderDetailRepository.findAllByOrderIDWithProduct(id);
        model.addAttribute("orders", ods);
        model.addAttribute("order", order);
        return "client/order/detail";
    }

    @GetMapping("/cancel/{id}")
    public String cancel(HttpSession session, Model model, @PathVariable Long id) {
        User userLogin = this.getUserLogin(session);
        Orders order = orderRepository.findById(id).orElse(null);
        if (order == null || order.getUser().getId() != userLogin.getId() || order.getStatus() != 0) {
            return "redirect:/";
        }
        order.setMessage("Người dùng hủy đặt hàng");
        order.setStatus(3);
        orderRepository.save(order);
        return "redirect:/order/index";
    }
}
