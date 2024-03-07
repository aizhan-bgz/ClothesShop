package com.example.clothesshop.controller;

import com.example.clothesshop.dto.SalesmanDto;
import com.example.clothesshop.entity.Transaction;
import com.example.clothesshop.service.SalesmanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Salesman", description = "API для продавцов")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/salesman")
public class SalesmanController {
    private final SalesmanService service;

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Список всех продавцов успешно получен",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = SalesmanDto.class)))})
    })
    @Operation(summary = "Просмотр списка всех продавцов")
    @GetMapping("/getAll")
    public ResponseEntity<List<SalesmanDto>> getAll() {
        List<SalesmanDto> salesmanDtoList = service.getAll();
        return new ResponseEntity<>(salesmanDtoList, HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Продавец успешно найден",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = SalesmanDto.class)))})
    })
    @Operation(summary = "Поиск продавца по ID")
    @GetMapping("/{id}")
    public ResponseEntity<SalesmanDto> getById(@PathVariable Long id) {
        SalesmanDto salesmanDto = service.getById(id);
        if (salesmanDto != null) {
            return new ResponseEntity<>(salesmanDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Продавец успешно создан",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = SalesmanDto.class)))})
    })
    @Operation(summary = "Создание продавца")
    @PostMapping()
    public ResponseEntity<SalesmanDto> create (@RequestBody SalesmanDto dto) {
        SalesmanDto createdSalesman =  service.createSalesman(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSalesman);
    }

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Продавец успешно удален",
                    content = {
                            @Content(mediaType = "application/json")})
    })
    @Operation(summary = "Удаление продавца")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
