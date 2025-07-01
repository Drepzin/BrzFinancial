package com.app.BrzFinances.entity.dto;

import com.app.BrzFinances.entity.PaymentMethod;
import com.app.BrzFinances.entity.PurchaseDetail;

import java.math.BigDecimal;
import java.time.LocalTime;

public record PurchaseDetailResponseDto(LocalTime hour, String productName, Integer quantity
        , BigDecimal totalValue, String paymentMethod) {

    public static PurchaseDetailResponseDto toResponse(PurchaseDetail detail){
        LocalTime hour = detail.getHours();
        String productName = detail.getProduct().getName();
        Integer quantity = detail.getQuantity();
        BigDecimal totalValue = detail.getTotalValue();
        String paymentMethod = detail.getPaymentMethod().getType();
        return new PurchaseDetailResponseDto(hour, productName, quantity, totalValue, paymentMethod);
    }
}
