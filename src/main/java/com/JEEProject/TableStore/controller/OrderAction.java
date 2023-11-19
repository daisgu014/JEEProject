package com.JEEProject.TableStore.controller;

import com.JEEProject.TableStore.Auth.user.UserAuthService;
import com.JEEProject.TableStore.Model.Account;
import com.JEEProject.TableStore.services.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OrderAction {

    @Autowired
    OrderService orderService;
    @Autowired
    private UserAuthService userAuthService;

    @RequestMapping(path = "/admin/orders/confirm/*")
    @PreAuthorize("hasRole('ADMIN')")
    public String confirmOrder(@RequestParam("id") String id){
        orderService.confirmOrder(Integer.valueOf(id),userAuthService.getUser().getId());
        return  "redirect:/admin/orders";
    }
    @RequestMapping(path = "/admin/orders/cancel/*")
    @PreAuthorize("hasRole('ADMIN')")
    public String cancelOrder(@RequestParam("id") String id){
        orderService.cancelOrder(Integer.valueOf(id),userAuthService.getUser().getId());
        return  "redirect:/admin/orders";
    }


    @RequestMapping(path = "/user/orders/cancel/*")
    @PreAuthorize("hasRole('USER')")
    public String cancelOrderByUser(@RequestParam("id") String id, HttpSession session){
        orderService.cancelOrder(Integer.valueOf(id),((Account)session.getAttribute("account")).getId());
        return "redirect:/user/purchased";
    }
}
