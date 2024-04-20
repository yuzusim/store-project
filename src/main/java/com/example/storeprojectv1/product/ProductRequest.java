package com.example.storeprojectv1.product;

import lombok.Data;

public class ProductRequest {
    @Data
    public static class UpdateDTO {
        private String name;
        private Integer price;
        private Integer qty;
    }

    @Data
    public static class SaveDTO {
        private String name;
        private Integer price;
        private Integer qty;

        public Product toEntity() {
            return Product.builder()
                    .name(name)
                    .price(price)
                    .qty(qty)
                    //.user(user)
                    .build();

        }

    }
}
