package com.example.clothesshop.service.impl;

import com.example.clothesshop.dto.ProductDto;
import com.example.clothesshop.entity.Product;
import com.example.clothesshop.repo.ProductRepo;
import com.example.clothesshop.service.ManufacturerService;
import com.example.clothesshop.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepo repo;
    private final ManufacturerService manufacturerService;
    @Override
    public ProductDto createProduct(ProductDto dto) {
        Product product = Product.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .size(dto.getSize())
                .color(dto.getColor())
                .manufacturer(manufacturerService.findEntityById(dto.getManufacturerId()))
                .build();
        try{
            repo.save(product);
        } catch (Exception e){
            log.error(e.getStackTrace().toString());
        }
        return dto;
    }

    @Override
    public ProductDto findById(Long id) {
        Product product = repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Product not found with id" + id));
        ProductDto productDto = ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .size(product.getSize())
                .color(product.getColor())
                .manufacturerId(product.getManufacturer().getId())
                .build();
        return productDto;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = repo.findAll();
        List<ProductDto> productDtoList = new ArrayList<>();

        for (Product product: products) {
            ProductDto productDto = ProductDto.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .description(product.getDescription())
                    .price(product.getPrice())
                    .size(product.getSize())
                    .color(product.getColor())
                    .manufacturerId(product.getManufacturer().getId())
                    .build();
            productDtoList.add(productDto);
        }
        return productDtoList;
    }

    @Override
    public void deleteById(Long id) {
        repo.deleteById(id);
    }

    @Override
    public List<ProductDto> getProductsByManufacturerId(Long manufacturerId) {
        List<Product> products = repo.getProductsByManufacturerId(manufacturerId);
        List<ProductDto> productDtoList = new ArrayList<>();

        for (Product product: products) {
            ProductDto productDto = ProductDto.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .description(product.getDescription())
                    .price(product.getPrice())
                    .size(product.getSize())
                    .color(product.getColor())
                    .manufacturerId(product.getManufacturer().getId())
                    .build();
            productDtoList.add(productDto);
        }
        return productDtoList;
    }

    @Override
    public List<ProductDto> findProductByColor(String color) {
        List<Product> products = repo.findProductByColor(color);
        List<ProductDto> productDtoList = new ArrayList<>();

        for (Product product: products) {
            ProductDto productDto = ProductDto.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .description(product.getDescription())
                    .price(product.getPrice())
                    .size(product.getSize())
                    .color(product.getColor())
                    .manufacturerId(product.getManufacturer().getId())
                    .build();
            productDtoList.add(productDto);
        }
        return productDtoList;
    }

    @Override
    public List<ProductDto> findProductByCriteris(Double price, List<String> manufacturers, List<String> colors) {
        List<Product> products = repo.findAllByPriceGreaterThanAndManufacturerInAndColorIn(price, manufacturers, colors);
        List<ProductDto> productDtoList = new ArrayList<>();

        for (Product product: products) {
            ProductDto productDto = ProductDto.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .description(product.getDescription())
                    .price(product.getPrice())
                    .size(product.getSize())
                    .color(product.getColor())
                    .manufacturerId(product.getManufacturer().getId())
                    .build();
            productDtoList.add(productDto);
        }
        return productDtoList;
    }

    @Override
    public void decreaseStockQuantity(Product product, int quantity) {
        int currentStock = product.getQuantity();
        int updatedStock = currentStock - quantity;

        if (updatedStock < 0) {
            throw new IllegalArgumentException("Stock quantity cannot be negative");
        }
        product.setQuantity(updatedStock);
        repo.save(product);
    }
}
