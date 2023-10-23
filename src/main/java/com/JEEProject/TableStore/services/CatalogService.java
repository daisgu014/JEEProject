package com.JEEProject.TableStore.services;

import com.JEEProject.TableStore.Model.Category;
import com.JEEProject.TableStore.Model.Product;
import com.JEEProject.TableStore.repositories.CatalogRepository;
import com.JEEProject.TableStore.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CatalogService {
    @Autowired
    CatalogRepository catalogRepository;
    @Autowired
    CategoryRepository categoryRepository;

    public Iterable<Product> findAllProducts() {
        return catalogRepository.findAll();
    }
    public Iterable<Category> findAllCategories() {
        return categoryRepository.findAll();
    }
}
