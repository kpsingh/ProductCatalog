package com.lld4.productcatalog.repo;

import com.lld4.productcatalog.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

    Optional<Product> findById(Long aLong);

    List<Product> findByPriceBetween(Double lower, Double higher);

    List<Product> findProductByIsPrimeSpecific(Boolean isPrime);

    List<Product> findAllByOrderByPriceDesc();


    @Query("select p.name from Product p WHERE p.id=?1") // positional association
    String findProductNameFromId(Long id);

    // read the category name from product id using named association
    @Query("select c.name from Product p join Category c on p.category.id = c.id and p.id=:productId") // named association
    String findCategoryNameFromProductId(Long productId);



}
