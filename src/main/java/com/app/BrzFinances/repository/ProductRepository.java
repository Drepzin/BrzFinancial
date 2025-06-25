package com.app.BrzFinances.repository;

import com.app.BrzFinances.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findProductByNameAndPrice(String name, BigDecimal price);
}
