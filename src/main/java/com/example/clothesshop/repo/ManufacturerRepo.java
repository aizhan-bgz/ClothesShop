package com.example.clothesshop.repo;

import com.example.clothesshop.entity.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManufacturerRepo extends JpaRepository <Manufacturer, Long> {
}
