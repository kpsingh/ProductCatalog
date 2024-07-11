package com.lld4.productcatalog.repo;

import com.lld4.productcatalog.models.Product;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Query;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductRepoTest {

    @Autowired
    private ProductRepo productRepo;



    @Test
    @Transactional
    void findByPriceBetween() {
        List<Product> productList = productRepo.findByPriceBetween(1D, 1000D);
        Collections.sort(productList, (p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice()));
        for(Product product : productList) {
            System.out.println(product.getName() + " " + product.getPrice());
        }
    }

    @Test
    @Transactional
    public  void testFindProductByIsPrime(){
        List<Product> productList = productRepo.findProductByIsPrimeSpecific(Boolean.TRUE);
        //Collections.sort(productList, (p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice()));
        for(Product product : productList) {
            System.out.println(product.getName() + " " + product.getIsPrimeSpecific());
        }
    }

    @Test
    @Transactional
    public void findAllProductByPriceDesc(){
        List<Product> productList = productRepo.findAllByOrderByPriceDesc();
        for(Product product : productList) {
            System.out.println(product.getName() + " : " + product.getPrice() + " : " + product.getCategory().getName());
        }
    }

    @Test
    @Transactional
    public void testFindProductNameById(){
        String productName = productRepo.findProductNameFromId(12L);
        assertNotNull(productName);
        System.out.println(productName);

    }

    @Test
    @Transactional
    public void testFindCategoryNameFromProductId(){
        String categoryName = productRepo.findCategoryNameFromProductId(12L);
        assertNotNull(categoryName);
        System.out.println(categoryName);
    }

}