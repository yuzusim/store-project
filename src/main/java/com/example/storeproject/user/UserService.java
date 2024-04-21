package com.example.storeproject.user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserJPARepository userJPARepo;
    private final EntityManager em;

//    public User findById(int id) {
//        User user = em.find(User.class, id); // em.find()는 데이터베이스에서 엔티티를 검색하는 데 사용
//        return user;
//
//    }

    public User findByUsernameAndPassword(UserRequest.LoginDTO reqDTO) {
        Query query = em.createQuery("select u from User u where u.username = :username and u.password = :password", User.class);
        query.setParameter("username", reqDTO.getUsername());
        query.setParameter("password", reqDTO.getPassword());
        return (User) query.getSingleResult();
    }


//    @Transactional
//    public int 회원가입(User user) {
//        String rawPassword = user.getPassword(); // 1234 원문
//        String encPassword = encoder.encode(rawPassword); // 해쉬
//        user.setPassword(encPassword);
//        user.setRole(RoleType.USER);
//        try {
//            userJPARepo.save(user);
//            return 1;
//        } catch (Exception e) {
//            return -1;
//        }
//
//    }

//    @Transactional
//    public void userSave(UserRequest.JoinDTO reqDTO) {
//        Query query = em.createNativeQuery("insert into user_tb(name, username, email, password, address, age, phone, role, created_at) values(?, ?, ?, ?, ?, ?, ?, ?, now()) ");
//        query.setParameter(1, reqDTO.getName());
//        query.setParameter(2, reqDTO.getUsername());
//        query.setParameter(3, reqDTO.getEmail());
//        query.setParameter(4, reqDTO.getPassword());
//        query.setParameter(5, reqDTO.getAddress());
//        query.setParameter(6, reqDTO.getAge());
//        query.setParameter(7, reqDTO.getPhone());
//        query.setParameter(8, 0);
//        query.executeUpdate();
//    }

//    public User findByUsername(String reqDTO) {
//        Query query =
//                em.createQuery("select u from User u where u.username = :username", User.class);
//        query.setParameter("username", reqDTO.getUsername());
//        try {
//            User user = (User) query.getSingleResult();
//            return user;
//        } catch (Exception e) {
//            return null;
//        }
//    }

    @Transactional
    public User save(User user) {
        em.persist(user);
        return user;
    }

//    @Transactional // db에 write 할때는 필수
//    public void save(UserRequest.JoinDTO reqDTO) {
//        Query query = em.createNativeQuery("insert into user_tb(username, password, phone, email, address, role, created_at) values(?,?,?,?,?,?, now())");
//        query.setParameter(1, reqDTO.getUsername());
//        query.setParameter(2, reqDTO.getPassword());
//        query.setParameter(3, reqDTO.getPhone());
//        query.setParameter(4, reqDTO.getEmail());
//        query.setParameter(5, reqDTO.getAddress());
//        query.setParameter(6, 2);
//        query.executeUpdate();
//    }

    public User findByUsername(String username) {
        Query query = em.createNativeQuery("select * from user_tb where username=?", User.class);
        query.setParameter(1, username);
        try {
            User user = (User) query.getSingleResult();
            return user;
        } catch (Exception e) {
            return null;
        }
    }


}
