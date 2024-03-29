package com.example.clothesshop.repo;

import com.example.clothesshop.entity.Salesman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesmanRepo extends JpaRepository<Salesman, Long> {
}
