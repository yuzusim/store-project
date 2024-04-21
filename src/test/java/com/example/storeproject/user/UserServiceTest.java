package com.example.storeproject.user;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@Import(UserService.class)
@DataJpaTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private EntityManager em;

    @Test
    public void findByUsernameAndPassword_test() {
        // given
        String username = "심유주";
        String password = "1234";

//        // when
//        User user = userService.findByUsernameAndPassword(username, password);

        // then
//        Assertions.assertThat(user.getUsername()).isEqualTo("심유주");

//        if (user == null) {
//            System.out.println("아이디 혹은 로그인이 틀렸습니다.");
//        } else {
//            if (user.getPassword().equals(password)) {
//                System.out.println("login_test : 로그인이 되었습니다");
//            } else {
//                System.out.println("login_test : 비밀번호가 되었습니다");
//            }
//        }

    }
}
