package com.JEEProject.TableStore.repositories;

import com.JEEProject.TableStore.Model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

}
