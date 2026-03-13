package com.arjunnigam.productservice.services;

import com.arjunnigam.productservice.exceptions.ProductNotFoundException;
import com.arjunnigam.productservice.models.Category;
import com.arjunnigam.productservice.models.Product;
import com.arjunnigam.productservice.repositories.CategoryRepository;
import com.arjunnigam.productservice.repositories.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// This class interacts with our own database

@Service("selfProductService") // Name of the bean is selfProductService
//@Primary
public class SelfProductService implements ProductService{

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getSingleProduct(Long productId) throws ProductNotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isEmpty())
        {
            throw new ProductNotFoundException(productId);
        }

        return optionalProduct.get();

    }

    @Override
    public Product createProduct(Product product) {
        // Validations
//        if(product.getId()!=null)
//        {
//            Optional<Product> optionalProduct = productRepository.findById(product.getId());
//            if(optionalProduct.isEmpty())
//            {
//                throw new RuntimeException("Product not found");
//            }
//        }

        Category category = product.getCategory();
        Optional<Category> optionalCategory = categoryRepository.findByTitle(category.getTitle());
        if(optionalCategory.isEmpty())
        {
            // Create a category
            Category savedCategory = categoryRepository.save(category); // The parameter category does not have the ID set
            product.setCategory(savedCategory);
        }
        return productRepository.save(product);
    }

    @Override
    public Product replaceProduct(Long productId, Product product) {
        return null;
    }
}
