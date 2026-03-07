package com.arjunnigam.productservice.services;

import com.arjunnigam.productservice.models.Category;
import com.arjunnigam.productservice.models.Product;
import dtos.FakeStoreProductDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class FakeStoreProductService implements ProductService{
    private RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate)
    {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Product> getAllProducts() {

        // TODO: HW
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
    public Product getSingleProduct(Long productId) {
        // make an HTTP call to fakestore api to get the product with the given ID.
        // https://fakestoreapi/cim/products/1
        //  RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<FakeStoreProductDto> responseEntity =
                restTemplate.getForEntity(
                        "https://fakestoreapi.com/products/" + productId,
                        FakeStoreProductDto.class
                ); // Whatever response you will get from this url, capture that response into object of FakeStoreProductDto type. .class just represents the type of the object

                return convertFakeStoreProductDtoToProduct(responseEntity.getBody());


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
}
