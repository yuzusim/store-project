package com.example.storeproject.user;

import lombok.Data;

public class UserRequest {
    //회원가입수정
    @Data
    public static class UpdateDTO {
        private String username;
        private String password;
        private String phone;
        private String email;
        private String address;
    }

    //회원가입
    @Data
    public static class JoinDTO {
        private String username;
        private String password;
        private String phone;
        private String email;
        private String address;
        private Integer role;

        public User toEntity() { // 컨트롤러의 코드가 엄청나게 줄어 듬
            return User.builder()
                    .username(username) // shift + enter 줄 넘기기
                    .password(password)
                    .phone(phone)
                    .email(email)
                    .address(address)
                    .role(role)
                    .build();
        }

    }

    //로그인
    @Data
    public static class LoginDTO {
        private String username;
        private String password;
        private Integer role;
    }
}
