package com.example.clothesshop.repo;

import com.example.clothesshop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    List<Product> getProductsByManufacturerId (Long manufacturerId);
    @Query("SELECT p FROM Product p WHERE p.color = :color")
    List<Product> findProductByColor(@Param("color") String color);


    @Query("""
        SELECT p FROM Product p
        WHERE p.price > :price
        AND p.manufacturer.name IN :manufacturers
        AND p.color IN :colors
        """)
    List<Product> findAllByPriceGreaterThanAndManufacturerInAndColorIn(
            @Param ("price") Double price,
            @Param ("manufacturers")List<String> manufacturers,
            @Param ("colors")List<String> colors);
}
