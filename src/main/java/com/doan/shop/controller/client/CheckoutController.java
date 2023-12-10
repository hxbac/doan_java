package com.doan.shop.controller.client;

import java.util.ArrayList;
import java.util.List;
import com.doan.shop.controller.Base;
import com.doan.shop.dto.CartProductDTO;
import com.doan.shop.model.OrderDetail;
import com.doan.shop.model.Orders;
import com.doan.shop.model.Product;
import com.doan.shop.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import com.doan.shop.repository.CartRepository;
import com.doan.shop.repository.OrderDetailRepository;
import com.doan.shop.repository.OrderRepository;
import com.doan.shop.repository.ProductRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(path = "/checkout")
public class CheckoutController extends Base {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/index")
    public String index(HttpSession session, Model model) {
        User userLogin = this.getUserLogin(session);
        List<CartProductDTO> cartProductDTOs = cartRepository.findAllByUserIDWithProduct(userLogin.getId());
        Long totalPriceCart = 0L;
        for (CartProductDTO cart : cartProductDTOs) {
            totalPriceCart += cart.getQuantity() * cart.getPrice();
        }
        model.addAttribute("carts", cartProductDTOs);
        model.addAttribute("totalPriceCart", this.getPriceStr(totalPriceCart));
        model.addAttribute("orders", new Orders()); 
        return "client/checkout/index";
    }

    @PostMapping("/store")
    public String store(HttpSession session, @ModelAttribute Orders order) {
        User userLogin = this.getUserLogin(session);

        order.setUser(userLogin);
        order.setStatus(order.statusOrder().get("ORDER"));
        orderRepository.save(order);

        List<CartProductDTO> cartProductDTOs = cartRepository.findAllByUserIDWithProduct(userLogin.getId());
        Long totalPriceCart = 0L;
        List<OrderDetail> orderDetails = new ArrayList<>();
        for (CartProductDTO cart : cartProductDTOs) {
            Product product = productRepository.findById(cart.getProductID()).orElse(null);
            if (product == null) {
                continue;
            }
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(order);
            orderDetail.setProductID(product);
            orderDetail.setPrice(product.getPrice());
            orderDetail.setQuantity(cart.getQuantity());
            orderDetails.add(orderDetail);
            totalPriceCart += cart.getQuantity() * cart.getPrice();
        }
        order.setTotal(totalPriceCart);
        orderRepository.save(order);
        orderDetailRepository.saveAll(orderDetails);
        cartRepository.deleteByUserID(userLogin.getId());
        return "redirect:/cart/index";
    }
}
