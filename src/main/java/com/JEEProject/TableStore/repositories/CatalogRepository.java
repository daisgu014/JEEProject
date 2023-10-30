package com.JEEProject.TableStore.repositories;

import com.JEEProject.TableStore.Model.Product;
import com.JEEProject.TableStore.Model.ProductSearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CatalogRepository extends CrudRepository<Product, Integer>, JpaSpecificationExecutor<Product> {

//    @Query("SELECT p FROM Product p WHERE p.DeleteAt IS NULL")
//    Page<Product> findProductsNotDeleted(Pageable pageable);
    @Query("select DISTINCT p.color FROM Product p WHERE p.DeleteAt IS NULL")
    Iterable<String> findAllColors();
    @Query(value = "SELECT p FROM Product p WHERE p.DeleteAt IS NULL ORDER BY RAND() LIMIT 4")
    List<Product> findRandomProducts();


//    @Query("SELECT p FROM Product p WHERE " +
//            "p.DeleteAt IS NULL " +
//            "AND (:name IS NULL OR p.name LIKE %:#{#criteria.name}%) " +
//            "AND (:categoryID IS NULL OR p.category.id IN :#{#criteria.categoryIDs}) " +
//            "AND (:minPrice IS NULL OR p.price >= :#{#criteria.minPrice} AS Float) " +
//            "AND (:maxPrice IS NULL OR p.price <= :#{#criteria.maxPrice} AS Float) " +
//            "AND (:colors IS NULL OR p.color IN :#{#criteria.colors})")
//    Page<Product> findProductsByCriteria(ProductSearchCriteria criteria, Pageable pageable);
}
