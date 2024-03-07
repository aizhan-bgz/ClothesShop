package com.example.clothesshop.service.impl;

import com.example.clothesshop.dto.SalesmanDto;
import com.example.clothesshop.entity.Salesman;
import com.example.clothesshop.repo.SalesmanRepo;
import com.example.clothesshop.service.SalesmanService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SalesmanServiceImpl implements SalesmanService {
    private final SalesmanRepo repo;
    @Override
    public SalesmanDto createSalesman(SalesmanDto dto) {
        Salesman salesman = Salesman.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
//                .products(salesmanDto.getProducts())
                .build();
        try {
            repo.save(salesman);
        } catch (Exception e) {
            log.error(e.getStackTrace().toString());
        }
        return  dto;
    }

    @Override
    public  SalesmanDto getById(Long id) {
        Salesman salesman = repo.findById(id).orElseThrow(() -> new EntityNotFoundException("Salesman not found with id" + id));
        SalesmanDto salesmanDto = SalesmanDto.builder()
                .id(salesman.getId())
                .name(salesman.getName())
                .email(salesman.getEmail())
                .phoneNumber(salesman.getPhoneNumber())
                .build();
        return salesmanDto;
    }

    @Override
    public List<SalesmanDto> getAll() {
        List<Salesman> salesmanList = repo.findAll();
        List<SalesmanDto> salesmanDtoList = new ArrayList<>();
        for (Salesman salesman: salesmanList) {
            SalesmanDto salesmanDto = SalesmanDto.builder()
                    .id(salesman.getId())
                    .name(salesman.getName())
                    .email(salesman.getEmail())
                    .phoneNumber(salesman.getPhoneNumber())
                    .build();
            salesmanDtoList.add(salesmanDto);
        }
        return salesmanDtoList;
    }

    @Override
    public void deleteById(Long id) {
        repo.deleteById(id);
    }
}
