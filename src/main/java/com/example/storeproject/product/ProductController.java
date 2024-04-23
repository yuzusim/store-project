package com.example.storeproject.product;

import com.example.storeproject.user.User;
import com.example.storeproject.util.ApiUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Controller
public class ProductController {
    private final ProductRepository productRepository;
    private final ProductService productService;
    private final HttpSession session;


    // 상품목록보기
    @GetMapping({"/product", "/"})
    public String list(HttpServletRequest request) {
        List<Product> productList = productService.findAll();
        request.setAttribute("productList", productList);

        // 페이지 리턴
        return "product/list";
    }

    // 상품 상세보기
    @GetMapping("/product/{id}")
    public String detail(@PathVariable int id, HttpServletRequest request) {
        Product product = productService.findById(id);
        request.setAttribute("product", product);

        // 페이지 리턴
        return "product/detail";
    }

    // 상품 등록하기
    @GetMapping("/product/saveForm")
    public String saveForm() {
        return "product/saveForm";
    }

    @PostMapping("product/add")
    public String save(ProductRequest.SaveDTO reqDTO) {
//        System.out.println("name : "+name);
//        System.out.println("number : "+number);
//        System.out.println("qty : "+qty);

        User sessionUser = (User) session.getAttribute("sessionUser");
        productService.save(reqDTO.toEntity(sessionUser));

        // 페이지 리턴
        return "redirect:/";
    }

    @GetMapping("/api/name-same-check")
    public @ResponseBody ApiUtil<?> nameSameCheck(@RequestParam("name") String name) {
        Product product = productService.findByname(name);
        if (product == null) { // 상품 등록 해도 된다.
            return new ApiUtil<>(true);
        } else { // 상품 등록 하면 안된다.
            return new ApiUtil<>(false);
        }
    }

    // 상품 수정하기
    @GetMapping("/product/{id}/updateForm")
    public String updateForm(@PathVariable int id, HttpServletRequest request) {
        Product product = productService.findById(id);
        request.setAttribute("product", product);

        // 페이지 리턴
        return "product/updateForm";
    }

    @PostMapping("/product/{id}/update")
    public String update(@PathVariable int id, ProductRequest.UpdateDTO reqDTO) {
        productService.updeteById(id, reqDTO.getName(), reqDTO.getPrice(), reqDTO.getQty());
        // 페이지 리턴
        return "redirect:/product/" + id;
    }

    // 상품 삭제하기
    @PostMapping("/product/{id}/delete")
    public String delete(@PathVariable int id) {
        productService.deleteById(id);
        // 페이지 리턴
        return "redirect:/";
    }
}
