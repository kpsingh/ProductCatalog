package com.lld4.productcatalog.services;

import com.lld4.productcatalog.models.Product;
import com.lld4.productcatalog.repo.ProductRepo;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StorageProductService implements IProductService {

    private ProductRepo productRepo;

    public StorageProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public Product getProduct(Long productId) {
        Product product = productRepo.findById(productId).orElseThrow(() -> new IllegalArgumentException("Product not found"));
        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> allProducts = productRepo.findAll();
        return allProducts;
    }

    @Override
    public Product createProduct(Product Product) {
        Product result = productRepo.save(Product);
        return result;
    }
}
