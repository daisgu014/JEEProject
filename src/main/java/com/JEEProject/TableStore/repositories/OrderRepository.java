package com.JEEProject.TableStore.repositories;


import com.JEEProject.TableStore.Model.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order,Integer> {
    List<Order> findByUserId(int userID);
}
