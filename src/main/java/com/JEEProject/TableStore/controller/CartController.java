package com.JEEProject.TableStore.controller;

import com.JEEProject.TableStore.Model.Cart;
import com.JEEProject.TableStore.services.CartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping(path = "cart")
public class CartController {
    @Autowired
    CartService cartService;
    public String getCart() {
        return "index";
    }

    @PostMapping(path = "/add")
    public String addToCart(@RequestParam("productID") Integer productID,
                            @RequestParam("quantityInput")Optional<Integer> qty)
    {
        try{
            Integer userID = 1;
            Optional<Cart> existingCartItem = cartService.findByUserIDAndProductID(userID, productID);
            if(existingCartItem.isPresent()) {
                Cart existingCart = existingCartItem.get();
                existingCart.setQty(existingCart.getQty() + qty.orElse(1));
                cartService.addToCart(existingCart);
            } else {
                Cart cart = new Cart(userID, productID, qty.orElse(1));
                cartService.addToCart(cart);
            }
        } catch (Exception e) {

        }
        return "redirect:/index";
    }
}
