package com.example.storeproject.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJPARepository extends JpaRepository<User, Integer> {
    //Optional<User> findByUsername(String username);
}
