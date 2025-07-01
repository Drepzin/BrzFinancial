package com.app.BrzFinances.entity.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PurchaseDetailRequestDto(@NotBlank String productName, @NotNull Integer quantity, @NotNull Integer paymentMethod){
}