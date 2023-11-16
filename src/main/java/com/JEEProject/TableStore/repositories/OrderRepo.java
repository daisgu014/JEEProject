package com.JEEProject.TableStore.repositories;


import com.JEEProject.TableStore.Model.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface OrderRepo extends CrudRepository<Order,Integer> {

    @Query( "select user_id, COUNT(*) orderCount, SUM(total_price) total_price " +
            "from Order o " +
            "where o.create_at BETWEEN :sd AND :ed " +
            "GROUP BY user_id ")
    public List<Object[]> findTop5Customer(@Param("sd") Date sd, @Param("ed") Date ed);

    @Query( "select confirm_id, COUNT(*) orderCount, SUM(total_price) total_price " +
            "from Order o " +
            "where o.create_at BETWEEN :sd AND :ed " +
            "GROUP BY confirm_id ")
    public List<Object[]> findTop5Saler(@Param("sd") Date sd, @Param("ed") Date ed);

}
