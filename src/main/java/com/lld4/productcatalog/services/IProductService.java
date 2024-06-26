package com.lld4.productcatalog.services;

import com.lld4.productcatalog.models.Product;

import java.util.List;

public interface IProductService {

    Product getProduct(Long productId);

    List<Product> getAllProducts();

    Product createProduct(Product Product);
}
