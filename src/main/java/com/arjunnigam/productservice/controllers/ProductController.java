package com.arjunnigam.productservice.controllers;
import java.util.*;

import com.arjunnigam.productservice.models.Product;
import com.arjunnigam.productservice.services.ProductService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// localhost:8080/products
@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    // loclhost:8080/products/1
    @GetMapping("/{productId}")
    public Product getSingleProduct(@PathVariable("productId") Long productId)
    {
        return productService.getSingleProduct(productId);
    }


    // locathost:8080/products
    @GetMapping()
    public List<Product> getAllProducts()
    {
        return productService.getAllProducts();
    }

    // localhost:8080/products
    @PostMapping()
    public Product createProduct(@RequestBody Product product)
    {
        return null;
    }

    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") Long productId, @RequestBody Product product)
    {
        return null;
    }




}
