package com.JEEProject.TableStore.controller;

import com.JEEProject.TableStore.Auth.user.UserAuthService;
import com.JEEProject.TableStore.Model.Order;
import com.JEEProject.TableStore.services.MailSenderService;
import com.JEEProject.TableStore.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.StreamSupport;

@Controller

@RequestMapping(path = "/admin/orders")
@PreAuthorize("hasAnyRole('ADMIN','SALE')")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private MailSenderService mailSender;
    @Autowired
    private UserAuthService userAuthService;

    @RequestMapping(value = "",method = RequestMethod.GET)
    public ModelAndView getOrders(){
        ModelAndView mv = new ModelAndView("adminOrders");
        mv.addObject("user",userAuthService.getUser());
        mv.addObject("orders",
                StreamSupport.stream(orderService.findAll().spliterator(), false).toList());
        return mv;
    }

    @GetMapping(value = "/search")
    public ModelAndView getOrders(@RequestParam String svalue, @RequestParam String sday, @RequestParam String eday){
        ModelAndView mv = new ModelAndView("adminOrders");
        mv.addObject("orders",
                StreamSupport.stream(orderService.findAll().spliterator(), false)
                        .filter(e->{
                            return e.orderBy(svalue) && e.isBefore(eday) && e.isAfter(sday);
                        })
                        .toList()
        );
        return mv;
    }
}
