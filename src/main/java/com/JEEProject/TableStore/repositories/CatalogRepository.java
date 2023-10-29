package com.JEEProject.TableStore.repositories;

import com.JEEProject.TableStore.Model.Product;
import org.springframework.data.repository.CrudRepository;

public interface CatalogRepository extends CrudRepository<Product, Integer> {

}
