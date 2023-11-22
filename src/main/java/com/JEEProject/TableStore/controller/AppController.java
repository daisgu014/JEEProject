package com.JEEProject.TableStore.controller;


import jakarta.servlet.http.HttpSession;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AppController {
    @GetMapping(path = {"","/","/home"})
    public String getHome(){
        return "index";
    }
    @GetMapping(path = {"/header"})
    public String getHeader(){
        return "template/header";
    }
    @GetMapping(path = {"/footer"})
    public String getFooter(){
        return "template/footer";
    }
    @GetMapping(path = {"/login"})
    public String getLogin(){
        return "loginAdmin";
    }
}
