package com.arjunnigam.productservice.controllers;

import com.arjunnigam.productservice.exceptions.ProductNotFoundException;
import com.arjunnigam.productservice.models.Product;
import com.arjunnigam.productservice.services.ProductService;
import jakarta.persistence.Entity;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {
    @Autowired
    private ProductController productController;

//    @MockBean
//    private ProductService productService;
//    @Test
//    public void testGetSingleProductPositive() throws ProductNotFoundException {
//        Long productId = 10L;
//
//        Product expectedProduct = new Product();
//        when(productService.getSingleProduct(productId)).thenReturn(expectedProduct); // Our Service is returning this Product -> This is mocked
//        Product actualProduct = productController.getSingleProduct(productId);  // Our Controller is returning this Product -> This is actual
//
//        // expected and actual products should be equal for the testCase to pass
//        assertEquals(expectedProduct, actualProduct, "Products are not equal"); // The third parameter (message) gets executed if the assert statement fails
//    }
//
//    @Test
//    public void testGetSingleProductInvalidId() throws ProductNotFoundException {
//        Long productId = -1L;
//        when(productService.getSingleProduct(productId)).thenThrow(new ProductNotFoundException());
//
//        assertThrows(ProductNotFoundException.class, () -> productController.getSingleProduct(productId));
//    }
//
//    @Test
//    public void testGetAllProducts()
//    {
//        Product product1 = new Product();
//        // .... set some attributes to Product1
//
//        Product product2 = new Product();
//        // .... set some attributes to Product1
//
//        Product product3 = new Product();
//        // .... set some attributes to Product1
//
//        Product product4 = new Product();
//        // .... set some attributes to Product1
//
//        List<Product> expectedProducts = List.of(product1,product2,product3);
//
//        when(productService.getAllProducts()).thenReturn(expectedProducts);
//
//
//        List<Product> actualProducts = productController.getAllProducts();
//
//        assertArrayEquals(expectedProducts.toArray(), actualProducts.toArray());
//    }

}