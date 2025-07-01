package com.app.BrzFinances.service.impl;

import com.app.BrzFinances.entity.PaymentMethod;
import com.app.BrzFinances.entity.Product;
import com.app.BrzFinances.entity.PurchaseDetail;
import com.app.BrzFinances.entity.dto.PurchaseDetailRequestDto;
import com.app.BrzFinances.entity.dto.PurchaseDetailResponseDto;
import com.app.BrzFinances.exception.InvalidPaymentMethodException;
import com.app.BrzFinances.exception.ProductNotFoundException;
import com.app.BrzFinances.exception.PurchaseNotFoundException;
import com.app.BrzFinances.repository.PaymentMethodsRepository;
import com.app.BrzFinances.repository.ProductRepository;
import com.app.BrzFinances.repository.PurchaseDetailRepository;
import com.app.BrzFinances.service.BrzUserService;
import com.app.BrzFinances.service.PurchaseDetailService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseDetailServiceImpl implements PurchaseDetailService {

    private final PurchaseDetailRepository detailRepository;

    private final ProductRepository productRepository;

    private final PaymentMethodsRepository paymentMethodsRepository;

    private final BrzUserServiceImpl brzUserService;

    public PurchaseDetailServiceImpl(PurchaseDetailRepository detailRepository, ProductRepository productRepository,
                                     PaymentMethodsRepository paymentMethodsRepository, BrzUserServiceImpl brzUserService) {
        this.detailRepository = detailRepository;
        this.productRepository = productRepository;
        this.paymentMethodsRepository = paymentMethodsRepository;
        this.brzUserService = brzUserService;
    }

    @Override
    public PurchaseDetailResponseDto addPurchaseDetail(PurchaseDetailRequestDto dto, Long userId) {
        var optUser = brzUserService.findUserById(userId);
        LocalTime hours = LocalDateTime.now().toLocalTime();
        //
        String productName = dto.productName();
        var product = findProductByName(productName);
        Integer quantity = dto.quantity();
        BigDecimal price = product.getPrice();
        BigDecimal totalValue = price.multiply(BigDecimal.valueOf(dto.quantity()));
        PaymentMethod paymentMethod = findPaymentMethodByCode(dto.paymentMethod());
        //
        PurchaseDetail pd = new PurchaseDetail(hours, product, quantity, paymentMethod);
        pd.setTotalValue(totalValue);
        pd.setBrzUser(optUser);
        detailRepository.save(pd);
        return PurchaseDetailResponseDto.toResponse(pd);
    }

    @Override
    public void deletePurchaseDetailById(Long id) {
        var optPurchase = findPurchaseById(id);
        detailRepository.delete(optPurchase);
    }

    @Override
    public List<PurchaseDetailResponseDto> findAllPurchases() {
        return detailRepository.findAll().stream().map(PurchaseDetailResponseDto::toResponse).toList();
    }

    @Override
    public List<PurchaseDetailResponseDto> findPurchaseByFilter(SearchFilter filter) {
        var list = detailRepository.findAll();
        List<PurchaseDetailResponseDto> responses = new ArrayList<>();
        for (PurchaseDetail pd : list){
            if(filter.condition(pd)){
                responses.add(PurchaseDetailResponseDto.toResponse(pd));
            }
        }
        return responses;
    }

    private Product findProductByName(String productName){
        var optProduct = productRepository.findProductByName(productName);
        if(optProduct.isEmpty()){
            throw new ProductNotFoundException("Product don't exist!");
        }
        return optProduct.get();
    }

    private PurchaseDetail findPurchaseById(Long id){
        var optPurchase = detailRepository.findById(id);
        if(optPurchase.isEmpty()){
            throw new PurchaseNotFoundException("Purchase not found");
        }
        return optPurchase.get();
    }

    private PaymentMethod findPaymentMethodByCode(Integer code){
        var optPayment = paymentMethodsRepository.findByCode(code);
        if(optPayment.isEmpty()){
            throw new InvalidPaymentMethodException("This payment is not acceptable");
        }
        return optPayment.get();
    }
}
