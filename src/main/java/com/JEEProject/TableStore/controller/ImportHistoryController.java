package com.JEEProject.TableStore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(path = "admin/products/import-history")
public class ImportHistoryController {
    @RequestMapping(value = "", method = RequestMethod.GET)
     public String getImportHistory(ModelMap modelMap){
         return "importHistory";
     }
}
