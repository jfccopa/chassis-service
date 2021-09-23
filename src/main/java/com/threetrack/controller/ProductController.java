package com.threetrack.controller;

import com.threetrack.entity.Product;
import com.threetrack.service.seller.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/products")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @RequestMapping(method = RequestMethod.GET, value="/products/{id}")
    public Product findProduct(@PathVariable(value = "id") Integer productId){
        return productService.getProductId(productId);
    }

    @RequestMapping(method = RequestMethod.POST, value="/products")
    public String addProduct(@RequestBody Product product){
        System.out.println(product.getName());
        String message = " ";

        //productService.addProduct(product);
        if(productService.addProduct(product)==true){
            message = "insert product";
            return message;
        }else{
            message = "no insertado";
            return message;
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value="/products")
    public String updateProduct(@RequestBody Product product){

        System.out.println("Entre al PUT");
        String message = " ";
        if(productService.upProduct(product)==true){
            message = "update product";
            return message;
        }else{
            message = "update error";
            return message;
        }

    }

    @RequestMapping(method = RequestMethod.DELETE, value="/products/{id}")
    public String deleteProduct(@PathVariable(value = "id") Integer productId){

        System.out.println("Entre al DELETE");
        String message = " ";
        if(productService.deleteProduct(productId)){
            message = "DELETE product";
            return message;
        }else{
            message = "DELETE error";
            return message;
        }

    }
}