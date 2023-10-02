package com.JEEProject.TableStore.repositories;

import com.JEEProject.TableStore.Model.Category;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {
    @Modifying
    @Query("UPDATE Category c SET c.DeleteAt = NOW() WHERE c.Id = :category_id")
    @Transactional
    void deleteCategoryUpCreateAt(@Param("category_id") Integer id);

}