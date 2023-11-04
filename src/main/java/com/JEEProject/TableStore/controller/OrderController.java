package com.JEEProject.TableStore.controller;

import com.JEEProject.TableStore.Model.Order;
import com.JEEProject.TableStore.services.MailSenderService;
import com.JEEProject.TableStore.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;
import java.util.stream.StreamSupport;

@Controller
@RequestMapping(path = "/admin/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private MailSenderService mailSender;

    @RequestMapping(value = "")
    public ModelAndView getOrders(){
        ModelAndView mv = new ModelAndView("/adminOrders");
        mv.addObject("orders",
                StreamSupport.stream(orderService.findAll().spliterator(), false).toList());
        return mv;
    }


}
