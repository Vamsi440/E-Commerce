package dev.vamsi.productservice.Services;

import dev.vamsi.productservice.DTOs.ProductDto;
import dev.vamsi.productservice.Models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAllProducts();
    Optional<Product> getProductById(Long productId);
    Product addProduct(ProductDto productDto);
    Product PatchProduct(Long productId, Product product);
    Product PutProduct(Long productId, Product product);
    Product DeleteProduct(Long productId);
}
