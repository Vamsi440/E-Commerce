package dev.vamsi.productservice.Services;

import dev.vamsi.productservice.Models.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();
    Category getCategory(String category);
}
