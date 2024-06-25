package dev.vamsi.productservice.Services;

import dev.vamsi.productservice.Clients.FakeStoreApi.FakeStoreClient;
import dev.vamsi.productservice.Clients.FakeStoreApi.FakeStoreProductDto;
import dev.vamsi.productservice.DTOs.ProductDto;
import dev.vamsi.productservice.Models.Category;
import dev.vamsi.productservice.Models.Product;
//import org.springframework.boot.web.client.RestTemplateBuilder;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
//import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
//import org.springframework.web.client.RequestCallback;
//import org.springframework.web.client.ResponseExtractor;
//import org.springframework.web.client.RestClientException;
//import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FakeStroreProductServiceimpl implements ProductService{

//    private RestTemplateBuilder restTemplateBuilder;
    private FakeStoreClient fakeStoreClient;
    public FakeStroreProductServiceimpl(FakeStoreClient fakeStoreClient) {
//        this.restTemplateBuilder = restTemplateBuilder;
        this.fakeStoreClient = fakeStoreClient;
    }

//    private <T> ResponseEntity<T> requestForEntity(HttpMethod httpMethod,String url, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
//        RestTemplate restTemplate = restTemplateBuilder.requestFactory
//                (HttpComponentsClientHttpRequestFactory.class).build();
//        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
//        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
//        return restTemplate.execute(url,httpMethod, requestCallback, responseExtractor, uriVariables);
//    }

    private Product ConvertFakeStoreProductDtoToProduct(FakeStoreProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setDescription(product.getDescription());
        product.setImageurl(productDto.getImage());
        Category category = new Category();
        category.setName(productDto.getCategory());
        product.setCategory(category);
        return product;
    }

    private FakeStoreProductDto ConvertProducttoFakestroreprodcutDto(Product product){
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setId(product.getId());
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setImage(product.getImageurl());
        fakeStoreProductDto.setCategory(product.getCategory().getName());
        return fakeStoreProductDto;
    }

    @Override
    public List<Product> getAllProducts() {
//        RestTemplate restTemplate = restTemplateBuilder.build();
//        ResponseEntity<FakeStoreProductDto[]> response = restTemplate.getForEntity
//                ("https://fakestoreapi.com/products", FakeStoreProductDto[].class);
        List<FakeStoreProductDto> fakeStoreProductDtos = fakeStoreClient.getAllProducts();
        List<Product> answer  = new ArrayList<>();
        for(FakeStoreProductDto productDto : fakeStoreProductDtos){
            answer.add(ConvertFakeStoreProductDtoToProduct(productDto));
        }
        return answer;
    }

    @Override
    public Optional<Product> getProductById(Long productId) {
//        RestTemplate restTemplate = restTemplateBuilder.build();
//        //responseentity will have status codes for future use like 2xx 3xx.,
//        ResponseEntity<FakeStoreProductDto> response =
//                restTemplate.getForEntity("https://fakestoreapi.com/products/{productId}", FakeStoreProductDto.class, productId);
//        FakeStoreProductDto productDto = response.getBody();
           FakeStoreProductDto productDto = fakeStoreClient.getProductById(productId);
        if(productDto == null){
            return Optional.empty();
        }
        return Optional.of(ConvertFakeStoreProductDtoToProduct(productDto));
    }

    @Override
    public Product addProduct(ProductDto product) {
//        RestTemplate restTemplate = restTemplateBuilder.build();
//        ResponseEntity<FakeStoreProductDto> response= restTemplate.postForEntity
//                ("https://fakestoreapi.com/products", product, FakeStoreProductDto.class);
        FakeStoreProductDto productDto = fakeStoreClient.addProduct(product);
        return ConvertFakeStoreProductDtoToProduct(productDto);
    }

    @Override
    public Product PatchProduct(Long productId, Product product) {
        //RestTemplate restTemplate = restTemplateBuilder.build();
          FakeStoreProductDto fakeStoreProductDto = ConvertProducttoFakestroreprodcutDto(product);
//        ResponseEntity<FakeStoreProductDto> response = requestForEntity(
//                HttpMethod.PATCH,
//                "https://fakestoreapi.com/products/{id}",
//                fakeStoreProductDto,
//                FakeStoreProductDto.class,
//                productId
//        );
        FakeStoreProductDto fakeStoreProductDto1 = fakeStoreClient.PatchProduct(productId,fakeStoreProductDto);
        return ConvertFakeStoreProductDtoToProduct(fakeStoreProductDto1);
    }

    @Override
    public Product PutProduct(Long productId, Product product) {
        //RestTemplate restTemplate = restTemplateBuilder.build();
        FakeStoreProductDto fakeStoreProductDto = ConvertProducttoFakestroreprodcutDto(product);
//        ResponseEntity<FakeStoreProductDto> response = requestForEntity(
//                HttpMethod.PUT,
//                "https://fakestoreapi.com/products/{id}",
//                fakeStoreProductDto,
//                FakeStoreProductDto.class,
//                productId
//        );
        FakeStoreProductDto fakeStoreProductDto1 = fakeStoreClient.PutProduct(productId,fakeStoreProductDto);
        return ConvertFakeStoreProductDtoToProduct(fakeStoreProductDto1);
    }

    @Override
    public Product DeleteProduct(Long productId) {
//          RestTemplate restTemplate =restTemplateBuilder.build();
//        ResponseEntity<FakeStoreProductDto> response = requestForEntity(
//                HttpMethod.DELETE,
//                "https://fakestoreapi.com/products/{id}",
//                null,
//                FakeStoreProductDto.class,
//                productId
//        );
        FakeStoreProductDto fakeStoreProductDto = fakeStoreClient.DeleteProduct(productId);
        return ConvertFakeStoreProductDtoToProduct(fakeStoreProductDto);
    }
}
