package com.JEEProject.TableStore.controller;

import com.JEEProject.TableStore.Model.Account;
import com.JEEProject.TableStore.Model.Order;
import com.JEEProject.TableStore.services.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class StatisticController {

    @Autowired
    private StatisticService st;


    @GetMapping("/statistic")
    public ResponseEntity<Map<Account, List<Order>>> getOrder(){
        return ResponseEntity.ok(st.groupOrderByAccount());
    }
}
