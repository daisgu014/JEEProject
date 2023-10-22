package com.JEEProject.TableStore.controller;

import com.JEEProject.TableStore.repositories.CatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import javax.management.modelmbean.ModelMBean;

@Controller
public class CatalogController {
    @Autowired
    private CatalogRepository catalogRepository;
    @GetMapping(path = {"/catalog"})
    public String getCatalog(ModelMap modelMap){
        modelMap.addAttribute("products", catalogRepository.findAll());
        return "catalog";
    }
}
