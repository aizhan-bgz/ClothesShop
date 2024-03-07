package com.example.clothesshop.controller;

import com.example.clothesshop.dto.ProductDto;
import com.example.clothesshop.entity.Transaction;
import com.example.clothesshop.service.ProductService;
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

@Tag(name = "Product", description = "API для продуктов")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService service;

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Список всех продуктов успешно получен",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ProductDto.class)))})
    })
    @Operation(summary = "Просмотр списка всех продуктов")
    @GetMapping("/getAll")
    public ResponseEntity<List<ProductDto>> getAll() {
        List<ProductDto> productDtoList = service.getAllProducts();
        return new ResponseEntity<>(productDtoList, HttpStatus.OK);
    }


    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Список продуктов по производителю (по ID) успешно получен",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ProductDto.class)))})
    })
    @Operation(summary = "Просмотр списка продуктов по производителю (по ID)")
    @GetMapping("/getByManufacturerId/{manufacturerId}")
    public ResponseEntity<List<ProductDto>> getProductsByManufacturerId(@PathVariable Long manufacturerId) {
        List<ProductDto> productDtoList = service.getProductsByManufacturerId(manufacturerId);
        return new ResponseEntity<>(productDtoList, HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Список продуктов по цвету продукта успешно получен",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ProductDto.class)))})
    })
    @Operation(summary = "Поиск и просмотр списка продуктов по цвету")
    @GetMapping("/getByColor")
    public ResponseEntity<List<ProductDto>> findProductByColor(@RequestParam String color){
        List<ProductDto> productDtoList = service.findProductByColor(color);
        return new ResponseEntity<>(productDtoList, HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Список продуктов по нескольким критериям успешно получен",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ProductDto.class)))})
    })
    @Operation(summary = "Поиск и просмотр списка продуктов по нескольким критериям")
    @GetMapping("/filterBy")
    public ResponseEntity<List<ProductDto>> getProductByCriteris(
            @RequestParam("price") Double price,
            @RequestParam("manufacturers") List<String> manufacturers,
            @RequestParam("colors")  List<String> colors
    ) {
        List<ProductDto> productDtoList = service.findProductByCriteris(price, manufacturers, colors );
        return new ResponseEntity<>(productDtoList, HttpStatus.OK);
    }


    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Продукт успешно найден",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ProductDto.class)))})
    })
    @Operation(summary = "Поиск продуктов по ID")
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> findById(@PathVariable Long id) {
        ProductDto productDto = service.findById(id);
        if (productDto != null) {
            return new ResponseEntity<>(productDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Продукт успешно создан",
                    content = {
                            @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ProductDto.class)))})
    })
    @Operation(summary = "Создание продуктов")
    @PostMapping()
    public ResponseEntity<ProductDto> create (@RequestBody ProductDto dto) {
        ProductDto createdProduct =  service.createProduct(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }


    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Продукт успешно удален",
                    content = {
                            @Content(mediaType = "application/json")})
    })
    @Operation(summary = "Удаление продуктов")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
