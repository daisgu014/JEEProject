package com.JEEProject.TableStore.repositories;

import com.JEEProject.TableStore.Model.Product;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    Iterable<Product> findAllByDeleteAt();

     Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);
    Page<Product> findByStatus(String status, Pageable pageable);
    Page<Product>findByProviderId(Integer providerId,Pageable pageable);
    Page<Product>findByCategoryId(Integer categoryId,Pageable pageable);

    Page<Product>findByPriceBetween(Integer minvalue,Integer maxValue, Pageable pageable);


}
