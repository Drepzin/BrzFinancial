package com.app.BrzFinances.repository;

import com.app.BrzFinances.entity.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentMethodsRepository extends JpaRepository<PaymentMethod, Long> {

    Optional<PaymentMethod> findByCode(Integer code);
}
