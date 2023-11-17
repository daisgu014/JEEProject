package com.JEEProject.TableStore.controller;

import com.JEEProject.TableStore.Auth.user.UserAuthService;
import com.JEEProject.TableStore.services.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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


    @PostMapping(path = "/customer")
    @ResponseBody
    public List<Object> getStatisticData(@RequestBody List<Integer> monthData){
        System.err.println(monthData);
        Integer month = monthData.get(1);
        Integer year = monthData.get(0);

        try {
            return List.of(
                    st.top5Customer(month,year),
                    st.top5Saler(month,year),
                    st.top10Product(month,year),
                    st.dailyRevenue(month,year)
            );
        } catch (ParseException e) {
            return new ArrayList<>();
        }
    }
}
