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

@Service @Primary
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
    public Iterable<Product> getAll(){
        return productRepository.findAllByDeleteAt();
    }
    public Page<Product> getAllWhereDeleteAtIsNull(Pageable pageable){
    return productRepository.findProductsWhereDeleteAtIsNull(pageable);
    }
    public Page<Product> filter(Pageable pageable, String name, Integer minPrice, Integer maxPrice, Integer CategoryId, Integer providerId,String Status){
        Page<Product> products = getAllWhereDeleteAtIsNull(pageable);
        List<Product> filteredProducts = new ArrayList<>();

        // Filter by name
        if (name != null && !name.isEmpty()) {
            products = productRepository.findByNameContainingIgnoreCase(name, pageable);
        }

        // Filter by price range
        if (minPrice != null && maxPrice != null) {
            products = productRepository.findByPriceBetween(minPrice, maxPrice, pageable);
        }

        // Filter by category ID
        if (CategoryId != null && CategoryId!=-1) {
            products = productRepository.findByCategoryId(CategoryId, pageable);
        }

        // Filter by provider ID
        if (providerId != null && providerId!=-1) {
            products = productRepository.findByProviderId(providerId, pageable);
        }

        // Filter by status
        if (Status != null && !Status.isEmpty() &&!Status.equalsIgnoreCase("Tất cả")) {
            products = productRepository.findByStatus(Status, pageable);
        }

        // Populate the filtered products list
        products.forEach(filteredProducts::add);

        // Create a new Page with the filtered products
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
                || !product.getImgPath().equalsIgnoreCase("Vui lòng chọn ảnh")){
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
