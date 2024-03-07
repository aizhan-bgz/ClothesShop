package com.example.clothesshop.dto;

import com.example.clothesshop.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalesmanDto {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private List<Product> products;
}
