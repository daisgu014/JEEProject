package com.JEEProject.TableStore.repositories;

import com.JEEProject.TableStore.Model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ProductRepositoryExt extends ProductRepository{
    Collection<Product> findByCategoryId(Integer categoryId);
}
