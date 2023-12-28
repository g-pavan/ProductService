package com.productservice.productservice.services;


import com.productservice.productservice.dtos.FakeStoreProdutDto;
import com.productservice.productservice.dtos.GenericProductDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("fakeProductService")
public class FakeStoreProductService implements ProductService{
    private RestTemplateBuilder restTemplateBuilder;
    private String specificProductUrl = "https://fakestoreapi.com/products/{id}";
    private String geniricProductUrl = "https://fakestoreapi.com/products/";

    FakeStoreProductService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }

    private static GenericProductDto convertToGenericProductDto(FakeStoreProdutDto fakeStoreProdutDto){
        GenericProductDto genericProductDto = new GenericProductDto();
        genericProductDto.setId(fakeStoreProdutDto.getId());
        genericProductDto.setCategory(fakeStoreProdutDto.getCategory());
        genericProductDto.setDescription(fakeStoreProdutDto.getDescription());
        genericProductDto.setImage(fakeStoreProdutDto.getImage());
        genericProductDto.setPrice(fakeStoreProdutDto.getPrice());
        genericProductDto.setTitle(fakeStoreProdutDto.getTitle());

        return genericProductDto;
    }
    @Override
    public GenericProductDto getProductById(Long id) {

        // Integrate the FakeStore API
        // RestTemplate
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProdutDto> responseEntity =  restTemplate.getForEntity(specificProductUrl, FakeStoreProdutDto.class, id);

        // Convert FakeStoreProductDto to GenericProductDto before returning


        return convertToGenericProductDto(responseEntity.getBody());
    }

    @Override
    public List<GenericProductDto> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<FakeStoreProdutDto[]> responseEntity = restTemplate.getForEntity(geniricProductUrl, FakeStoreProdutDto[].class);

        List<GenericProductDto> result = new ArrayList<>();
        List<FakeStoreProdutDto> fakeStoreProdutDtos = List.of(responseEntity.getBody());
        for(FakeStoreProdutDto fakeStoreProdutDto: fakeStoreProdutDtos){
            result.add(convertToGenericProductDto(fakeStoreProdutDto));
        }

        return result;
    }

    @Override
    public void deleteProductById() {

    }

    @Override
    public GenericProductDto createProduct(GenericProductDto genericProductDto) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<FakeStoreProdutDto> responseEntity =  restTemplate.postForEntity(geniricProductUrl, genericProductDto, FakeStoreProdutDto.class);


        return convertToGenericProductDto(responseEntity.getBody());
    }

    @Override
    public void updateProductById() {

    }
}
