package com.JEEProject.TableStore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CatalogController {
    @GetMapping(path = {"/catalog"})
    public String getHome(){
        return "catalog";
    }
}
