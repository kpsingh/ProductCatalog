package com.lld4.productcatalog.controllers;

import com.lld4.productcatalog.dtos.CategoryDto;
import com.lld4.productcatalog.dtos.ProductDto;
import com.lld4.productcatalog.mappers.ProductMapper;
import com.lld4.productcatalog.models.Category;
import com.lld4.productcatalog.models.Product;
import com.lld4.productcatalog.services.IProductService;
import com.netflix.discovery.DiscoveryClient;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    private final IProductService productService;
    private final ProductMapper productMapper;

    private final RestTemplate restTemplate;
    private final RestClient restClient;

    private DiscoveryClient discoveryClient;

    public ProductController(@Qualifier("storageProductService") IProductService productService, ProductMapper productMapper, RestTemplate restTemplate, RestClient restClient) {
        this.productService = productService;
        this.productMapper = productMapper;
        this.restTemplate = restTemplate;
        this.restClient = restClient;
    }


    @GetMapping("/products/{id}")
    public ResponseEntity<String> getProduct(@PathVariable("id") Long productId) {
        try {
            // Initial validation of request
            if (productId == null || productId <= 0) {
                throw new IllegalArgumentException("Invalid product id");
            }

            // Example of RestTemplate
            //ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://PaymentService/payments/5", String.class);
            // return responseEntity;

            /* example of RestClient -
                    RestClient is the preferred choice for new Spring Boot projects due to its improved API and active development.
                    It offers a more concise and modern approach to making HTTP requests.

                    https://www.baeldung.com/spring-cloud-netflix-eureka
             */
            //String response = restClient.get().uri("http://localhost:3032/payments/5").retrieve().body(String.class);
            String response = restClient.get().uri("http://PaymentService/payments/5").retrieve().body(String.class);
            return new ResponseEntity<>(response, HttpStatus.OK);


            // we can add the custom headers as well so d it will appear in the response of this API
            //  MultiValueMap<String, String> customHeaders = new LinkedMultiValueMap<>();
            //  customHeaders.add("myheader", "This is my custom header");

            //  Product product = productService.getProduct(productId);
            //  ProductDto productDto = productMapper.getProductDtoFromProduct(product);
            // the advantage of returning the ResponseEntity is that we can add the status and any other headers as we want but if we simply return the product then those options will not be available to us
            // return new ResponseEntity<>(productDto, customHeaders, HttpStatus.OK);

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
