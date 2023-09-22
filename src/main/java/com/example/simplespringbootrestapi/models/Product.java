package com.example.simplespringbootrestapi.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private String id;
    private String name;
    private String description;
    private String features;
    private String price;
    private String keywords;
    private String url;
    private String category;
    private String subcategory;
}
