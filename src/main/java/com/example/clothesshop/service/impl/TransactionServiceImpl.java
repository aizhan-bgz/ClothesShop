package com.example.clothesshop.service.impl;

import com.example.clothesshop.dto.TransactionDto;
import com.example.clothesshop.entity.Buyer;
import com.example.clothesshop.entity.Product;
import com.example.clothesshop.entity.Salesman;
import com.example.clothesshop.entity.Transaction;
import com.example.clothesshop.exception.NotFoundException;
import com.example.clothesshop.repo.BuyerRepo;
import com.example.clothesshop.repo.ProductRepo;
import com.example.clothesshop.repo.SalesmanRepo;
import com.example.clothesshop.repo.TransactionRepo;
import com.example.clothesshop.service.ProductService;
import com.example.clothesshop.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepo repo;
    private final SalesmanRepo salesmanRepo;
    private final BuyerRepo buyerRepo;
    private final ProductRepo productRepo;
    private final ProductService productService;

    @Override
    public List<Transaction> getAll() {
        return repo.findAll();
    }

    @Override
    public Transaction getById(Long id) {
        return repo.findById(id).orElseThrow(()-> new NotFoundException("Транзакция с ID " + id + " не найдена"));
    }
    @Override
    public Transaction sellProduct(TransactionDto transactionDto) {
        Salesman salesman = salesmanRepo.findById(transactionDto.getSalesmanID())
                .orElseThrow(() -> new NotFoundException("Salesman not found with ID: " + transactionDto.getSalesmanID()));
        Buyer buyer = buyerRepo.findById(transactionDto.getBuyerID())
                .orElseThrow(() -> new NotFoundException("Buyer not found with ID: " + transactionDto.getBuyerID()));
        Product product = productRepo.findById(transactionDto.getProductId())
                .orElseThrow(() -> new NotFoundException("Product not found with ID: " + transactionDto.getProductId()));
        if (product.getQuantity() < transactionDto.getQuantity()) {
            throw new IllegalArgumentException("Insufficient stock quantity");
        }
        else {
            Transaction transaction = new Transaction();
            transaction.setProduct(product);
            transaction.setBuyer(buyer);
            transaction.setSalesman(salesman);
            transaction.setQuantity(transactionDto.getQuantity());
            productService.decreaseStockQuantity(product, transaction.getQuantity());
            return repo.save(transaction);
        }
    }
    @Override
    public List<Transaction> getAllBySalesmanId(Long salesmanId) {
        return repo.findAllBySalesmanId(salesmanId);
    }
    @Override
    public List<Transaction> getAllByBuyerId(Long salesmanId) {
        return null;
    }
}
