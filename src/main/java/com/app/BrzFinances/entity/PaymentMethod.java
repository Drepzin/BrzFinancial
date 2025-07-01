package com.app.BrzFinances.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "payment_method")
public class PaymentMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

    private Integer code;

    @OneToMany(mappedBy = "paymentMethod", fetch = FetchType.LAZY)
    private List<PurchaseDetail> purchaseDetails = new ArrayList<>();

    public PaymentMethod(){}

    public PaymentMethod(String type, Integer code) {
        this.type = type;
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public List<PurchaseDetail> getPurchaseDetails() {
        return purchaseDetails;
    }

    public void addPurchaseDetail(PurchaseDetail purchaseDetail){
        purchaseDetails.add(purchaseDetail);
    }

    public enum PaymentType{
        CASH("Money", 1),
        CREDIT("Credit card", 2),
        DEBIT("Debit card", 3);

        private String type;

        private int code;

        PaymentType(String type, int code) {
            this.type = type;
            this.code = code;
        }

        public PaymentMethod get(){
            return new PaymentMethod(type, code);
        }
    }
}
