package com.JEEProject.TableStore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(path = "categories")
public class CategoryController {
    @RequestMapping(name = "", method = RequestMethod.GET)
    public String getAllCategory(ModelMap modelMap){
        modelMap.addAttribute("name","Nguyễn Hữu Đại");
        return"index";
    }

}
