package com.JEEProject.TableStore.services;

import com.JEEProject.TableStore.Model.Product;
import com.JEEProject.TableStore.repositories.AccountRepository;
import com.JEEProject.TableStore.repositories.OrderRepo;
import com.JEEProject.TableStore.repositories.ProductRepository;
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

    @Autowired
    private ProductRepository productRepo;


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

    public ProductData top10Product(Integer month, Integer year){
        return findTop10Product(
                String.format("%s-%s-%s",year,month,1),
                String.format("%s-%s-%s",year,month+1,1)
        );
    }


    public DailyRevenueData dailyRevenue(Integer month, Integer year){
        return findDailyRevenue(
                String.format("%s-%s-%s",year,month,1),
                String.format("%s-%s-%s",year,month+1,1)
        );
    }

    private DailyRevenueData findDailyRevenue(String sd, String ed) {
        DailyRevenueData list = new DailyRevenueData();
        orderRepo.dailyRevenue(java.sql.Date.valueOf(sd), java.sql.Date.valueOf(ed))
                .forEach(list::addData);
        return list;

    }

    private ProductData findTop10Product(String sd, String ed) {
        ProductData list = new ProductData();
        orderRepo.findTop10Product(java.sql.Date.valueOf(sd), java.sql.Date.valueOf(ed))
                .forEach(list::addData);
        return list;

    }

    private CustomerData findTop5Customer(String sd, String ed){
        CustomerData list = new CustomerData();
        orderRepo.findTop5Customer(java.sql.Date.valueOf(sd), java.sql.Date.valueOf(ed))
                .forEach(list::addData);
        return list;
    }

    private SalerData findTop5Saler(String sd, String ed){
        SalerData list = new SalerData();
        orderRepo.findTop5Saler(java.sql.Date.valueOf(sd), Date.valueOf(ed))
                .forEach(list::addData);
        return list;
    }

    @AllArgsConstructor
    @Getter
    @Setter
    class DailyRevenueData{
        List<Date> dates;
        List<Integer> orderCount;
        List<Long> dailyRevenue;
        Long total;

        public DailyRevenueData() {
            this.dates = new ArrayList<>();
            this.orderCount = new ArrayList<>();
            this.dailyRevenue = new ArrayList<>();
            this.total = 0L;
        }

        public void addData(Object[] rawData){
            this.dates.add((Date)rawData[0]);
            this.orderCount.add(Integer.valueOf(rawData[1].toString()));
            this.dailyRevenue.add((Long)rawData[2]);
            this.total += (Long)rawData[2];
        }
    }

    @AllArgsConstructor
    @Getter
    @Setter
    class ProductData{
        List<Integer> id;
        List<String> productName;
        List<Long> productCount;
        List<Long> total;

        public ProductData() {
            this.id = new ArrayList<>();
            this.productName = new ArrayList<>();
            this.productCount = new ArrayList<>();
            this.total = new ArrayList<>();
        }

        public void addData(Object[] rawData){
            Product p = productRepo.findById((Integer) rawData[0]).get();
            this.id.add((Integer) rawData[0]);
            this.productName.add(p.getName());
            this.productCount.add((Long) rawData[1]);
            this.total.add(p.getPrice()*(Long) rawData[1]);
        }
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
