package com.lld4.productcatalog.clients;

import com.lld4.productcatalog.dtos.FakeStoreProductDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class FakeStoreApiClient {

    RestTemplateBuilder restTemplateBuilder;

    public FakeStoreApiClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public FakeStoreProductDto getProductById(Long productId) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> fakeProductResponseEntity = restTemplate.getForEntity("https://fakestoreapi.com/products/{productId}", FakeStoreProductDto.class, productId);
        if (fakeProductResponseEntity != null && fakeProductResponseEntity.getStatusCode().is2xxSuccessful()) {
            return fakeProductResponseEntity.getBody();
        }
        return null;
    }

    public List<FakeStoreProductDto> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> fakeProductRestResponse = restTemplate.getForEntity("https://fakestoreapi.com/products", FakeStoreProductDto[].class);
        if (fakeProductRestResponse != null && fakeProductRestResponse.getStatusCode().is2xxSuccessful()) {
            return Arrays.asList(fakeProductRestResponse.getBody());
        }
        return null;
    }
}
