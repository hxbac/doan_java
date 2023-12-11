package com.doan.shop.dto;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import com.doan.shop.model.Orders;
import com.doan.shop.model.User;
import jakarta.persistence.Column;

public class OrderUserDTO {
    public OrderUserDTO(Orders od, User u) {
        this.id = od.getId();
        this.fullname = od.getFullname();
        this.name = u.getName();
        this.phone = od.getPhone();
        this.address = od.getAddress();
        this.total = od.getTotal();
        this.userID = u.getId();
        this.status = od.getStatus();
        this.note = od.getNote();
        this.message = od.getMessage();
        this.createdAt = od.getCreatedAt();
    }

    private Long id;
    private String fullname;
    private String name;
    private String phone;
    private String address;
    private Long total;
    private Long userID;
    private Integer status;
    private String note;
    private String message;
    @Column(name = "created_at", nullable = true)
    private LocalDateTime createdAt;

    public String getStatusStr() {
        switch (this.status) {
            case 0:
                return "Chờ admin xác nhận.";
            case 3:
                return "Đơn đặt hàng đã bị hủy.";
            case 1:
                return "Đơn đặt hàng đã được xác nhận. Chờ giao hàng.";
            case 2:
                return "Đơn đặt hàng thành công.";
            default:
                return "Trạng thái không xác định";
        }
    }

    public String getTimeCreate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");
        return this.createdAt.format(formatter);
    }

    public String getTotalPriceStr() {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        return numberFormat.format(this.total);
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

