package com.app.BrzFinances.controller;

import com.app.BrzFinances.entity.Product;
import com.app.BrzFinances.entity.dto.ProductDto;
import com.app.BrzFinances.service.impl.ProductServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Product")
public class ProductController {

    private final ProductServiceImpl productServiceImpl;

    public ProductController(ProductServiceImpl productServiceImpl) {
        this.productServiceImpl = productServiceImpl;
    }

    @PostMapping
    public ResponseEntity<ProductDto> addProduct(@Valid @RequestBody ProductDto productDto){
        return new ResponseEntity<>(productServiceImpl.addProduct(productDto), HttpStatusCode.valueOf(201));
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> findAllProducts(){
        return new ResponseEntity<>(productServiceImpl.getAllProducts(), HttpStatusCode.valueOf(200));
    }

    @GetMapping("/{name}")
    public ResponseEntity<Product> findProductByName(@PathVariable(name = "name") String productName){
        return new ResponseEntity<>(productServiceImpl.findProductByName(productName), HttpStatusCode.valueOf(200));
    }
}
