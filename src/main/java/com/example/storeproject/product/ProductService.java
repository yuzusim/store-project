package com.example.storeproject.product;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductJPARepository productRepo;
    private final EntityManager em;

    //상품 목록보기 완료
    public List<Product> findAll() {
        Query query =
                em.createQuery("select p from Product p order by p.id asc", Product.class);
        return query.getResultList();
    }

    public Product findById(int id) {
        Product product = em.find(Product.class, id);
        return product;
    }

    //상품 등록 완료
    @Transactional
    public Product save(ProductRequest.SaveDTO reqDTO) {
        productRepo.save(reqDTO.toEntity());
        return Product.builder().build();
    }

    //상품 업데이트 완료
    @Transactional
    public void updeteById(int id, String name, Integer price, Integer qty) {
        Product product = findById(id);
        product.setName(name);
        product.setPrice(price);
        product.setQty(qty);
    }

    //상품 삭제 완료
    @Transactional
    public void deleteById(int id) {
        Query query =
                em.createQuery("delete from Product p where p.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    public Product findByname(String name) {
        Query query = em.createQuery("select p from Product p where p.name= :name", Product.class);
        query.setParameter("name", name);
        try {
            Product product = (Product) query.getSingleResult();
            return product;
        } catch (Exception e) {
            return null;
        }
    }


}
