package com.JEEProject.TableStore.controller;

import com.JEEProject.TableStore.Model.Category;
import com.JEEProject.TableStore.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@Controller
@RequestMapping(path = "categories")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;
    @RequestMapping(name = "", method = RequestMethod.GET)
    public String getAllCategory(ModelMap modelMap){
        modelMap.addAttribute("name","Nguyễn Hữu Đại");
        Iterable<Category> categories = categoryRepository.findAll();
        Optional<Category> category = categoryRepository.findById(1);
        String name = category.get().getName();
        modelMap.addAttribute("category",name);
        modelMap.addAttribute("categories",categories);

        return"index";
    }

}
