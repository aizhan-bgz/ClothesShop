package com.example.clothesshop.service.impl;

import com.example.clothesshop.dto.ManufacturerDto;
import com.example.clothesshop.entity.Manufacturer;
import com.example.clothesshop.repo.ManufacturerRepo;
import com.example.clothesshop.service.ManufacturerService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ManufacturerServiceImpl implements ManufacturerService {
    private  final ManufacturerRepo repo;

    @Override
    public ManufacturerDto createManufacturer(ManufacturerDto dto) {
        Manufacturer manufacturer = Manufacturer.builder()
                .name(dto.getName())
                .contactInfo(dto.getContactInfo())
                .address(dto.getAddress())
                .build();
        try{
            repo.save(manufacturer);
        } catch (Exception e){
            log.error(e.getStackTrace().toString());
        }
        return dto;
    }

    @Override
    public ManufacturerDto findById(Long id) {
        Manufacturer manufacturer = repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Manufacturer not found with id" + id));
        ManufacturerDto manufacturerDto = ManufacturerDto.builder()
                .id(manufacturer.getId())
                .name(manufacturer.getName())
                .contactInfo(manufacturer.getContactInfo())
                .address(manufacturer.getAddress())
                .build();
        return manufacturerDto;
        }

    @Override
    public Manufacturer findEntityById(Long id) {
        return repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Manufacturer not found with id" + id));
    }

    @Override
    public List<ManufacturerDto> getAll() {
        List<Manufacturer> manufacturers = repo.findAll();
        List<ManufacturerDto> manufacturerDtoList = new ArrayList<>();

        for (Manufacturer manufacturer: manufacturers) {
            ManufacturerDto manufacturerDto = ManufacturerDto.builder()
                    .id(manufacturer.getId())
                    .name(manufacturer.getName())
                    .contactInfo(manufacturer.getContactInfo())
                    .address(manufacturer.getAddress())
                    .build();
            manufacturerDtoList.add(manufacturerDto);
        }
        return manufacturerDtoList;
    }

    @Override
    public void deleteById(Long id) {
        repo.deleteById(id);
        log.info("Manufacturer with id "+ id + " is deleted");
    }
}
