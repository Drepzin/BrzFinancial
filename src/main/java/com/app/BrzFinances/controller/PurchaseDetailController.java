package com.app.BrzFinances.controller;

import com.app.BrzFinances.entity.PurchaseDetail;
import com.app.BrzFinances.entity.dto.PurchaseDetailRequestDto;
import com.app.BrzFinances.entity.dto.PurchaseDetailResponseDto;
import com.app.BrzFinances.service.PurchaseDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/PurchaseDetail")
public class PurchaseDetailController {

    @Autowired
    PurchaseDetailService purchaseDetailService;

    @PostMapping("/{id}")
    public ResponseEntity<PurchaseDetailResponseDto> addPurchaseDetail(@RequestBody PurchaseDetailRequestDto dto,
                                                                       @PathVariable("id") Long id){
        return new ResponseEntity<>(purchaseDetailService.addPurchaseDetail(dto, id), HttpStatusCode.valueOf(201));
    }

    @GetMapping
    public ResponseEntity<List<PurchaseDetailResponseDto>> findAllPurchases(){
        return new ResponseEntity<>(purchaseDetailService.findAllPurchases(), HttpStatusCode.valueOf(200));
    }

    @GetMapping("/{email}")
    public ResponseEntity<List<PurchaseDetailResponseDto>> findAllPurchasesByEmail(@PathVariable("email") String email){
        return new ResponseEntity<>
                (purchaseDetailService.findPurchaseByFilter(pd -> pd.getBrzUser().getEmail().equals(email)),
                        HttpStatusCode.valueOf(200));
    }
}
