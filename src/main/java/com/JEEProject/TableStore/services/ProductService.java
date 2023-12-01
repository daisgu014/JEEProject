package com.JEEProject.TableStore.services;

import com.JEEProject.TableStore.Model.Category;
import com.JEEProject.TableStore.Model.Product;
import com.JEEProject.TableStore.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.stream.Collectors;

@Service @Primary
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    public boolean isProductNameAlreadyInUse(String name) {
        return productRepository.existsByName(name);
    }
    public boolean DeletebyId(Integer id){
        if(id!=null && id >0){
            productRepository.deleteProductUpDelete(id);
            return true;
        }
        return false;
    }
    public Iterable<Product> getAll(){
        return productRepository.findAllByDeleteAt();
    }
    public Page<Product> getAllWhereDeleteAtIsNull(Pageable pageable){
    return productRepository.findProductsWhereDeleteAtIsNull(pageable);
    }
    public Page<Product> filter(Pageable pageable, String name, Integer minPrice, Integer maxPrice, Integer CategoryId, Integer providerId,String Status){
        List<Product> filteredProducts = productRepository.findProductsWhereDeleteAtIsNull();
        if (name != null && !name.equalsIgnoreCase("")) {
            filteredProducts = filteredProducts.stream()
                    .filter(product -> product.getName().toLowerCase().contains(name.toLowerCase()))
                    .collect(Collectors.toList());
        }
        if (minPrice != null && maxPrice != null) {
            filteredProducts = filteredProducts.stream()
                    .filter(product -> product.getPrice() >= minPrice && product.getPrice() <= maxPrice)
                    .collect(Collectors.toList());
        }
        if (CategoryId != null && CategoryId!=-1) {
            filteredProducts = filteredProducts.stream()
                    .filter(product -> product.getCategory().getId()==CategoryId)
                    .collect(Collectors.toList());
        }
        if (providerId != null && providerId!=-1) {
            filteredProducts = filteredProducts.stream()
                    .filter(product -> product.getProvider().getId()==providerId)
                    .collect(Collectors.toList());
        }
        if (Status != null && !Status.isEmpty() &&!Status.equalsIgnoreCase("Tất cả")) {
            filteredProducts = filteredProducts.stream()
                    .filter(product -> product.getStatus().equalsIgnoreCase(Status))
                    .collect(Collectors.toList());
        }
        Page<Product> filteredPage = new PageImpl<>(filteredProducts, pageable, filteredProducts.size());
        return filteredPage;
    }
    public boolean updateProduct(Integer id, Product product){
        Optional<Product> p = productRepository.findById(id);
        if(p.isPresent()){
            if(!p.get().getName().equalsIgnoreCase(product.getName())){
                p.get().setName(product.getName());
            }
            if(!p.get().getColor().equalsIgnoreCase(product.getColor())){
                p.get().setColor(product.getColor());
            }
            if(p.get().getPrice()!=product.getPrice()){
               p.get().setPrice(product.getPrice());
            }
            if(!p.get().getStatus().equalsIgnoreCase(product.getStatus())){
                p.get().setStatus(product.getStatus());
            }
            if(!p.get().getImgPath().equalsIgnoreCase(product.getImgPath())
                && !product.getImgPath().equalsIgnoreCase("Vui lòng chọn ảnh")){
                p.get().setImgPath(product.getImgPath());
            }
            if(p.get().getCategory()!=product.getCategory()){
                p.get().setCategory(product.getCategory());
            }
            if(p.get().getProvider()!=product.getProvider()){
                p.get().setProvider(product.getProvider());
            }
            productRepository.save(p.get());
            return true;
        }
        return false;
    }
    public Optional<Product> findById(Integer id) {
        return productRepository.findById(id);
    }
    public void addProduct(Product product){
        productRepository.save(product);
    }
    public void saveProduct(Product product){
        productRepository.save(product);
    }

}
