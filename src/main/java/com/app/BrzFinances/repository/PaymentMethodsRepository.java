package com.app.BrzFinances.repository;

import com.app.BrzFinances.entity.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentMethodsRepository extends JpaRepository<PaymentMethod, Long> {
}
