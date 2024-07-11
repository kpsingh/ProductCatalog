package com.lld4.productcatalog.repo;

import com.lld4.productcatalog.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

    Optional<Product> findById(Long aLong);

    List<Product> findByPriceBetween(Double lower, Double higher);

    List<Product> findProductByIsPrimeSpecific(Boolean isPrime);

}
