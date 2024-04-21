package com.example.storeproject.order;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@NoArgsConstructor
@Data
@Table(name = "order_tb")
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer userId;
    private Integer productId;
    private Integer role;
    private String state; //구매, 취소

    @CreationTimestamp
    private Timestamp createdAt;

    @Builder
    public Order(Integer id, Integer userId, Integer productId, Integer role, String state, Timestamp createdAt) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.role = role;
        this.state = state;
        this.createdAt = createdAt;
    }

    // 주문을 여러번 할수 있다.
//    @ManyToOne(fetch = FetchType.LAZY)
//    private User user;
//
//    // 구매자는 여러번 상품을 주문할 수 있다.
//    @ManyToOne(fetch = FetchType.LAZY)
//    private Product product;
//
//    @Transient
//    private boolean isOrderOwner;
//
//    private Integer status; // 1:구매, 2:취소
//
//    @Builder
//    public Order(Integer id, User user, Product product, boolean isOrderOwner, Integer status) {
//        this.id = id;
//        this.user = user;
//        this.product = product;
//        this.isOrderOwner = isOrderOwner;
//        this.status = status;
//    }

}
