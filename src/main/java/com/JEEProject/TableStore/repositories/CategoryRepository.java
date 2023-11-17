package com.JEEProject.TableStore.repositories;

import com.JEEProject.TableStore.Model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {
    @Modifying
    @Query("UPDATE Category c SET c.deleteAt = NOW() WHERE c.id = :category_id")
    @Transactional
    void deleteCategoryUpDeleteAt(@Param("category_id") Integer id);

    @Query("SELECT c FROM Category c where c.deleteAt IS null ")
    Page<Category> findCategoriesWhereDeleteAtIsNull(Pageable pageable);
    Page<Category> findByNameContainingIgnoreCase(String name, Pageable pageable);
}