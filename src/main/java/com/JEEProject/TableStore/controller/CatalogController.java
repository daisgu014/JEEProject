package com.JEEProject.TableStore.controller;

import com.JEEProject.TableStore.services.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class CatalogController {
    @Autowired
    private CatalogService catalogService;
    @GetMapping(path = {"/catalog"})
    public String getCatalog(ModelMap modelMap){
        modelMap.addAttribute("products", catalogService.findAllProducts());
        modelMap.addAttribute("categories", catalogService.findAllCategories());
        return "catalog";
    }
}
