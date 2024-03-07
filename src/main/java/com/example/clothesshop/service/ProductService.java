package com.example.clothesshop.service;

import com.example.clothesshop.dto.ProductDto;
import com.example.clothesshop.entity.Product;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductService {
    ProductDto createProduct (ProductDto dto);
    ProductDto findById(Long id);
    List<ProductDto> getAllProducts();
    void deleteById(Long id);
    List<ProductDto> getProductsByManufacturerId (Long manufacturerId);
    List<ProductDto> findProductByColor(String color);

    List<ProductDto> findProductByCriteris(Double price, List<String> manufacturers, List<String> colors);
    void decreaseStockQuantity(Product product, int quantity);
}
