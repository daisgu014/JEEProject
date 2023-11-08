package com.JEEProject.TableStore.controller;

import com.JEEProject.TableStore.Model.Account;
import com.JEEProject.TableStore.Model.Order;
import com.JEEProject.TableStore.repositories.OrderRepository;
import com.JEEProject.TableStore.services.OrderService;
import com.JEEProject.TableStore.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.StreamSupport;

@Controller
@RequestMapping(path = "user")
public class UserProfileController {
    @Autowired
    UserService userService;
    //  Trang thông tin sản phẩm
    @RequestMapping(value = "/profile")
    public String getAllUserProfile(ModelMap modelMap, HttpSession session) {
        Account user = (Account) session.getAttribute("account");
        if (user != null){
            modelMap.addAttribute("error","");
            modelMap.addAttribute("account", user);
            return "userProfile";
        } else {
            return "redirect:/user/login";
        }
    }

    @PostMapping(value = "/profile")
    public String checkUpdateProfile(ModelMap modelMap, HttpSession session,
                                     @RequestParam("fullname") String fullname,
                                     @RequestParam("phone") String phone,
                                     @RequestParam("email") String email,
                                     @RequestParam("address") String address){
        Account user = (Account) session.getAttribute("account");
        if (!userService.checkUpdateEmail(user, email)){
            modelMap.addAttribute("error","Email đã được sữ dụng, xin hãy nhập email khác!");
            return "userProfile";
        }

        if (!userService.checkUpdatePhone(user, phone)){
            modelMap.addAttribute("error","Email đã được sữ dụng, xin hãy nhập email khác!");
            return "userProfile";
        }

        userService.updateInformation(user, fullname, address);
        session.setAttribute("account", user);
        return "redirect:/user/profile";
    }

//  Trang đổi mật khẩu
    @RequestMapping(value = "/password")
    public String getAllUserPassword(ModelMap modelMap, HttpSession session) {
        Account user = (Account) session.getAttribute("account");
        if (user != null){
            modelMap.addAttribute("account", user);
            modelMap.addAttribute("error","");
            return "userPassword";
        } else {
            return "redirect:/user/login";
        }
    }

    @PostMapping(value = "/password")
    public String checkUpdatePassword(ModelMap modelMap, HttpSession session,
                                      @RequestParam("password") String password,
                                      @RequestParam("newPassword") String newPassword){
        Account user = (Account) session.getAttribute("account");
        if (!userService.updatePassword(user, password, newPassword)){
            modelMap.addAttribute("error","Mật khẩu cũ không đúng!");
            return "userPassword";
        } else {
            return "redirect:/user/password";
        }
    }


// Trang thông tin đơn hàng đã mua
    @RequestMapping(value = "/purchased")
    public ModelAndView getAllUserPurchased(ModelMap modelMap, HttpSession session) {
        Account user = (Account) session.getAttribute("account");
        if (user != null){
            ModelAndView mv = new ModelAndView("/userPurchased");
            mv.addObject("orders",
                    StreamSupport.stream(userService.getAllUserOrder(user.getId()).spliterator(), false).toList());
            return mv;
        } else {
            ModelAndView mv = new ModelAndView("redirect:/user/login");
            return mv;
        }
    }


    //    Đăng xuất
    @RequestMapping(value = "/logout")
    public String logOut(ModelMap modelmap, HttpSession session){
        Account user = (Account) session.getAttribute("account");
        if (user != null){
            session.invalidate();
        }
        return "redirect:/user/login";
    }
}