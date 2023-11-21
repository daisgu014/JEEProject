package com.JEEProject.TableStore.repositories;

import com.JEEProject.TableStore.Model.Account;
import com.JEEProject.TableStore.Model.Cart;
import com.JEEProject.TableStore.Model.CartKey;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface CartRepository extends CrudRepository<Cart, CartKey> {
    public List<Cart> findCartByUserID(Integer userID);
}
