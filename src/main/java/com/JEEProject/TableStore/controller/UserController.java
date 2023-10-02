package com.JEEProject.TableStore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(path = "admin")
public class UserController {
    @RequestMapping( value = "/users" ,method = RequestMethod.GET)
    public String getUser(ModelMap modelMap){
        modelMap.addAttribute("controller","users");
        return"adminCategory";
    }
}
