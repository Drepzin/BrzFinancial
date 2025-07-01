package com.app.BrzFinances.service;

import com.app.BrzFinances.entity.Product;
import com.app.BrzFinances.entity.dto.ProductDto;

import java.util.List;

public interface ProductService {

    ProductDto addProduct(ProductDto productDto);

    void deleteProductById(Long id);

    void deleteProductByName(String name);

    ProductDto updateProduct(Long id, ProductDto productDto);

    List<ProductDto> getAllProducts();

    Product findProductByName(String name);
}
