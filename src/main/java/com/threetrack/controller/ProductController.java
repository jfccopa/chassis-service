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
        try {
            response.setData(productServiceImpl.getAllProducts());
            response.setSuccess(true);
            response.setMessage(Constants.PROCESSED_OK);    
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage(Constants.ERROR);
        }
        
        return response;
    }


    @GetMapping("/{id}")
    public ResponseDto<Product> findProduct(@PathVariable(value = "id") Integer productId){
        ResponseDto<Product> response = new ResponseDto<>();
        try {
            Product product = productServiceImpl.getProductId(productId);
            if(product != null){
                response.setData(product);       
                response.setMessage(Constants.PROCESSED_OK);
            }else{
                response.setMessage(Constants.ERROR_NO_DATA);
            }
            response.setSuccess(true);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage(Constants.ERROR);
        }
        return response;
    }

    @PostMapping
    public ResponseDto<Product> addProduct(@RequestBody Product product){
        ResponseDto<Product> response= new ResponseDto<>();
        try {
            if(productServiceImpl.addProduct(product)){
                response.setMessage(Constants.RESPONSE_CREATE);
            }else{
                response.setMessage(Constants.ERROR_CREATE);
            }
            response.setSuccess(true);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage(Constants.ERROR);
        }
        return response;
    }

    @PutMapping
    public ResponseDto<Product> updateProduct(@RequestBody Product product){
        ResponseDto<Product> response= new ResponseDto<>();
        try {
            if(productServiceImpl.upProduct(product)){
                response.setMessage(Constants.RESPONSE_UPDATE);
            }else{
                response.setMessage(Constants.ERROR_NO_DATA);
            }
            response.setSuccess(true);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage(Constants.ERROR);
        }
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseDto<Product> deleteProduct(@PathVariable(value = "id") Integer productId){
        ResponseDto<Product> response= new ResponseDto<>();
        try {
            if(productServiceImpl.deleteProduct(productId)){
                response.setMessage(Constants.RESPONSE_DELETE);
            }else{
                response.setMessage(Constants.ERROR_NO_DATA);
            }
            response.setSuccess(true);
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage(Constants.ERROR);
        }
        return response;
    }
}