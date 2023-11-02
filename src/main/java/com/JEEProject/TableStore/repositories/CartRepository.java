package com.JEEProject.TableStore.repositories;

import com.JEEProject.TableStore.Model.Cart;
import com.JEEProject.TableStore.Model.CartKey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends CrudRepository<Cart, CartKey> {

}
