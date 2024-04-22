package com.example.storeproject.order;

import com.example.storeproject.product.Product;
import com.example.storeproject.user.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderJPARepository orderJPARepo;
    private final EntityManager em;


    //
    public List<Order> findAll() {
        Query query =
                em.createQuery("select o from Order o order by o.id asc", Order.class);
        return query.getResultList();
        //
    }
}

