package com.JEEProject.TableStore.controller;

import com.JEEProject.TableStore.Model.Category;
import com.JEEProject.TableStore.repositories.CategoryRepository;
import com.JEEProject.TableStore.services.CategoryService;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path = "admin/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CategoryRepository categoryRepository;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getAllCategory(ModelMap modelMap,
                                 @RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "6") int size) {
        Pageable pageable = PageRequest.of(page,size, Sort.by("id"));
        Page<Category> categoryPage = categoryService.getAllCategoriesWhereDeleteAtIsNull(pageable);
        modelMap.addAttribute("categoryPage",categoryPage);
        return "adminCategory";
    }
    public boolean checkName(Category category){

        return false;
    }

    @PostMapping(value = "/create")
    public String insertCategory(ModelMap modelMap,@ModelAttribute("category") Category category) {
        modelMap.addAttribute("category", new Category());
        modelMap.addAttribute("Cotroller","Categories");
        categoryRepository.save(category);
        return "redirect:/admin/categories";

    }

    @RequestMapping(value = "/delete/{id}")
    public String deleteCategory(@PathVariable("id") Integer id){
        categoryRepository.deleteCategoryUpCreateAt(id);
        return "redirect:/admin/categories";
    }
    @RequestMapping(value = "/edit/{id}")
public String UpdateCategory(ModelMap modelMap,@PathVariable Integer id, @RequestParam("editedCategoryName") String updatedName){
        Category category = categoryRepository.findById(id).orElse(null);
        if(category==null){
            System.err.println("khong co");
        }else{
            modelMap.addAttribute("category",category);
            category.setName(updatedName);
            categoryRepository.save(category);
        }
        return "redirect:/admin/categories";

    }
}

