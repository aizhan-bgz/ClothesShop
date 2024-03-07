package com.example.clothesshop.controller;

import com.example.clothesshop.dto.ManufacturerDto;
import com.example.clothesshop.dto.ProductDto;
import com.example.clothesshop.entity.Transaction;
import com.example.clothesshop.service.ManufacturerService;
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

@Tag(name = "Manufacturer", description = "API для производителей")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/manufacturer")
public class ManufacturerController {
    private final ManufacturerService service;

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Список всех производителей успешно получен",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ManufacturerDto.class)))})
    })
    @Operation(summary = "Просмотр списка всех производителей")
    @GetMapping("/getAll")
    public ResponseEntity<List<ManufacturerDto>> getAll() {
        List<ManufacturerDto> manufacturerDtoList = service.getAll();
        return  new ResponseEntity<>(manufacturerDtoList, HttpStatus.OK);
    }
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Производитель успешно найден",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ManufacturerDto.class)))})
    })
    @Operation(summary = "Поиск производителей по ID")
    @GetMapping("/{id}")
    public ResponseEntity<ManufacturerDto> getById(@PathVariable Long id) {
        ManufacturerDto manufacturerDto = service.findById(id);
        if (manufacturerDto != null) {
            return new ResponseEntity<>(manufacturerDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Производитель успешно создан",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ManufacturerDto.class)))})
    })
    @Operation(summary = "Создание производителей")
    @PostMapping()
    public ResponseEntity<ManufacturerDto> create (@RequestBody ManufacturerDto dto) {
        ManufacturerDto createdManufacturer =  service.createManufacturer(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdManufacturer);
    }

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Производитель успешно удален",
                    content = {
                            @Content(mediaType = "application/json")})
    })
    @Operation(summary = "Удаление производителей")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
