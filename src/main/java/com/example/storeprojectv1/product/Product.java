package com.example.storeprojectv1.product;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Table(name = "product_tb")
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, length = 20, nullable = false)
    private String name; //상품명

    @Column(nullable = false)
    private Integer price; //가격

    @Column(nullable = false)
    private Integer qty; //수량

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Builder
    public Product(Integer id, String name, Integer price, Integer qty, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.qty = qty;
        this.createdAt = createdAt;
    }
}
