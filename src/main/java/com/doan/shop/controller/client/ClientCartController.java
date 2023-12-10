package com.doan.shop.controller.client;

import java.util.ArrayList;
import java.util.List;
import com.doan.shop.controller.Base;
import com.doan.shop.dto.CartProductDTO;
import com.doan.shop.model.Cart;
import com.doan.shop.model.Product;
import com.doan.shop.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import com.doan.shop.repository.CartRepository;
import com.doan.shop.repository.ProductRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(path = "/cart")
public class ClientCartController extends Base {
    @Autowired
    private CartRepository cartRepository;
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
        return "client/cart/index";
    }

    
    @PostMapping("/add-to-cart")
    public String addToCart(@RequestParam Long productID, @RequestParam Integer quantity, HttpSession session) {
        User userLogin = this.getUserLogin(session);
        List<Cart> checkExist = this.cartRepository.findAllByUserIDAndProductID(userLogin.getId(), productID);
        if (checkExist.size() == 0) {
            Product product = productRepository.findById(productID).orElseThrow(() -> new IllegalArgumentException("Không tồn tại bản ghi"));
            Cart newCart = new Cart();
            newCart.setProduct(product);
            newCart.setUserID(userLogin.getId());
            newCart.setQuantity(quantity);
            cartRepository.save(newCart);
        } else {
            Cart cart = checkExist.get(0);
            cart.setQuantity(quantity + cart.getQuantity());
            cartRepository.save(cart);
        }
        return "redirect:/cart/index";
    }
}
