package com.JEEProject.TableStore.services;

import com.JEEProject.TableStore.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    public boolean isProductNameAlreadyInUse(String name) {
        // Kiểm tra xem tên sản phẩm đã tồn tại trong cơ sở dữ liệu hay chưa
        return productRepository.existsByName(name);
    }
}
