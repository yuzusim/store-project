package com.example.storeproject.order;

import lombok.Data;

public class OrderRequest {

    @Data
    public static class OrderDTO {
        private Integer userId;
        private Integer status;
    }

    @Data
    public static class SaveDTO {
        private Integer userId;
        private Integer status;
    }

}
