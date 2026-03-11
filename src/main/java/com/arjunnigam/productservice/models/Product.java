package com.arjunnigam.productservice.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "Products")
public class Product extends BaseModel{
    @Column(nullable = false,  unique = true)
    private String title;
    private String description;
    private Double price;
    private String imageUrl;
    @ManyToOne
    private Category category;
}

/*

   1           1
Product -- Category =>  M : 1
   M           1

   One product will have one category and one Category will have many products. Hence the cardinaltiy will be M : 1
 */