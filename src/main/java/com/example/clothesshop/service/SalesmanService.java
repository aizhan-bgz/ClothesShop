package com.example.clothesshop.service;

import com.example.clothesshop.dto.SalesmanDto;
import com.example.clothesshop.entity.Salesman;

import java.util.List;

public interface SalesmanService {

    SalesmanDto createSalesman (SalesmanDto dto);
    SalesmanDto getById(Long id);
    List<SalesmanDto> getAll();
    void deleteById(Long id);
}
