package com.arjunnigam.productservice;

import com.arjunnigam.productservice.models.Product;
import com.arjunnigam.productservice.projections.ProductWithTitleAndPrice;
import com.arjunnigam.productservice.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class ProductServiceApplicationTests {
    @Autowired
    private ProductRepository productRepository;
    @Test
    void contextLoads() {
    }

    @Test
    public void testQuery(){
        List<ProductWithTitleAndPrice> productWithTitleAndPrice = productRepository.findTitleAndPriceById();
        for (ProductWithTitleAndPrice p : productWithTitleAndPrice) {
            System.out.println(p.getTitle() + " " + p.getPrice());

        }

        Optional<Product> optionalProduct = productRepository.findByCategory_Title("mobile");
        System.out.println(optionalProduct.get().getPrice());
    }

}
