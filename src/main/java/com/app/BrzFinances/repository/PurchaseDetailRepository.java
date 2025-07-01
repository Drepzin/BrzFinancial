package com.app.BrzFinances.repository;

import com.app.BrzFinances.entity.PurchaseDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseDetailRepository extends JpaRepository<PurchaseDetail, Long> {
}
