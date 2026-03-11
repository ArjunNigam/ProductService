package com.arjunnigam.productservice.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity (name = "Categories")
public class Category extends BaseModel{
    private String title;

}
