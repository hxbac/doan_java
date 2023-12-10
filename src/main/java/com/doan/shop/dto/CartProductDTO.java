package com.doan.shop.dto;

import java.text.NumberFormat;
import java.util.Locale;

import com.doan.shop.model.Cart;
import com.doan.shop.model.Product;

public class CartProductDTO {

    private Long id;
    private Long productID;
    private Integer quantity;
    private String name;
    private String image;
    private Long price;

    public CartProductDTO(Cart cart, Product product) {
        this.id = cart.getId();
        this.productID = product.getId();
        this.quantity = cart.getQuantity();
        this.name = product.getName();
        this.image = product.getImage();
        this.price = product.getPrice();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductID() {
        return productID;
    }

    public void setProductID(Long productID) {
        this.productID = productID;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getPriceStr() {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        return numberFormat.format(this.getPrice());
    }

    public String getTotalPriceStr() {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        return numberFormat.format(this.getPrice() * this.getQuantity());
    }
}

