package com.app.BrzFinances.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "daily_total_extracts")
public class DailyTotalExtract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private BrzUser brzUser;

    @OneToMany(mappedBy = "dailyTotalExtract")
    private List<PurchaseDetail> purchaseDetails = new ArrayList<>();

    public DailyTotalExtract(){}

    public DailyTotalExtract(Date date, BrzUser brzUser) {
        this.date = date;
        this.brzUser = brzUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BrzUser getBrzUser() {
        return brzUser;
    }

    public void setBrzUser(BrzUser brzUser) {
        this.brzUser = brzUser;
    }

    public List<PurchaseDetail> getPurchaseDetails() {
        return purchaseDetails;
    }

    public void addPurchaseDetail(PurchaseDetail purchaseDetail){
        purchaseDetails.add(purchaseDetail);
    }
}
