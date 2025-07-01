package com.app.BrzFinances.service;

import com.app.BrzFinances.entity.PurchaseDetail;
import com.app.BrzFinances.entity.dto.PurchaseDetailRequestDto;
import com.app.BrzFinances.entity.dto.PurchaseDetailResponseDto;
import com.app.BrzFinances.service.impl.SearchFilter;

import java.util.List;

public interface PurchaseDetailService {

    PurchaseDetailResponseDto addPurchaseDetail(PurchaseDetailRequestDto dto, Long id);

    void deletePurchaseDetailById(Long id);

    List<PurchaseDetailResponseDto> findAllPurchases();

    List<PurchaseDetailResponseDto> findPurchaseByFilter(SearchFilter filter);

}
