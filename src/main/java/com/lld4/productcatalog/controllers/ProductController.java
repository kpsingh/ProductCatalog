package com.lld4.productcatalog.controllers;

import com.lld4.productcatalog.dtos.CategoryDto;
import com.lld4.productcatalog.dtos.ProductDto;
import com.lld4.productcatalog.models.Category;
import com.lld4.productcatalog.models.Product;
import com.lld4.productcatalog.services.IProductService;
import com.lld4.productcatalog.services.ProductService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private IProductService productService;


    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable("id") Long productId) {
        try {
            if (productId == null || productId <= 0) {
                throw new BadRequestException("Invalid product id");
            }
            Product product = productService.getProduct(productId);
            if (product == null) {
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            ProductDto productDto = getProductDto(product);
            return new ResponseEntity<>(productDto, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/products")
    public List<ProductDto> getAllProducts() {
        return new ArrayList<>();
    }

    @PostMapping
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        return productDto;
    }

    @PutMapping("{id}")
    public ProductDto replaceProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
        return productDto;
    }

    private ProductDto getProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        productDto.setDescription(product.getDescription());
        productDto.setImageUrl(product.getImageUrl());

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(product.getCategory().getId());
        categoryDto.setName(product.getCategory().getName());
        categoryDto.setDescription(product.getCategory().getDescription());

        productDto.setCategory(categoryDto);
        return productDto;
    }

}
