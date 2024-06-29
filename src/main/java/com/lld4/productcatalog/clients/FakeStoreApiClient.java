package com.lld4.productcatalog.clients;

import com.lld4.productcatalog.dtos.FakeStoreProductDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
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
        // ResponseEntity<FakeStoreProductDto> fakeProductResponseEntity = restTemplate.getForEntity("https://fakestoreapi.com/products/{productId}", FakeStoreProductDto.class, productId);
        ResponseEntity<FakeStoreProductDto> fakeProductResponseEntity = requestForEntity(HttpMethod.GET, "https://fakestoreapi.com/products/{id}", null, FakeStoreProductDto.class, productId);
        if (fakeProductResponseEntity != null && fakeProductResponseEntity.getStatusCode().is2xxSuccessful()) {
            return fakeProductResponseEntity.getBody();
        }
        return null;
    }

    public List<FakeStoreProductDto> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> fakeProductRestResponse = restTemplate.getForEntity("https://fakestoreapi.com/products", FakeStoreProductDto[].class);
        // ResponseEntity<FakeStoreProductDto> fakeProductRestResponse = requestForEntity(HttpMethod.GET, "https://fakestoreapi.com/products", null, FakeStoreProductDto.class);
        if (fakeProductRestResponse != null && fakeProductRestResponse.getStatusCode().is2xxSuccessful()) {
            return Arrays.asList(fakeProductRestResponse.getBody());
        }
        return null;
    }

    // Our own custom method to call the rest request, it can take method type as an argument can ca be used to call all type like GET, POST, PUT, DELETE in one place
    public <T> ResponseEntity<T> requestForEntity(HttpMethod httpMethod, String url, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }
}
