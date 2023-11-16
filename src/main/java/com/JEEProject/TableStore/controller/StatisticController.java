package com.JEEProject.TableStore.controller;

import com.JEEProject.TableStore.Model.Account;
import com.JEEProject.TableStore.Model.Order;
import com.JEEProject.TableStore.services.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(path = {"/admin/statistic"})
@PreAuthorize("hasRole('ADMIN')")
public class StatisticController {

    @Autowired
    private StatisticService st;


    @GetMapping(value = "")
    public ResponseEntity<Map<Account, List<Order>>> getOrder(){
        return ResponseEntity.ok(st.groupOrderByAccount());
    }

    @RequestMapping(path = "/customer")
    @ResponseBody
    public List<List<Object[]>> findTop5Customer(){
        try {

            return List.of(st.top5Customer(11,2023),st.top5Saler(11,2023));
        } catch (ParseException e) {
            return new ArrayList<>();
        }
    }
}
