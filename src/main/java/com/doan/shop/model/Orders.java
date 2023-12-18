package com.doan.shop.model;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullname;
    private String phone;
    private String address;
    private Long total;
    private Integer status;
    private String note;
    private String message;
    @Column(name = "created_at", nullable = true)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;

    public String getStatusStr() {
        switch (this.status) {
            case 0:
                String str = "</br> <a href='/order/cancel/"+ id +"'>Hủy đặt hàng</a>";
                return "<span class=\"text-nowrap badge badge-info\">Chờ xác nhận</span>" + str;
            case 3:
                return "<span class=\"text-nowrap badge badge-danger\">Đã hủy</span>";
            case 1:
                return "<span class=\"text-nowrap badge badge-warning\">Đang giao hàng</span>";
            case 2:
                return "<span class=\"text-nowrap badge badge-success\">Thành công</span>";
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
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