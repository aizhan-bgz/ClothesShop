package com.example.clothesshop.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Salesman salesman;

    @ManyToOne
    private Buyer buyer;

    @ManyToOne
    private Product product;

    private Integer quantity;


    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", salesman=" + salesman +
                ", buyer=" + buyer +
                ", product=" + product +
                ", quantity=" + quantity +
                '}';
    }

}
