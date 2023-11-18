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

    @Query( "select ods.product_id, count(*) " +
            "from Order o " +
            "join o.details ods on o.id = ods.order_id " +
            "where o.create_at BETWEEN :sd AND :ed " +
            "GROUP BY ods.product_id " +
            "ORDER BY count(*) LIMIT 10")
    public List<Object[]> findTop10Product(@Param("sd") Date sd, @Param("ed") Date ed);

    @Query( "select o.create_at , count(*) , sum(o.total_price)" +
            "from Order o " +
            "where o.create_at BETWEEN :sd AND :ed " +
            "GROUP BY o.create_at " )
    public List<Object[]> dailyRevenue(@Param("sd") Date sd, @Param("ed") Date ed);
}
