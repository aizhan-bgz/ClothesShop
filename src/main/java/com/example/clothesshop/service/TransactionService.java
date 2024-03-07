package com.example.clothesshop.service;

import com.example.clothesshop.dto.TransactionDto;
import com.example.clothesshop.entity.Transaction;

import java.util.List;

public interface TransactionService {

    List<Transaction> getAll();
    Transaction getById(Long id);

    Transaction sellProduct (TransactionDto transactionDto);

    List<Transaction> getAllBySalesmanId(Long salesmanId);
    List<Transaction> getAllByBuyerId(Long salesmanId);
}
