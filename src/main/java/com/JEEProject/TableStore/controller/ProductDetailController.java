package com.JEEProject.TableStore.controller;

import com.JEEProject.TableStore.Model.Product;
import com.JEEProject.TableStore.repositories.CatalogRepository;
import com.JEEProject.TableStore.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "productDetail")
public class ProductDetailController {
    @Autowired
    private CatalogRepository catalogRepository;
    @GetMapping(path = "/{productID}")
    public String getDetail(ModelMap modelMap, @PathVariable Integer productID) {
        if(catalogRepository.findById(productID).isPresent()) {
            modelMap.addAttribute("product",catalogRepository.findById(productID).get());
        }
        return "productDetail";
    }
}
