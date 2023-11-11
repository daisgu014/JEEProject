package com.JEEProject.TableStore.services;

import com.JEEProject.TableStore.Model.Account;
import com.JEEProject.TableStore.Model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StatisticService {

    @Autowired
    private OrderService orderService;

    public Map<Account, List<Order>> groupOrderByAccount(){
        Map<Account, List<Order>> map = new HashMap<>();
        orderService.findAll().forEach(e->{
            if(map.containsKey(e.getUser())){
                map.get(e.getUser()).add(e);
            }else{
                ArrayList<Order> tmp = new ArrayList<>();
                tmp.add(e);
                map.put(e.getUser(), tmp);
            }
        });
        System.out.println(map.keySet().size());
        return map;
    }
}
