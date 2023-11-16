package com.JEEProject.TableStore.services;

import com.JEEProject.TableStore.Model.Order;
import com.JEEProject.TableStore.repositories.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Collection;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;

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

    public List<Object[]> findTop5Customer(String sd, String ed){
        return orderRepo.findTop5Customer(Date.valueOf(sd), Date.valueOf(ed));
    }

    public List<Object[]> findTop5Saler(String sd, String ed){
        return orderRepo.findTop5Saler(Date.valueOf(sd), Date.valueOf(ed));
    }

}
