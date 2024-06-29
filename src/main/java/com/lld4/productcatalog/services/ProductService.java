package com.lld4.productcatalog.services;

import com.lld4.productcatalog.clients.FakeStoreApiClient;
import com.lld4.productcatalog.dtos.FakeStoreProductDto;
import com.lld4.productcatalog.mappers.FakeStoreProductMapper;
import com.lld4.productcatalog.models.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements IProductService {

    private final FakeStoreApiClient fakeStoreApiClient;
    private final RestTemplateBuilder restTemplateBuilder;
    private final FakeStoreProductMapper fakeStoreProductMapper;

    public ProductService(RestTemplateBuilder restTemplateBuilder, FakeStoreApiClient fakeStoreApiClient, FakeStoreProductMapper fakeStoreProductMapper) {
        this.restTemplateBuilder = restTemplateBuilder;
        this.fakeStoreApiClient = fakeStoreApiClient;
        this.fakeStoreProductMapper = fakeStoreProductMapper;
    }

    @Override
    public Product getProduct(Long productId) {
        FakeStoreProductDto fakeStoreProductDto = fakeStoreApiClient.getProductById(productId);
        if (fakeStoreProductDto != null) {
            Product product = fakeStoreProductMapper.getProductFromDto(fakeStoreProductDto);
            return product;
        }
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        List<FakeStoreProductDto> fakeStoreProductDtos = fakeStoreApiClient.getAllProducts();
        List<Product> products = new ArrayList<>();
        if (fakeStoreProductDtos != null) {
            for (FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos) {
                Product product = fakeStoreProductMapper.getProductFromDto(fakeStoreProductDto);
                products.add(product);
            }
            return products;
        }
        return null;
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }

}
