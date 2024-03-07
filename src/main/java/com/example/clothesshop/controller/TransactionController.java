package com.example.clothesshop.controller;

import com.example.clothesshop.dto.TransactionDto;
import com.example.clothesshop.entity.Transaction;
import com.example.clothesshop.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Transaction", description = "API для работы с транзакциями")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService service;

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Список всех транзакций успешно получен",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Transaction.class)))})
    })
    @Operation(summary = "Просмотр списка всех транзакций")
    @GetMapping
    public List<Transaction> getAll(){
        return service.getAll();
    }

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Список транзакций по продавцу успешно получен",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Transaction.class)))})
    })
    @Operation(summary = "Просмотр списка транзакций по продавцу")
    @GetMapping("/salesman/{id}")
    public List<Transaction> getAllBySalesmanId(@PathVariable Long id) {
        return service.getAllBySalesmanId(id);
    }

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Список транзакций по покупателю успешно получен",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Transaction.class)))})
    })
    @Operation(summary = "Просмотр списка транзакций по покупателю")
    @GetMapping("/buyer/{id}")
    public List<Transaction> getAllByBuyerId(@PathVariable Long id) {
        return service.getAllByBuyerId(id);
    }

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Транзакция успешно создана",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Transaction.class)))})
    })
    @Operation(summary = "Создание транзакции")
    @PostMapping
    public Transaction makeTransaction(TransactionDto dto) {
    return service.sellProduct(dto);
    }


}
