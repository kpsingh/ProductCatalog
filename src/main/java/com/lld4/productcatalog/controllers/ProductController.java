package com.lld4.productcatalog.controllers;

import com.lld4.productcatalog.dtos.ProductDto;
import com.lld4.productcatalog.models.Product;
import com.lld4.productcatalog.services.IProductService;
import com.lld4.productcatalog.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    private IProductService productService;


    @GetMapping("/products/{id}")
    public ProductDto getProduct(@PathVariable("id") Long productId) {
        Product product = productService.getProduct(productId);
        return getProductDto(product);
    }

    private ProductDto getProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        return productDto;
    }
}
