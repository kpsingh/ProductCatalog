package com.lld4.productcatalog.controllers;

import com.lld4.productcatalog.dtos.CategoryDto;
import com.lld4.productcatalog.dtos.ProductDto;
import com.lld4.productcatalog.models.Product;
import com.lld4.productcatalog.services.IProductService;
import org.apache.coyote.BadRequestException;
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

    public ProductController(IProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable("id") Long productId) {
        try {
            // Initial validation of request
            if (productId == null || productId <= 0) {
                throw new BadRequestException("Invalid product id");
            }

            // we can add the custom headers as well so d it will appear in the response of this API
            MultiValueMap<String, String> customHeaders = new LinkedMultiValueMap<>();
            customHeaders.add("myheader", "This is my custom header");

            Product product = productService.getProduct(productId);
            if (product == null) {
                // no response received thence the body part id null.
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            ProductDto productDto = getProductDto(product);

            // the advantage of returning the ResponseEntity is that we can add the status and any other headers as we want but if we simply return the product then those options will not be available to us
            return new ResponseEntity<>(productDto, customHeaders, HttpStatus.OK);

        } catch (Exception e) {
            // no response received thence the body part id null.
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
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
            ProductDto productDto = getProductDto(product);
            productDtos.add(productDto);
        }

        return new ResponseEntity<>(productDtos, customHeaders, HttpStatus.OK);
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

        if (product.getCategory() != null) {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setId(product.getCategory().getId());
            categoryDto.setName(product.getCategory().getName());
            categoryDto.setDescription(product.getCategory().getDescription());
            productDto.setCategory(categoryDto);
        }

        return productDto;
    }

}
