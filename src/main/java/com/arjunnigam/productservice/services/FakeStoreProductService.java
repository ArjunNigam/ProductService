package com.arjunnigam.productservice.services;

import com.arjunnigam.productservice.exceptions.ProductNotFoundException;
import com.arjunnigam.productservice.models.Category;
import com.arjunnigam.productservice.models.Product;
import com.arjunnigam.productservice.dtos.FakeStoreProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.data.domain.Pageable;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("fakeStoreProductService")  // Name of the bean is fakeStoreProductService
public class FakeStoreProductService implements ProductService{
    private RestTemplate restTemplate;
    private RedisTemplate<String, Object> redisTemplate;

    public FakeStoreProductService(RestTemplate restTemplate, RedisTemplate<String, Object> redisTemplate)
    {
        this.restTemplate = restTemplate;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public List<Product> getAllProducts() {


        ResponseEntity<FakeStoreProductDto[]> listResponseEntity = restTemplate.getForEntity("https://fakestoreapi.com/products",
                                                                                                   FakeStoreProductDto[].class);
        FakeStoreProductDto[] fakeStoreProductDtoList = listResponseEntity.getBody();
        List<Product> products = new ArrayList<>();
        for(FakeStoreProductDto dto : fakeStoreProductDtoList)
        {
            products.add(convertFakeStoreProductDtoToProduct(dto));
        }
        return products;
    }

    @Override
    public Product getSingleProduct(Long productId) throws ProductNotFoundException {
        // make an HTTP call to fakestore api to get the product with the given ID.
        // https://fakestoreapi/cim/products/1
        //  RestTemplate restTemplate = new RestTemplate();


        // First check if the product with the Id is present in the cache or not
        Product product = (Product) redisTemplate.opsForHash().get("PRODUCTS","PRODUCT_" + productId);
        if(product != null)
        {
            // Product was present in the cache - CACHE HIT
                return product;
        }

        // CACHE MISS

        ResponseEntity<FakeStoreProductDto> responseEntity =
                restTemplate.getForEntity(
                        "https://fakestoreapi.com/products/" + productId,
                        FakeStoreProductDto.class
                ); // Whatever response you will get from this url, capture that response into object of FakeStoreProductDto type. .class just represents the type of the object

                FakeStoreProductDto fakeStoreProductDto = responseEntity.getBody();
                if(fakeStoreProductDto == null)
                {
                    throw new ProductNotFoundException(productId);
                }

                product = convertFakeStoreProductDtoToProduct(fakeStoreProductDto);

                // Before returning the product, store it in redis
                redisTemplate.opsForHash().put("PRODUCTS", "PRODUCT_" + productId, product);
                return product;


    }

    private Product convertFakeStoreProductDtoToProduct(FakeStoreProductDto fakeStoreProductDto)
    {

        if(fakeStoreProductDto.getId()==null)
        {
            return null;
        }
        Product product = new Product();
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setImageUrl(fakeStoreProductDto.getImage());
        product.setPrice(fakeStoreProductDto.getPrice());
        Category category = new Category();
        category.setTitle(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        product.setId(fakeStoreProductDto.getId());
        return product;

    }
    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public Product replaceProduct(Long productId, Product product) {
        return null;
    }

    @Override
    public Page<Product> getProductsByTitle(String title, int pageNumber, int pageSize) {
        return null;
    }
}
