package com.JEEProject.TableStore.services;

import com.JEEProject.TableStore.Model.Order;
import com.JEEProject.TableStore.repositories.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;

    public Iterable<Order> findAll(){
        return orderRepo.findAll();
    }
}
