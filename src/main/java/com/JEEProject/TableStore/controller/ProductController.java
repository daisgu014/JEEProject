package com.JEEProject.TableStore.controller;

import com.JEEProject.TableStore.Model.Category;
import com.JEEProject.TableStore.Model.Product;
import com.JEEProject.TableStore.repositories.ProductRepository;
import com.JEEProject.TableStore.validation.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path = "admin")
public class ProductController {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    private ProductValidator productValidator;
    @InitBinder
    protected void initBinder(WebDataBinder binder){
        binder.setValidator(productValidator);
    }
    @RequestMapping( value = "/products" ,method = RequestMethod.GET)
    public String getProduct(ModelMap modelMap){
        modelMap.addAttribute("controller","products");
        Iterable<Product> products = productRepository.findAll();
        modelMap.addAttribute("products",products);
        return"adminProduct";
    }

    @PostMapping(value = "/create")
    public String insertCategory(ModelMap modelMap,@ModelAttribute("category") Category category) {
        modelMap.addAttribute("category", new Category());
        modelMap.addAttribute("Cotroller","Categories");
        return "redirect:/admin/products";

    }
}
