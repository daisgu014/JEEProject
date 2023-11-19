package com.JEEProject.TableStore.services;

import com.JEEProject.TableStore.Model.ORDERSTATE;
import com.JEEProject.TableStore.Model.Order;
import com.JEEProject.TableStore.repositories.AccountRepository;
import com.JEEProject.TableStore.repositories.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void confirmOrder(Integer orderId, Integer confirmId){
        Order od = orderRepo.findById(orderId).get();
        od.setConfirm_id(confirmId);
        od.setState(ORDERSTATE.DONE);
        orderRepo.save(od);
        mailSender.sendHTMLEmail(
                od.getUser().getEmail(),
                "Xác nhận đơn hàng",
                String.format("<h1>Đơn hàng của bạn đã được xác nhận thành công!</h1>\n" +
                        "Mã đơn hàng: %s\nTổng tiền: %s\n" +
                        "Cảm ơn bạn đã mua của chúng tôi!",od.getId(),od.getTotal_price())
        );
    }

    public void cancelOrder(Integer orderId, Integer confirmId){
        Order od = orderRepo.findById(orderId).get();
        od.setConfirm_id(confirmId);
        od.setState(ORDERSTATE.CANCEL);
        orderRepo.save(od);
        mailSender.sendHTMLEmail(
                od.getUser().getEmail(),
                "Xác nhận Huỷ đơn hàng",
                String.format("<h1>Đơn hàng đã hủy!</h1>\n" +
                        "Đơn hàng %s của bạn đã hủy\n" +
                        "Hẹn gặp lại bạn trong tương lai!",od.getId(),od.getTotal_price())
        );
    }
}
