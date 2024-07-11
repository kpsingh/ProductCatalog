package com.lld4.productcatalog.repo;

import com.lld4.productcatalog.models.Category;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryRepoTest {

    @Autowired
    private CategoryRepo categoryRepo;


    @Test
    @Transactional
    void findById() {
        Category category = categoryRepo.findById(1L).get();
        assertNotNull(category);
    }

    @Test
    @Transactional
    void findAllProductByCategory() {
        List<String> allProductByCategory = categoryRepo.findAllProductByCategory(1l);
        assertNotNull(allProductByCategory);
        for (String product : allProductByCategory) {
            System.out.println(product);

        }
    }
}