package com.JEEProject.TableStore.controller;

import com.JEEProject.TableStore.Model.Category;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@Controller
@RequestMapping(path = "admin")
public class ProductController {
    @RequestMapping( value = "/products" ,method = RequestMethod.GET)
    public String getProduct(ModelMap modelMap){
        modelMap.addAttribute("controller","products");
        modelMap.addAttribute("name","Nguyễn Hữu Đại");
        return"adminProduct";
    }
}
