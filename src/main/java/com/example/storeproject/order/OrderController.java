package com.example.storeproject.order;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class OrderController {

    private final OrderService orderService;
    private final HttpSession session;

    // 구매 목록보기
    @GetMapping({"/order/1/orderList"})
    public String list(HttpServletRequest request){
        List<Order> orderList = orderService.findAll();
        request.setAttribute("orderList", orderList);
        return "order/orderList";
    }

}
