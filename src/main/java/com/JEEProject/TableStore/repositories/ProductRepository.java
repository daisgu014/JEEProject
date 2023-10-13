package com.JEEProject.TableStore.repositories;

import com.JEEProject.TableStore.Model.Product;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ProductRepository extends CrudRepository<Product,Integer> {
    @Modifying
    @Query("UPDATE Product c SET c.DeleteAt = NOW() WHERE c.id = :product_id")
    @Transactional
    void deleteProductUpDelete(@Param("product_id") Integer id);

    boolean existsByName(String name);

}
