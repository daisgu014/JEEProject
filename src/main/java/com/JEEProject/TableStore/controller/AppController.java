package com.JEEProject.TableStore.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {
    @GetMapping(path = {"/*","/home"})
    public String getHome(){
        return "index";
    }
}
