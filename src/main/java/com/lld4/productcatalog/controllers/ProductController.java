package com.lld4.productcatalog.controllers;

import com.lld4.productcatalog.dtos.CategoryDto;
import com.lld4.productcatalog.dtos.ProductDto;
import com.lld4.productcatalog.mappers.ProductMapper;
import com.lld4.productcatalog.models.Category;
import com.lld4.productcatalog.models.Product;
import com.lld4.productcatalog.services.IProductService;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    private final IProductService productService;
    private final ProductMapper productMapper;

    public ProductController(@Qualifier("storageProductService") IProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }


    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable("id") Long productId) {
        try {
            // Initial validation of request
            if (productId == null || productId <= 0) {
                throw new IllegalArgumentException("Invalid product id");
            }

            // we can add the custom headers as well so d it will appear in the response of this API
            MultiValueMap<String, String> customHeaders = new LinkedMultiValueMap<>();
            customHeaders.add("myheader", "This is my custom header");

            Product product = productService.getProduct(productId);
            ProductDto productDto = productMapper.getProductDtoFromProduct(product);
            // the advantage of returning the ResponseEntity is that we can add the status and any other headers as we want but if we simply return the product then those options will not be available to us
            return new ResponseEntity<>(productDto, customHeaders, HttpStatus.OK);

        } catch (IllegalArgumentException e) {
            // this will be read from ControllerAdvice handleException method and message will be taken care.
            throw e;
        }
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        if (products == null) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        MultiValueMap<String, String> customHeaders = new LinkedMultiValueMap<>();
        customHeaders.add("myheader", "This is my custom header GetAllProducts");

        List<ProductDto> productDtos = new ArrayList<>();
        for (Product product : products) {
            ProductDto productDto = productMapper.getProductDtoFromProduct(product);
            productDtos.add(productDto);
        }

        return new ResponseEntity<>(productDtos, customHeaders, HttpStatus.OK);
    }

    @PostMapping("/products")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        Product product = productMapper.getProductFromProductDto(productDto);
        productService.createProduct(product);
        return new ResponseEntity<>(productDto, HttpStatus.CREATED);
    }


    @PutMapping("{id}")
    public ProductDto replaceProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
        return productDto;
    }

}
