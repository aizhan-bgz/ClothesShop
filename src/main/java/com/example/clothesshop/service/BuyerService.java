package com.example.clothesshop.service;

import com.example.clothesshop.dto.BuyerDto;

import java.util.List;

public interface BuyerService {

    BuyerDto create (BuyerDto dto);
    List<BuyerDto> getAll();
    BuyerDto getById (Long id);
    void delete (Long id);


}
