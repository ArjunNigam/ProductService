package com.arjunnigam.productservice.repositories;

import com.arjunnigam.productservice.models.Category;
import com.arjunnigam.productservice.models.Product;
import com.arjunnigam.productservice.projections.ProductWithTitleAndPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long > {

    //Declared Queries


    @Override
    Optional<Product> findById(Long aLong);

    // select * from products
    @Override
    List<Product> findAll();
    
    
    // select * from products where title = ?
    Optional<Product> findByTitle(String title);
    
    // select * from products where title LIKE '%str%'
    Optional<Product> findByTitleContains(String title);

    //select * from products where title LIKE '%str%'
    Optional<Product> findByTitleContainsIgnoreCase(String title);

    //select * from products where price >= start and price <= end
    Optional<Product> findByPriceBetween(Double start, Double end);

    // select * from products where title LIKE %title% and price >= start and price <= end
    //List<Product> findbyTitleContainsIgnoreCaseAndPriceBetween(String title, Double start, Double end);

    List<Product> findByCreatedAtBetween(Date start, Date end);

    @Override
    void deleteById(Long aLong);

    //For create and update we use the SAVE method
    Product save(Product product);


    // Query : Find the title and price of the product with id = 10
    // select title, price from products where id = 10;
    @Query(value = "select p.title , p.price from products p where p.id = 3", nativeQuery = true)   // Product is the model name and not table name
    List<ProductWithTitleAndPrice> findTitleAndPriceById();

    Optional<Product> findByCategory(Category category);

    Optional<Product> findByCategory_Title(String title);
}
