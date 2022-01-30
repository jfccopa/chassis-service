package com.threetrack.service;

import com.threetrack.entity.Product;
import com.threetrack.repository.dao.ProductDao;
import com.threetrack.utils.DateUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    public List<Product> getAllProducts(){
        return productDao.list();
    }

    public Product getProductId(Integer productId){
        return productDao.findById(productId);
    }

    public boolean addProduct(Product product){

        try{
            product.setState('A');
            product.setCreateDate(DateUtils.getCurrentDate());
            product.setDeleted(false);

            productDao.add(product, 0);

            return true;

        }catch (Exception ex){
            return false;
        }
    }

    public boolean upProduct(Product product){

        try{
            productDao.update(product, 0);

            return true;

        }catch (Exception ex){
            return false;
        }
    }

    public boolean deleteProduct(Integer productId){

        try{
            return productDao.delete(productId, 0);      
        }catch (Exception ex){
            return false;
        }
    }

}
