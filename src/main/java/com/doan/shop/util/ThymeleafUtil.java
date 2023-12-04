package com.doan.shop.util;

public class ThymeleafUtil {
    public static String formatPrice(Long price) {
        return String.format("%,.0f", price);
    }
}
