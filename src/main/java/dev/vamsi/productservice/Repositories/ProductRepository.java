package dev.vamsi.productservice.Repositories;

import dev.vamsi.productservice.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {


}
