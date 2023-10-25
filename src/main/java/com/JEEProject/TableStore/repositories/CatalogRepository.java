package com.JEEProject.TableStore.repositories;

import com.JEEProject.TableStore.Model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CatalogRepository extends CrudRepository<Product, Integer>, JpaSpecificationExecutor<Product> {

    @Query("SELECT p FROM Product p WHERE p.DeleteAt IS NULL")
    Page<Product> findProductsNotDeleted(Pageable pageable);
    @Query("select DISTINCT p.color from Product p WHERE p.DeleteAt IS NULL")
    Iterable<String> findAllColors();
}
