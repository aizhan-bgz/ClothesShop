package com.example.clothesshop.service.impl;

import com.example.clothesshop.dto.BuyerDto;
import com.example.clothesshop.entity.Buyer;
import com.example.clothesshop.exception.NotFoundException;
import com.example.clothesshop.repo.BuyerRepo;
import com.example.clothesshop.service.BuyerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BuyerServiceImpl implements BuyerService {
    private final BuyerRepo repo;

    @Override
    public BuyerDto create(BuyerDto dto) {
        Buyer buyer = Buyer.builder()
                .name(dto.getName())
                .surname(dto.getSurname())
                .build();
        repo.save(buyer);
        dto.setId(buyer.getId());
        return dto;
    }

    @Override
    public BuyerDto getById(Long id) {
        Buyer buyer = repo.findById(id).orElseThrow(() -> new NotFoundException("Покупатель с ID " + id + " не найден"));
        BuyerDto dto = BuyerDto.builder()
                .id(buyer.getId())
                .name(buyer.getName())
                .surname(buyer.getSurname())
                .build();
        return dto;
    }

    @Override
    public List<BuyerDto> getAll() {
        List<Buyer> buyers = repo.findAll();
        List<BuyerDto> dtoList = new ArrayList<>();

        for (Buyer buyer: buyers) {
            BuyerDto dto = BuyerDto.builder()
                    .id(buyer.getId())
                    .name(buyer.getName())
                    .surname(buyer.getSurname())
                    .build();
            dtoList.add(dto);
        }
        return dtoList;
    }


    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
