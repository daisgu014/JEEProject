package com.JEEProject.TableStore.controller;

import com.JEEProject.TableStore.Model.Category;
import com.JEEProject.TableStore.repositories.CategoryRepository;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path = "admin/categories")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getAllCategory(ModelMap modelMap) {
        Iterable<Category> categories = categoryRepository.findAll();
        modelMap.addAttribute("categories", categories);
        return "adminCategory";
    }

    @PostMapping(value = "/create")
    public String insertCategory(ModelMap modelMap,@ModelAttribute("category") Category category) {
        modelMap.addAttribute("category", new Category());
        categoryRepository.save(category);
        return "redirect:/admin/categories";

    }

    @RequestMapping(value = "/delete/{id}")
    public String deleteCategory(@PathVariable("id") Integer id){
        categoryRepository.deleteCategoryUpCreateAt(id);
        return "redirect:/admin/categories";
    }
    @RequestMapping(value = "/edit/{id}")
public String UpdateCategory(@PathVariable Integer id, @RequestParam("editedCategoryName") String updatedName){
        Category category = categoryRepository.findById(id).orElse(null);
        if(category==null){
            System.err.println("khong co");
        }else{
            category.setName(updatedName);
            categoryRepository.save(category);
        }
        return "redirect:/admin/categories";

    }
}

