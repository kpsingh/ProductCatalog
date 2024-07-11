package com.lld4.productcatalog.repo;

import com.lld4.productcatalog.models.Category;
import com.lld4.productcatalog.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {


    public Optional<Category> findById(Long id);

    @Query("select p.name from Product p join Category c on c.id = p.category.id and c.id=:categoryId")
    public List<String> findAllProductByCategory(Long categoryId);
}
