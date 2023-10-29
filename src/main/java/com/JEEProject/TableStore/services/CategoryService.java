package com.JEEProject.TableStore.services;

import com.JEEProject.TableStore.Model.Category;
import com.JEEProject.TableStore.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public Iterable<Category> getAll(){
        return categoryRepository.findAll();
    }
    public void create(Category category){
        categoryRepository.save(category);
    }
    public void deleteAll(ArrayList<Integer> ids){
        if(ids!=null){
            ids.forEach(id->{
                categoryRepository.deleteCategoryUpCreateAt(id);
            });
        }
    }
    public void updateCategory(){}
    public Page<Category> getAllCategoriesWhereDeleteAtIsNull(Pageable pageable){
        return categoryRepository.findCategoriesWhereDeleteAtIsNull(pageable);
    }
}
