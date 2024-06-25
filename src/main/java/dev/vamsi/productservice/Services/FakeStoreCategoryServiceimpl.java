package dev.vamsi.productservice.Services;

import dev.vamsi.productservice.DTOs.FakeStoreCategoryDto;
import dev.vamsi.productservice.Models.Category;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreCategoryServiceimpl implements CategoryService{


    private RestTemplateBuilder restTemplateBuilder;
    public FakeStoreCategoryServiceimpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    private Category ConvertFakeStoreCategoryDtotoCategory(FakeStoreCategoryDto fakeStoreCategoryDto) {
        Category category = new Category();
        category.setName(fakeStoreCategoryDto.getCategory());
        return category;
    }
    @Override
    public List<Category> getAllCategories() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreCategoryDto[]> response = restTemplate.getForEntity(
                "https://fakestoreapi.com/products/categories",
                FakeStoreCategoryDto[].class
        );
        List<Category> categories = new ArrayList<>();
        for (FakeStoreCategoryDto dto : response.getBody()) {
            categories.add(ConvertFakeStoreCategoryDtotoCategory(dto));
        }
        return categories;
    }

    @Override
    public Category getCategory(String category) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreCategoryDto[]> response = restTemplate.getForEntity(
                "https://fakestoreapi.com/products/categories/{name}",
                FakeStoreCategoryDto[].class,
                category
        );

        return null;
    }
}
