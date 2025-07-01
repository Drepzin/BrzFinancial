package com.app.BrzFinances.service.impl;

import com.app.BrzFinances.entity.Product;
import com.app.BrzFinances.entity.dto.ProductDto;
import com.app.BrzFinances.exception.BrzFinanceException;
import com.app.BrzFinances.exception.ProductAlreadyExistException;
import com.app.BrzFinances.exception.ProductNotFoundException;
import com.app.BrzFinances.exception.PurchaseNotFoundException;
import com.app.BrzFinances.repository.ProductRepository;
import com.app.BrzFinances.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductDto addProduct(ProductDto productDto){
        Product product = productDto.toProduct();
        product.setName(product.getName().toLowerCase().replace(' ', '-'));
        var optProduct = productRepository.findProductByName(productDto.name());
        if(optProduct.isPresent()){
            throw new ProductAlreadyExistException("This product is already registered");
        }
        productRepository.save(product);
        return ProductDto.toDto(product);
    }

    @Override
    public void deleteProductById(Long id) {
        var optProduct = findProductById(id);
        productRepository.delete(optProduct);
    }

    @Override
    public void deleteProductByName(String name) {
        var list = productRepository.findAll();
        if(list.isEmpty()){
            throw new BrzFinanceException();
        }
        productRepository.delete(list.get(0));
    }

    @Override
    public ProductDto updateProduct(Long id, ProductDto productDto) {
        return null;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream().map(ProductDto::toDto).toList();
    }

    @Override
    public Product findProductByName(String name) {
        var optProduct = productRepository.findProductByName(name);
        if (optProduct.isEmpty()){
            throw new ProductNotFoundException("Product not found in the database");
        }
        return optProduct.get();
    }

    public Product findProductById(Long id){
        var product = productRepository.findById(id);
        if(product.isEmpty()){
            throw new ProductNotFoundException("Product don't exist");
        }
        return product.get();
    }


}
