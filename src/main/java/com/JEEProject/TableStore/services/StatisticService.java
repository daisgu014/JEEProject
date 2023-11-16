package com.JEEProject.TableStore.services;

import com.JEEProject.TableStore.repositories.AccountRepository;
import com.JEEProject.TableStore.repositories.OrderRepo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.ParseException;
import java.util.*;

@Service
public class StatisticService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private AccountRepository accountRepository;


    public CustomerData top5Customer(Integer month, Integer year) throws ParseException {
        return findTop5Customer(
                String.format("%s-%s-%s",year,month,1),
                String.format("%s-%s-%s",year,month+1,1));
    }
    public SalerData top5Saler(Integer month, Integer year){
        return findTop5Saler(
                String.format("%s-%s-%s",year,month,1),
                String.format("%s-%s-%s",year,month+1,1));
    }

    public void top10Product(){

    }

    public void totalRevenue(){

    }

    public void revenuePerDayInWeek(){

    }


    public CustomerData findTop5Customer(String sd, String ed){
        CustomerData list = new CustomerData();
        orderRepo.findTop5Customer(java.sql.Date.valueOf(sd), java.sql.Date.valueOf(ed))
                .forEach(list::addData);
        return list;
    }

    public SalerData findTop5Saler(String sd, String ed){
        SalerData list = new SalerData();
        orderRepo.findTop5Saler(java.sql.Date.valueOf(sd), Date.valueOf(ed))
                .forEach(list::addData);
        return list;
    }


    @AllArgsConstructor
    @Getter
    @Setter
    class CustomerData{
        List<Integer> id;
        List<String> fullName;
        List<Long> orderCount;
        List<Long> total;

        public CustomerData() {
            this.id = new ArrayList<>();
            this.fullName = new ArrayList<>();
            this.orderCount = new ArrayList<>();
            this.total = new ArrayList<>();
        }

        public void addData(Object[] rawData){
            this.id.add((Integer) rawData[0]);
            this.fullName.add(accountRepository.findById((Integer) rawData[0]).get().getFullname());
            this.orderCount.add((Long) rawData[1]);
            this.total.add((Long) rawData[2]);
        }
    }

    @AllArgsConstructor
    @Getter
    @Setter
    class SalerData{
        List<Integer> id;
        List<String> fullName;
        List<Long> orderCount;
        List<Long> total;

        public SalerData() {
            this.id = new ArrayList<>();
            this.fullName = new ArrayList<>();
            this.orderCount = new ArrayList<>();
            this.total = new ArrayList<>();
        }

        public void addData(Object[] rawData){
            this.id.add((Integer) rawData[0]);
            this.orderCount.add((Long) rawData[1]);
            this.total.add((Long) rawData[2]);
            try{
                this.fullName.add(accountRepository.findById((Integer) rawData[0]).get().getFullname());
            }catch (Exception ex){
                this.fullName.add("Not Confirm");
            }
        }
    }

}
