package com.example.storeproject.order;

import com.example.storeproject.user.User;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderJPARepository orderJPARepo;
    private final EntityManager em;


    public void 구매하기(OrderRequest.SaveDTO reqDTO, User sessionUser) {
    }
}

