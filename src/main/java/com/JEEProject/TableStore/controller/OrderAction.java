package com.JEEProject.TableStore.controller;

import com.JEEProject.TableStore.Auth.user.UserAuthService;
import com.JEEProject.TableStore.Model.*;
import com.JEEProject.TableStore.services.OrderService;
import com.JEEProject.TableStore.services.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderAction {

    @Autowired
    OrderService orderService;

    @Autowired
    ProductService ps;
    @Autowired
    private UserAuthService userAuthService;

    private final HttpServletRequest HttpRequest;

    @RequestMapping(path = "/admin/orders/confirm/*")
    @PreAuthorize("hasRole('ADMIN')")
    public String confirmOrder(@RequestParam("id") String id){
        orderService.confirmOrder(Integer.valueOf(id),userAuthService.getUser().getId());
        return  "redirect:/admin/orders";
    }
    @RequestMapping(path = "/admin/orders/cancel/*")
    @PreAuthorize("hasRole('ADMIN')")
    public String cancelOrder(@RequestParam("id") String id){
        orderService.cancelOrder(Integer.valueOf(id),userAuthService.getUser().getId());
        return  "redirect:/admin/orders";
    }


    @RequestMapping(path = "/user/orders/cancel/*")
    @PreAuthorize("hasRole('USER')")
    public String cancelOrderByUser(@RequestParam("id") String id, HttpSession session){
        orderService.cancelOrder(Integer.valueOf(id),((Account)session.getAttribute("account")).getId());
        return "redirect:/user/purchased";
    }

    @PostMapping(value = "/payment")
    @ResponseBody
    public ResponseEntity<ResponseObject> createNewOrder(@RequestBody List<CartRequest> reqs){
        try {
            Order order = orderService.createOrder();
            order.setUser_id(((Account) HttpRequest.getSession().getAttribute("account")).getId());
            System.out.println(order);

            reqs.forEach(e ->
                    {
                        OrderDetail tmp = new OrderDetail();
                        tmp.setOrder_id(order.getId());
                        tmp.setProduct_id(e.getProductID());
                        tmp.setQty(e.getQty());
                        orderService.addDetail(
                                order,
                                tmp
                                );
                    }
            );
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("Thành công","Đơn hàng đặt thành công",""));
        }catch (Exception ex){
            System.err.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("Thất bại", ex.getMessage(),""));
        }
    }
}
