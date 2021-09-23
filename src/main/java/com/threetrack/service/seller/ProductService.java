package com.threetrack.service.seller;

import com.threetrack.entity.Product;
import com.threetrack.repository.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductDao productDao;
    private List<Product> products = new ArrayList<>(Arrays.asList(
    ));

    public List<Product> getAllProducts(){
        return productDao.list();
    }

    public Product getProductId(Integer productId){
        return productDao.findById(productId);
    }

    public boolean addProduct(Product product){

        try{
            product.setState('A');
            product.setCreateDate(Timestamp.from(Instant.now()));
            product.setDeleted(false);

            productDao.add(product);

            return true;

        }catch (Exception ex){
            return false;
        }
    }

    public boolean upProduct(Product product){

        try{
            product.setUpdateDate(Timestamp.from(Instant.now()));
            productDao.update(product);

            return true;

        }catch (Exception ex){
            return false;
        }
    }

    public boolean deleteProduct(Integer productId){

        try{
            productDao.delete(productId);
            return true;

        }catch (Exception ex){
            return false;
        }
    }

}
