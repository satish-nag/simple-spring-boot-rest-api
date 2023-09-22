package com.example.simplespringbootrestapi.controller;

import com.example.simplespringbootrestapi.models.Product;
import com.example.simplespringbootrestapi.models.Response;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ObjectMapper objectMapper;

    List<Product> productList;

    @PostConstruct
    public void init() throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("products.json");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(classPathResource.getURL().openStream()));
        productList=objectMapper.readValue(bufferedReader, new TypeReference<List<Product>>() {});
    }

    @GetMapping("{productId}")
    public Response<Product> getProduct(@PathVariable String productId){
        Optional<Product> product1 = productList.stream().filter(product -> product.getId().equalsIgnoreCase(productId)).findAny();
        Response<Product> response = Response.<Product>builder().build();
        if(!product1.isPresent()) {
             response.setErrorMessage("Product Id not found in list");
        }else {
            response.setData(product1.get());
        }
        return response;
    }

    @GetMapping
    public Response<List<Product>> getProducts(){
        return Response.<List<Product>>builder().data(productList).build();
    }
}
