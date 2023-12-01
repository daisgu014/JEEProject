package com.JEEProject.TableStore.repositories;

import com.JEEProject.TableStore.Model.Account;
import com.JEEProject.TableStore.Model.Cart;
import com.JEEProject.TableStore.Model.CartKey;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

@Repository
public interface CartRepository extends CrudRepository<Cart, CartKey> {
    @Modifying
    @Query("SELECT  c FROM Cart c WHERE  c.userID= :userId")
    public List<Cart> findCartByUser(@Param("userId") Integer userID);

    @Modifying
    @Query("delete   FROM Cart c WHERE c.productID = :product_id AND c.userID= :userId AND c.qty= :qty")
    @Transactional
    void deleteCart(@Param("product_id") Integer id,
                    @Param("userId") Integer userId,
                    @Param("qty") Integer qty);
}
