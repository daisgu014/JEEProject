package com.JEEProject.TableStore.services;

import com.JEEProject.TableStore.Model.Order;
import com.JEEProject.TableStore.repositories.AccountRepository;
import com.JEEProject.TableStore.repositories.OrderRepo;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    protected AccountRepository accountRepository;

    @Autowired
    private MailSenderService mailSender;

    public Iterable<Order> findAll(){
        return orderRepo.findAll();
    }

    public Order findById(Integer id){
        return orderRepo.findById(id).get();
    }

    public void setConfirm(Integer orderId, Integer confirmId){
        Order od = orderRepo.findById(orderId).get();
        od.setConfirm_id(confirmId);
        orderRepo.save(od);
        mailSender.sendHTMLEmail(
                od.getUser().getEmail(),
                "Xác nhận đơn hàng",
                String.format("<h1>Đơn hàng của bạn đã được xác nhận thành công!</h1>\n" +
                        "Mã đơn hàng: %s\nTổng tiền: %s",od.getId(),od.getTotal_price())
        );
    }

    public CustomerData findTop5Customer(String sd, String ed){
        CustomerData list = new CustomerData();
         orderRepo.findTop5Customer(Date.valueOf(sd), Date.valueOf(ed)).forEach(
                 list::addData
         );
         return list;
    }

    public List<Object[]> findTop5Saler(String sd, String ed){
        return orderRepo.findTop5Saler(Date.valueOf(sd), Date.valueOf(ed));
    }


    @AllArgsConstructor
    @Getter @Setter
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

}
