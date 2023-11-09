package com.JEEProject.TableStore.controller;

import com.JEEProject.TableStore.Model.Product;
import com.JEEProject.TableStore.Model.ProductSearchCriteria;
import com.JEEProject.TableStore.services.CatalogService;
import jakarta.validation.Valid;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
public class CatalogController {
    @Autowired
    private CatalogService catalogService;
    private ProductSearchCriteria criteria = new ProductSearchCriteria();
    @GetMapping(path = {"/catalog"})
    public String getCatalog(ModelMap modelMap,
                             @RequestParam("page")Optional<Integer> page
    ){
        Pageable pageable = PageRequest.of(page.orElse(0), 6);
        Page<Product> products = catalogService.searchProducts(criteria, pageable);
        modelMap.addAttribute("productPages", products);
        modelMap.addAttribute("categories", catalogService.findAllCategories());
        modelMap.addAttribute("colors", catalogService.findAllColors());
        modelMap.addAttribute("criteria", criteria);
        modelMap.addAttribute("ERROR", "");
        return "catalog";
    }

    @PostMapping("/catalog")
    public String searchProducts(ModelMap modelMap,
                                 @Valid @ModelAttribute("criteria") ProductSearchCriteria criteria,
                                 @RequestParam("page")Optional<Integer> page,
                                 @RequestParam("btnSubmit") String btnSubmit
    ){
        if ("cancel".equals(btnSubmit)) {
            criteria.setCategoryID(new ArrayList<>());
            criteria.setColors(new ArrayList<>());
            criteria.setName("");
            criteria.setMaxPrice("");
            criteria.setMinPrice("");
        }
        Pageable pageable = PageRequest.of(page.orElse(0), 6);
        Page<Product> products = catalogService.searchProducts(criteria, pageable);
        modelMap.addAttribute("productPages", products);
        modelMap.addAttribute("categories", catalogService.findAllCategories());
        modelMap.addAttribute("colors", catalogService.findAllColors());
        this.criteria = criteria;
        return "catalog";
    }

    @GetMapping(path = "/catalog/category/{categoryID}")
    public String getProductByCategoryID(ModelMap modelMap,
                                         @PathVariable Integer categoryID,
                                         @RequestParam("page")Optional<Integer> page
    ) {
        ArrayList<String> s = new ArrayList<>();
        s.add(categoryID.toString());
        criteria.setCategoryID(s);
        criteria.setColors(new ArrayList<>());
        criteria.setName("");
        criteria.setMaxPrice("");
        criteria.setMinPrice("");
        return "redirect:/catalog";
    }

    @GetMapping(path = "/catalog/all")
    public String getCatalog() {
        criteria.setCategoryID(new ArrayList<>());
        criteria.setColors(new ArrayList<>());
        criteria.setName("");
        criteria.setMaxPrice("");
        criteria.setMinPrice("");
        return "redirect:/catalog";
    }

}
