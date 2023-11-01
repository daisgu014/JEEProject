package com.JEEProject.TableStore.controller;

import com.JEEProject.TableStore.Model.Order;
import com.JEEProject.TableStore.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "/admin/orders/confirm/*")
public class ConfirmOrder {

    @Autowired
    OrderService orderService;

    @RequestMapping(value = "")
    public String confirmOrder(@RequestParam("id") String id){
        orderService.setConfirm(Integer.valueOf(id),1);
        return  "redirect:..";
    }
}
