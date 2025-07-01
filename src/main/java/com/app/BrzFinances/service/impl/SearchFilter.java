package com.app.BrzFinances.service.impl;

import com.app.BrzFinances.entity.PurchaseDetail;

@FunctionalInterface
public interface SearchFilter{

    Boolean condition(PurchaseDetail pd);
}
