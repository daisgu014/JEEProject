package com.JEEProject.TableStore.services;

import com.JEEProject.TableStore.Model.Category;
import com.JEEProject.TableStore.Model.Product;
import com.JEEProject.TableStore.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    public boolean isProductNameAlreadyInUse(String name) {
        // Kiểm tra xem tên sản phẩm đã tồn tại trong cơ sở dữ liệu hay chưa
        return productRepository.existsByName(name);
    }
    public boolean DeletebyId(Integer id){
        if(id!=null && id >0){
            productRepository.deleteProductUpDelete(id);
            return true;
        }
        return false;
    }
    public boolean DeleteIds(Integer[] ids){
      if (ids.length>0){
          for (int i=0;i<= ids.length;i++){
              productRepository.deleteProductUpDelete(ids[i]);
          }
       }
       return false;
    }
    public Page<Product> getAllWhereDeleteAtIsNull(Pageable pageable){
    return productRepository.findProductsWhereDeleteAtIsNull(pageable);
    }
    public void addProduct(Product product){
        productRepository.save(product);
    }

}
