package com.example.storeproject.product;

import com.example.storeproject._core.common.ImgSaveUtil;
import com.example.storeproject.user.User;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

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
        private MultipartFile img;

        public Product toEntity(User user) {
            String imgFileName = ImgSaveUtil.save(img);
            return Product.builder()
                    .img(imgFileName)
                    .name(name)
                    .price(price)
                    .qty(qty)
                    .user(user)
                    .build();
        }

    }
}
