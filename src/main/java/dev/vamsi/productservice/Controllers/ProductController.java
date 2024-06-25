package dev.vamsi.productservice.Controllers;

import dev.vamsi.productservice.DTOs.Errorresponsedto;
import dev.vamsi.productservice.DTOs.ProductDto;
import dev.vamsi.productservice.Exceptions.NotFoundException;
import dev.vamsi.productservice.Models.Category;
import dev.vamsi.productservice.Models.Product;
import dev.vamsi.productservice.Services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    private Product ConvertProductDtotoProduct(ProductDto productDto){
        Product product = new Product();
        Category category = new Category();
        category.setName(productDto.getCategory());
        product.setId(productDto.getId());
        product.setCategory(category);
        product.setPrice(productDto.getPrice());
        product.setTitle(productDto.getTitle());
        product.setDescription(productDto.getDescription());
        product.setImageurl(productDto.getImage());
        return product;
    }

    @GetMapping()
    public ResponseEntity<List<Product>> getAllProducts(){
        ResponseEntity<List<Product>> responseEntity = new
                ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable("productId") Long productId) throws NotFoundException {
        Optional<Product> productOptional = productService.getProductById(productId);
        if(productOptional.isEmpty()){
            throw new NotFoundException("No product found with id " + productId);
        }
        ResponseEntity<Product> response = new ResponseEntity(
                productService.getProductById(productId),
                HttpStatus.OK);
        return response;
    }

    @PostMapping()
    public ResponseEntity<Product> addProduct(@RequestBody ProductDto productDto){
        ResponseEntity<Product> response = new ResponseEntity<>(
                productService.addProduct(productDto),
                HttpStatus.CREATED
        );
        return response;
    }

    @PatchMapping("/{productId}")
    public ResponseEntity<Product> PatchProduct(@PathVariable("productId") Long productId, @RequestBody ProductDto productDto){
        Product product = ConvertProductDtotoProduct(productDto);
        ResponseEntity<Product> response = new ResponseEntity<>(
                productService.PatchProduct(productId,product),
                HttpStatus.OK
        );
        return response;
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Product> PutProduct(@PathVariable("productId") Long productId, @RequestBody ProductDto productDto){
        Product product = ConvertProductDtotoProduct(productDto);
        ResponseEntity<Product> response = new ResponseEntity<>(
                productService.PutProduct(productId,product),
                HttpStatus.OK
        );
        return response;
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Product> DeleteProduct(@PathVariable("productId") Long productId){
        ResponseEntity<Product> response = new ResponseEntity<>(
                productService.DeleteProduct(productId),
                HttpStatus.OK
        );
        return response;
    }

//    @ExceptionHandler(NotFoundException.class)
//    public ResponseEntity<Errorresponsedto> HandleNotFoundException(Exception exception){
//        Errorresponsedto errorresponsedto = new Errorresponsedto();
//        errorresponsedto.setErrorMessage(exception.getMessage());
//        return new ResponseEntity<>(errorresponsedto, HttpStatus.NOT_FOUND);
//    }
}
