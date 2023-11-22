package com.JEEProject.TableStore.controller;

import com.JEEProject.TableStore.Model.Account;
import com.JEEProject.TableStore.Model.Cart;
import com.JEEProject.TableStore.Model.Product;
import com.JEEProject.TableStore.services.CartService;
import com.JEEProject.TableStore.services.CatalogService;
import com.JEEProject.TableStore.services.ProductService;
import com.JEEProject.TableStore.services.ProviderService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "cart")
public class CartController {
    @Autowired
    CartService cartService;

    @Autowired
    CatalogService catalogService;
    @RequestMapping()
    public String getCart(
                          ModelMap modelMap,
                          HttpServletRequest request, HttpSession session) {

        try {
            Integer userID = ((Account)session.getAttribute("account")).getId();
            int total = 0;
            int qty = 0;
            List<Cart> carts = cartService.findCartByUserID(userID);
            List<Product> products = new ArrayList<>();
            for (Cart c: carts) {
                products.add(catalogService.findProductByID(c.getProductID()).get());
                total += c.getQty() * catalogService.findProductByID(c.getProductID()).get().getPrice();
                qty += c.getQty();
            }

            modelMap.addAttribute("carts", carts);
            modelMap.addAttribute("products", products);
            modelMap.addAttribute("length", !carts.isEmpty() ? (carts.size() - 1) : 0);
            modelMap.addAttribute("total", total);

            session.setAttribute("cart-qty", qty);
            return "cart";
        } catch (Exception ex){
            return "messageNotLogin";
        }

    }

    @PostMapping(path = "/add")
    public String addToCart(@RequestParam("productID") Integer productID,
                            @RequestParam("quantityInput")Optional<Integer> qty,
                            HttpSession session)
    {
        try{
            Account user = (Account) session.getAttribute("account");
            if (user != null){
                Integer userID = user.getId();
                Optional<Cart> existingCartItem = cartService.findByUserIDAndProductID(userID, productID);
                if(existingCartItem.isPresent()) {
                    Cart existingCart = existingCartItem.get();
                    existingCart.setQty(existingCart.getQty() + qty.orElse(1));
                    cartService.addToCart(existingCart);
                } else {
                    Cart cart = new Cart(userID, productID, qty.orElse(1));
                    cartService.addToCart(cart);
                }
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
           return "messageNotLogin";
        }
        return "redirect:/catalog";
    }
}
