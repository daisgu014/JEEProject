package com.JEEProject.TableStore.services;

import com.JEEProject.TableStore.Model.Account;
import com.JEEProject.TableStore.Model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.StreamSupport;

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

    public OrderService.CustomerData top5Customer(Integer month, Integer year) throws ParseException {
        return orderService.findTop5Customer(
                String.format("%s-%s-%s",year,month,1),
                String.format("%s-%s-%s",year,month+1,1));
    }
    public List<Object[]> top5Saler(Integer month, Integer year){
        return orderService.findTop5Saler(
                String.format("%s-%s-%s",year,month,1),
                String.format("%s-%s-%s",year,month+1,1));
    }

    public void top10Product(){

    }

    public void totalRevenue(){

    }

    public void revenuePerDayInWeek(){

    }
}
