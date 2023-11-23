package com.JEEProject.TableStore.controller;


import com.JEEProject.TableStore.Model.Account;
import com.JEEProject.TableStore.Model.CartRequest;
import com.JEEProject.TableStore.Model.ResponseObject;
import com.JEEProject.TableStore.services.CartService;
import com.JEEProject.TableStore.services.CategoryService;
import com.JEEProject.TableStore.services.ProductService;
import com.JEEProject.TableStore.services.ProductServiceExt;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AppController {

    @Autowired
    ProductServiceExt ps;
    @Autowired
    CategoryService cs;
    @Autowired
    CartService cartService;
    private final HttpServletRequest HttpRequest;

    @GetMapping(path = {"","/","/home"})
    public String getHome(){
        return "index";
    }
    @GetMapping(path = {"/header"})
    public ModelAndView getHeader(HttpSession session){
        ModelAndView mv = new ModelAndView("template/header");
        try {
            mv.addObject(
                    "cartSize",
                    cartService.findCartByUserID(
                                    ((Account) session.getAttribute("account")).getId())
                            .size());
        }catch (Exception ex) {
            mv.addObject(
                    "cartSize",0);
        }
        return mv;
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
    @PostMapping(path = "/deleteCart")
    public ResponseEntity<ResponseObject> deleteCart(@RequestBody List<CartRequest> list){
        HttpSession httpSession = HttpRequest.getSession();
        try{
            if(list.size()<0){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject(
                                "Thất bại","Chưa chọn sản phẩm cần xóa",""
                        )
                );
            }else {
               Account account= ((Account)httpSession.getAttribute("account"));
               cartService.deleteCart(list,account);
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(
                                "Thành công","Xóa khỏi giỏ hàng thành công",""
                        )
                );
            }
        }catch (Exception e ){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject(
                            "Thất bại","Lỗi:"+e.toString(),""
                    )
            );
        }
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
