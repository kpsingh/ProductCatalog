package com.lld4.productcatalog.mappers;

import com.lld4.productcatalog.dtos.CategoryDto;
import com.lld4.productcatalog.dtos.ProductDto;
import com.lld4.productcatalog.models.Category;
import com.lld4.productcatalog.models.Product;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ProductMapper {

    public Product getProductFromProductDto(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId()); // setting id is important since autocreation of id set to be false
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setImageUrl(productDto.getImageUrl());
        product.setCreatedAt(new Date());
        product.setLastUpdatedAt(new Date());

        Category category = new Category();
        category.setId(productDto.getCategory().getId()); // setting id is important since autocreation of id set to be false
        category.setName(productDto.getCategory().getName());
        category.setDescription(productDto.getCategory().getDescription());
        category.setCreatedAt(new Date());
        category.setLastUpdatedAt(new Date());

        product.setCategory(category);
        return product;
    }

    public ProductDto getProductDtoFromProduct(Product product) {
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
