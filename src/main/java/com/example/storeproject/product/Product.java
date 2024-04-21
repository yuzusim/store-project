package com.example.storeproject.product;

import com.example.storeproject.user.User;
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

    //여러개의 상품에 유저가 구매할 수 있다. N:1
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    //@OrderBy("id desc")
    //@OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    //private List<Order> orders = new ArrayList<>();

    @Transient // 테이블 생성이 안됨
    private boolean isproductOwner;

    @Builder
    public Product(Integer id, String name, Integer price, Integer qty, LocalDateTime createdAt, User user, boolean isproductOwner) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.qty = qty;
        this.createdAt = createdAt;
        this.user = user;
        //this.orders = orders;
        this.isproductOwner = isproductOwner;
    }
}
