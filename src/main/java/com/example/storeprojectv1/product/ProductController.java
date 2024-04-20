package com.example.storeprojectv1.product;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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

        //User sessionUser = (User) session.getAttribute("sessionUser");
        productService.save(reqDTO);

        // 페이지 리턴
        return "redirect:/";
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
