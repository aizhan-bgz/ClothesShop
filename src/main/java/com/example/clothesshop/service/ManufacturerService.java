package com.example.clothesshop.service;

import com.example.clothesshop.dto.ManufacturerDto;
import com.example.clothesshop.entity.Manufacturer;
import java.util.List;

public interface ManufacturerService {

    ManufacturerDto createManufacturer (ManufacturerDto dto);
    ManufacturerDto findById(Long id);
    Manufacturer findEntityById(Long id);
    List<ManufacturerDto> getAll();
    void deleteById(Long id);

}
