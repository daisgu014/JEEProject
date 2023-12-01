package com.JEEProject.TableStore.services;

import com.JEEProject.TableStore.Model.Category;
import com.JEEProject.TableStore.Model.Product;
import com.JEEProject.TableStore.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
                categoryRepository.deleteCategoryUpDeleteAt(id);
            });
        }
    }
    public void updateCategory(){}
    public Page<Category> getAllCategoriesWhereDeleteAtIsNull(Pageable pageable){
        return categoryRepository.findCategoriesWhereDeleteAtIsNull(pageable);
    }
    public Category findByIds(Integer id){
        if(categoryRepository.findById(id).isPresent()){
            return categoryRepository.findById(id).get();
        }
        return null;
    }
    public void addCategory(Category category){
        if(categoryRepository.findByName(category.getName()).isPresent()
        && categoryRepository.findByName(category.getName()).get().getDeleteAt() !=null){
            categoryRepository.createExName(category.getName());
        }else {
            Date currentDate = new Date();
            category.setCreateAt(currentDate);
            categoryRepository.save(category);
        }

    }
    public Optional<Category> findById(Integer id){
        return categoryRepository.findById(id);
    }
    public void updateCategory(Integer id, Category category){
        Optional<Category> categoryOptional = findById(id);
        if(categoryOptional.isPresent()){
            if(category.getName()!=null && !category.getName().equalsIgnoreCase("")){
                categoryOptional.get().setName(category.getName());
                categoryRepository.save(categoryOptional.get());
            }
        }
    }
    public boolean DeletebyId(Integer id){
        if(id!=null && id>0){
            categoryRepository.deleteCategoryUpDeleteAt(id);
            return  true;
        }
        return  false;
    }
    public Page<Category> filter(Pageable pageable, String name){
        Page<Category> products = getAllCategoriesWhereDeleteAtIsNull(pageable);
        List<Category> filteredProducts = new ArrayList<>();
        if(name!=null && !name.equalsIgnoreCase("")){
            return categoryRepository.findByNameContainingIgnoreCase(name,pageable);
        }
        return  products;
    }
}
