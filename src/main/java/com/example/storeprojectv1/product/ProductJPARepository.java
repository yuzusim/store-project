package com.example.storeprojectv1.product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductJPARepository extends JpaRepository<Product, Integer> {
    
}
