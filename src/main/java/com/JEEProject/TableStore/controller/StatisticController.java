package com.JEEProject.TableStore.controller;

import com.JEEProject.TableStore.Auth.user.UserAuthService;
import com.JEEProject.TableStore.Model.Account;
import com.JEEProject.TableStore.Model.Order;
import com.JEEProject.TableStore.services.OrderService;
import com.JEEProject.TableStore.services.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.StreamSupport;


@Controller
@RequestMapping(path = {"/admin/statistic"})
@PreAuthorize("hasRole('ADMIN')")
public class StatisticController {

    @Autowired
    private StatisticService st;
    @Autowired
    private UserAuthService userAuthService;

    @GetMapping
    public ModelAndView getStatistic(){
        ModelAndView mv = new ModelAndView("statistic");
        mv.addObject("user",userAuthService.getUser());
        return mv;
    }


    @RequestMapping(path = "/customer")
    @ResponseBody
    public List<Object> findTop5Customer(){
        Integer month = 11;
        Integer year = 2023;
        try {
            return List.of(
                    st.top5Customer(month,year),
                    st.top5Saler(month,year),
                    st.top10Product(month,year)
            );
        } catch (ParseException e) {
            return new ArrayList<>();
        }
    }
}
