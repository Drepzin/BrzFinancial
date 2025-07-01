package com.app.BrzFinances.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalTime;

@Entity
@Table(name = "purchase_detail")
public class PurchaseDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalTime hours;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    private Integer quantity;

    @Column(name = "total_value")
    private BigDecimal totalValue;

    @ManyToOne
    @JoinColumn(name = "payment_method")
    private PaymentMethod paymentMethod;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private BrzUser brzUser;

    public PurchaseDetail(){}

    public PurchaseDetail(LocalTime hours, Product product, Integer quantity, PaymentMethod paymentMethod) {
        this.hours = hours;
        this.product = product;
        this.quantity = quantity;
        this.paymentMethod = paymentMethod;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalTime getHours() {
        return hours;
    }

    public void setHours(LocalTime hours) {
        this.hours = hours;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public BrzUser getBrzUser() {
        return brzUser;
    }

    public void setBrzUser(BrzUser brzUser) {
        this.brzUser = brzUser;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }
}
