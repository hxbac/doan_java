package com.doan.shop.dto;

import java.text.NumberFormat;
import java.util.Locale;

public class OrderTotalDTO {
    public OrderTotalDTO(Integer day, Long total) {
        this.day = day;
        this.total = total;
    }

    private Integer day;
    private Long total;

    public String getTotalPriceStr() {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        return numberFormat.format(this.total);
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }
}

