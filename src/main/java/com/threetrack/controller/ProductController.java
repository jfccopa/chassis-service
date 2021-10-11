package com.threetrack.controller;

import com.threetrack.dto.Response;
import com.threetrack.entity.Product;
import com.threetrack.service.ProductServiceImpl;
import com.threetrack.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductServiceImpl productServiceImpl;

    @GetMapping
    public Response<List<Product>> getAllProducts(){
        Response<List<Product>> response= new Response<>();
        response.setData(productServiceImpl.getAllProducts());
        response.setSuccess(true);
        return response;
    }


    @GetMapping("/{id}")
    public Response<Product> findProduct(@PathVariable(value = "id") Integer productId){
        Response<Product> response = new Response<>();
        response.setData(productServiceImpl.getProductId(productId));
        response.setSuccess(true);
        return response;
    }

    @PostMapping
    public Response<Product> addProduct(@RequestBody Product product){
        Response<Product> response= new Response<>();
        if(productServiceImpl.addProduct(product)==true){
            response.setMessage(Constants.RESPONSE_CREATE);
            response.setSuccess(true);
            return response;
        }else{
            response.setSuccess(true);
            return response;
        }
    }

    @PutMapping
    public Response<Product>  updateProduct(@RequestBody Product product){

        Response<Product> response= new Response<>();
        if(productServiceImpl.upProduct(product)==true){
            response.setMessage(Constants.RESPONSE_UPDATE);
            response.setSuccess(true);
            return response;
        }else{
            response.setMessage(Constants.ERROR);
            return response;
        }

    }

    @DeleteMapping("/{id}")
    public Response<Product>  deleteProduct(@PathVariable(value = "id") Integer productId){
        Response<Product> response= new Response<>();
        if(productServiceImpl.deleteProduct(productId)){
            response.setMessage(Constants.RESPONSE_DELETE);
            response.setSuccess(true);
            return response;
        }else{
            response.setMessage(Constants.ERROR);
            return response;
        }

    }
}