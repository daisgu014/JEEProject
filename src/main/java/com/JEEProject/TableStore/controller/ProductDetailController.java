package com.JEEProject.TableStore.controller;

import com.JEEProject.TableStore.Model.Product;
import com.JEEProject.TableStore.repositories.CatalogRepository;
import com.JEEProject.TableStore.repositories.CategoryRepository;
import com.JEEProject.TableStore.services.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(path = "productDetail")
public class    ProductDetailController {
    @Autowired
    private CatalogService catalogService;
    @GetMapping(path = "/{productID}")
    public String getDetail(ModelMap modelMap, @PathVariable Integer productID) {
        if(catalogService.findProductByID(productID).isPresent()) {
            Product p = catalogService.findProductByID(productID).get();
            modelMap.addAttribute("product", p);
            modelMap.addAttribute("category", catalogService.findCategoryByID(p.getCategory().getId()).get());
        }
        List<Product> randomProducts = catalogService.getRandomProducts();
        modelMap.addAttribute("products", randomProducts);
        return "productDetail";
    }
}
