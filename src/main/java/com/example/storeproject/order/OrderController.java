package com.example.storeproject.order;

import com.example.storeproject.user.User;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class OrderController {

    private final OrderService orderService;
    private final HttpSession session;

    @PostMapping("/reply/save")
    public String save(OrderRequest.SaveDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        orderService.구매하기(reqDTO, sessionUser);
        return "redirect:/product/" + reqDTO.getUserId();
    }
}
