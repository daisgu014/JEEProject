package com.JEEProject.TableStore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(path = "accounts")
public class ProviderController {
    @RequestMapping(value = "",method = RequestMethod.GET)
    public String getAllProviders(ModelMap modelMap){
        System.out.println("Hello World");
        return "adminProvider";
    }
}
