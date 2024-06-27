package com.lld4.productcatalog.services;

import com.lld4.productcatalog.dtos.FakeStoreProductDto;
import com.lld4.productcatalog.models.Category;
import com.lld4.productcatalog.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements IProductService {

    private final RestTemplateBuilder restTemplateBuilder;

    public ProductService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public Product getProduct(Long productId) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity =
                restTemplate.getForEntity("https://fakestoreapi.com/products/{id}", FakeStoreProductDto.class, productId);

        if (fakeStoreProductDtoResponseEntity.getStatusCode().is2xxSuccessful() && fakeStoreProductDtoResponseEntity.getBody() != null) {
            // FakeStoreProductDto fakeStoreProductDto = fakeStoreProductDtoResponseEntity.getBody();
            return from(fakeStoreProductDtoResponseEntity.getBody());
        }
        return null;

    }

    @Override
    public List<Product> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> litOfProductEntity = restTemplate.getForEntity("https://fakestoreapi.com/products", FakeStoreProductDto[].class);

        if (litOfProductEntity.getStatusCode().is2xxSuccessful() && litOfProductEntity.getBody() != null) {
            FakeStoreProductDto[] fakeProductsDos = litOfProductEntity.getBody();
            List<Product> products = new ArrayList<>();
            for (FakeStoreProductDto fakeStoreProductDto : fakeProductsDos) {
                Product product = from(fakeStoreProductDto);
                products.add(product);
            }
            return products;
        }
        return null;
    }

    @Override
    public Product createProduct(Product Product) {
        return null;
    }

    private Product from(FakeStoreProductDto fakeStoreProductDto) {
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setName(fakeStoreProductDto.getTitle());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setImageUrl(fakeStoreProductDto.getImage());

        Category category = new Category();
        category.setName(fakeStoreProductDto.getCategory());

        product.setCategory(category);
        return product;

    }
}
