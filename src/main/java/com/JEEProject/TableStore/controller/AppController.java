package com.JEEProject.TableStore.controller;


import com.JEEProject.TableStore.services.CategoryService;
import com.JEEProject.TableStore.services.ProductService;
import com.JEEProject.TableStore.services.ProductServiceExt;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AppController {

    @Autowired
    ProductServiceExt ps;
    @Autowired
    CategoryService cs;
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
    @GetMapping(path = "/about")
    public String getAbout(){
        return "Intro";
    }

    @GetMapping(path = "/product/{cateId}")
    public ModelAndView getProduct(@PathVariable Integer cateId){
        ModelAndView mv = new ModelAndView("template/product");
        mv.addObject("products", ps.getByCategoryId(cateId));
        return mv;
    }


    @GetMapping(path = "/category")
    public ModelAndView getCategory(){
        ModelAndView mv = new ModelAndView("template/category");
        mv.addObject("categories", cs.getAll());
        return mv;
    }

    @GetMapping(path = "/slide")
    public String getSlide(){
        return "static/slide";
    }
}
