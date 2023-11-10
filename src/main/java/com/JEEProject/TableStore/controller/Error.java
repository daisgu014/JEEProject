package com.JEEProject.TableStore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(path = "/errorPage")
public class Error {
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getError(){
        return "ErrorPage";
    }
}
