package com.JEEProject.TableStore.services;

import com.JEEProject.TableStore.Model.Cart;
import com.JEEProject.TableStore.Model.CartKey;
import com.JEEProject.TableStore.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
