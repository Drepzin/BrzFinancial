package com.app.BrzFinances.entity.dto;

import com.app.BrzFinances.entity.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

public record ProductDto(@NotBlank String name, @NotNull BigDecimal price) {

    public Product toProduct(){
        return new Product(name, price);
    }

    public static ProductDto toDto(Product product){
        return new ProductDto(product.getName(), product.getPrice());
    }

    public class convert {

        public static List<ProductDto> toProductList(List<Product> products){
            return products.stream().map(p -> new ProductDto(p.getName(), p.getPrice())).toList();
        }
    }
}
