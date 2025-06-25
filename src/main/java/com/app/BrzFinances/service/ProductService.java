package com.app.BrzFinances.service;

import com.app.BrzFinances.entity.Product;
import com.app.BrzFinances.entity.dto.ProductDto;
import com.app.BrzFinances.exception.ProductAlreadyExistException;
import com.app.BrzFinances.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductDto addProduct(ProductDto productDto){
        Product product = productDto.toProduct();
        var optProduct = productRepository.findProductByNameAndPrice(product.getName(), product.getPrice());
        if(optProduct.isPresent()){
            throw new ProductAlreadyExistException("This product is already registered");
        }
        productRepository.save(product);
        return productDto;
    }


}
