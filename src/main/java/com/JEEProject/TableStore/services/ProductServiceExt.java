package com.JEEProject.TableStore.services;

import com.JEEProject.TableStore.Model.Product;
import com.JEEProject.TableStore.repositories.ProductRepositoryExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ProductServiceExt extends ProductService{
    @Autowired
    ProductRepositoryExt productRepository;

    public Collection<Product> getByCategoryId(Integer cateId){
        return productRepository.findByCategoryId(cateId);
    }
}
