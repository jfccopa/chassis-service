package com.threetrack.controller;

import com.threetrack.dto.ResponseDto;
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
    public ResponseDto<List<Product>> getAllProducts(){
        ResponseDto<List<Product>> response= new ResponseDto<>();
        response.setData(productServiceImpl.getAllProducts());
        response.setSuccess(true);
        return response;
    }


    @GetMapping("/{id}")
    public ResponseDto<Product> findProduct(@PathVariable(value = "id") Integer productId){
        ResponseDto<Product> response = new ResponseDto<>();
        response.setData(productServiceImpl.getProductId(productId));
        response.setSuccess(true);
        return response;
    }

    @PostMapping
    public ResponseDto<Product> addProduct(@RequestBody Product product){
        ResponseDto<Product> response= new ResponseDto<>();
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
    public ResponseDto<Product> updateProduct(@RequestBody Product product){

        ResponseDto<Product> response= new ResponseDto<>();
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
    public ResponseDto<Product> deleteProduct(@PathVariable(value = "id") Integer productId){
        ResponseDto<Product> response= new ResponseDto<>();
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