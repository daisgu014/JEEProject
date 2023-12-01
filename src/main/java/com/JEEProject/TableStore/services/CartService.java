package com.JEEProject.TableStore.services;

import com.JEEProject.TableStore.Model.*;
import com.JEEProject.TableStore.repositories.CartRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    CartRepository cartRepository;

    public void addToCart(Cart cart) {
        cartRepository.save(cart);
    }

    public Optional<Cart> findByUserIDAndProductID(Integer userID, Integer productID) {
        return cartRepository.findById(new CartKey(userID, productID));
    }

    public List<Cart> findCartByUserID(Integer userID) {
        return cartRepository.findCartByUser(userID);
    }
    public boolean deleteCart(List<CartRequest> list, Account account){
        if(list.size()>0){
            list.forEach(item->{
                cartRepository.deleteCart(item.getProductID(),account.getId(), item.getQty());
            });
            return true;
        }else {
            return false;
        }
    }
    public boolean deleteCart(CartRequest item, Account account){
        if(item!=null){
                cartRepository.deleteCart(item.getProductID(),account.getId(), item.getQty());
            return true;
        }else {
            return false;
        }
    }

}
