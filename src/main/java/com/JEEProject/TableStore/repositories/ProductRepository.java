package com.JEEProject.TableStore.repositories;

import com.JEEProject.TableStore.Model.Category;
import com.JEEProject.TableStore.Model.Product;
import com.JEEProject.TableStore.Model.Provider;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Primary
public interface ProductRepository extends CrudRepository<Product,Integer> {
    @Modifying
    @Query("UPDATE Product c SET c.DeleteAt = NOW() WHERE c.id = :product_id")
    @Transactional
    void deleteProductUpDelete(@Param("product_id") Integer id);

    boolean existsByName(String name);

    Object findAll(Pageable pageable);
    @Query("SELECT p FROM Product p WHERE p.DeleteAt IS NULL")
    Page<Product> findProductsWhereDeleteAtIsNull(Pageable pageable);
    @Query("SELECT p FROM Product p WHERE p.DeleteAt IS NULL")
    List<Product> findProductsWhereDeleteAtIsNull();
    @Query("SELECT p FROM Product p WHERE p.DeleteAt IS NULL")
    Iterable<Product> findAllByDeleteAt();

//     Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);
//    Page<Product> findByStatus(String status, Pageable pageable);
//    Page<Product>findByProviderId(Integer providerId,Pageable pageable);
//    Page<Product>findByCategoryId(Integer categoryId,Pageable pageable);
//
//    Page<Product>findByPriceBetween(Integer minvalue,Integer maxValue, Pageable pageable);
    Optional<Product> findByName(String name);

    @Modifying
    @Transactional
    @Query("UPDATE Product p SET p.imgPath= :imgpath, p.inStock=0, p.DeleteAt= null where p.name like %:productName% and p.category = :category and p.provider = :provider and p.color like %:color% and p.price = :price and p.DeleteAt is not null")
    void createProductEx(@Param("imgpath") String imgPath,
                         @Param("productName") String name,
                         @Param("category")Category category,
                         @Param("provider")Provider provider,
                         @Param("color") String color,
                         @Param("price") Integer price);
}
