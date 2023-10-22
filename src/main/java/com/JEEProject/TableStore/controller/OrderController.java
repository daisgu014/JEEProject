package com.JEEProject.TableStore.controller;

import com.JEEProject.TableStore.Model.Order;
import com.JEEProject.TableStore.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;
import java.util.stream.StreamSupport;

@Controller
@RequestMapping(path = "/admin/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "")
    public ModelAndView getOrders(){
        ModelAndView mv = new ModelAndView("/adminOrders");
        mv.addObject("orders",
                StreamSupport.stream(orderService.findAll().spliterator(), false).toList());
        orderService.findAll().forEach(e-> {
            e.getDetails().forEach(g->{
                System.out.println(g.getOrder_id());
                System.out.println(g.getProduct_id());
                System.out.println(g.getQty());
            });
        });
        return mv;
    }
}
