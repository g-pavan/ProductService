package com.productservice.productservice.services;

import com.productservice.productservice.dtos.FakeStoreProdutDto;
import com.productservice.productservice.dtos.GenericProductDto;

import java.util.List;

public interface ProductService {
    GenericProductDto getProductById(Long Id);

    List<GenericProductDto> getAllProducts();

    void deleteProductById();

    GenericProductDto createProduct(GenericProductDto genericProductDto);

    void updateProductById();
}
