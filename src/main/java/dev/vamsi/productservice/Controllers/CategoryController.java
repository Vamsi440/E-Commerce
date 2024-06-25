package dev.vamsi.productservice.Controllers;

import dev.vamsi.productservice.Models.Category;
import dev.vamsi.productservice.Services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products/categories")
public class CategoryController {

    private CategoryService categoryService;
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping()
    public ResponseEntity<List<Category>> getAllCategories() {
        ResponseEntity<List<Category>> responseEntity = new ResponseEntity<>(
                categoryService.getAllCategories(),
                HttpStatus.OK
        );
        return responseEntity;
    }

    @GetMapping("/{category}")
    public String getCategory(@PathVariable("category") String category) {
        return "Returning category " + category;
    }
}
