package com.JEEProject.TableStore.controller;

import com.JEEProject.TableStore.Model.Account;
import com.JEEProject.TableStore.Model.Order;
import com.JEEProject.TableStore.repositories.OrderRepository;
import com.JEEProject.TableStore.services.OrderService;
import com.JEEProject.TableStore.services.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.StreamSupport;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "user")
public class UserProfileController {
    private final PasswordEncoder passwordEncoder;
    @Autowired
    UserService userService;
    @Autowired
    OrderService orderService;

    //  Trang thông tin sản phẩm
    @RequestMapping(value = "/profile")
    public String getAllUserProfile(ModelMap modelMap, HttpSession session) {
        Account user = (Account) session.getAttribute("account");
        if (user != null){
            modelMap.addAttribute("error","");
            modelMap.addAttribute("success", "");
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
        if (user != null){
            if (!userService.checkUpdateEmail(user, email)){
                user = (Account) session.getAttribute("account");
                modelMap.addAttribute("account", user);
                modelMap.addAttribute("success", "");
                modelMap.addAttribute("error","Email đã được tài khoản khác sữ dụng, xin hãy nhập email khác!");
                return "userProfile";
            }
            if (!userService.checkUpdatePhone(user, phone)) {
                user = (Account) session.getAttribute("account");
                modelMap.addAttribute("account", user);
                modelMap.addAttribute("success", "");
                modelMap.addAttribute("error", "Số điện thoại đã được tài khoản khác sữ dụng, xin hãy nhập email khác!");
                return "userProfile";
            }
            user.setFullname(fullname);
            user.setAddress(address);
            userService.updateAccount(user);
            session.setAttribute("account", user);
            modelMap.addAttribute("error", "");
            modelMap.addAttribute("success", "Thay đổi thông tin thành công.");
            return "userProfile";
        } else {
            return "redirect:/user/login";
        }
    }

//  Trang đổi mật khẩu
    @RequestMapping(value = "/password")
    public String getAllUserPassword(ModelMap modelMap, HttpSession session) {
        Account user = (Account) session.getAttribute("account");
        if (user != null){
            modelMap.addAttribute("account", user);
            modelMap.addAttribute("success", "");
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
        if (user != null){
            if (!passwordEncoder.matches(password, user.getPassword())){
                modelMap.addAttribute("success", "");
                modelMap.addAttribute("error","Mật khẩu cũ không đúng!");
                return "userPassword";
            } else {
                user.setPassword(passwordEncoder.encode(newPassword));
                userService.updateAccount(user);
                session.setAttribute("account", user);
                modelMap.addAttribute("error","");
                modelMap.addAttribute("success", "Thay đổi thông tin thành công.");
                return "userPassword";
            }
        } else {
            return "redirect:/user/login";
        }
    }


// Trang thông tin đơn hàng đã mua
    @RequestMapping(value = "/purchased", method = RequestMethod.GET)
    public ModelAndView getAllUserPurchased(ModelMap modelMap, HttpSession session) {
        Account user = (Account) session.getAttribute("account");
        if (user != null){
            ModelAndView mv = new ModelAndView("userPurchased");
            mv.addObject("orders",
                    StreamSupport.stream(userService.getAllUserOrder(user.getId()).spliterator(), false).toList());
            return mv;
        } else {
            ModelAndView mv = new ModelAndView("redirect:/user/login");
            return mv;
        }
    }

    @GetMapping(value = "/purchased/search")
    public ModelAndView getOrders(@RequestParam(required = false) String orderID,
                                  @RequestParam(required = false) String startDay,
                                  @RequestParam(required = false) String endDay, HttpSession session){
        Account user = (Account) session.getAttribute("account");
        if (user != null){
            if (orderID.isEmpty() && startDay.isEmpty() && endDay.isEmpty()) {
                ModelAndView mv = new ModelAndView("redirect:/user/purchased");
                return mv;
            }
                ModelAndView mv = new ModelAndView("userPurchased");
                mv.addObject("orders", StreamSupport.stream(userService.getAllUserOrder(user.getId()).spliterator(), false)
                        .filter(e->{
                            return e.getId().toString().equals(orderID) && e.isBefore(endDay) && e.isAfter(startDay);
                        })
                        .toList()
                );
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