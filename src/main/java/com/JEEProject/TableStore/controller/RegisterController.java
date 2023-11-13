package com.JEEProject.TableStore.controller;

import com.JEEProject.TableStore.Model.Account;
import com.JEEProject.TableStore.Model.Category;
import com.JEEProject.TableStore.repositories.AccountRepository;
import com.JEEProject.TableStore.services.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(path = "user")
public class RegisterController {
    private final PasswordEncoder passwordEncoder;
    @Autowired
    UserService userService;
    // return name of "jsp file"
//    @RequestMapping(value = "", method = RequestMethod.GET)
    @RequestMapping(value = "/register")
    public String getAllRegister(ModelMap modelMap, HttpSession session) {
        Account user = (Account) session.getAttribute("account");
        if (user == null) {
            modelMap.addAttribute("success", "");
            modelMap.addAttribute("error", "");
            return "userRegister";
        } else {
            return "redirect:/user/profile";
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String insertCategory(ModelMap modelMap,@ModelAttribute("account") Account user) {
        user.setCreateAt(new Date());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (userService.findUsername(user.getUsername())){
            modelMap.addAttribute("error","Username đã được sữ dụng, xin hãy nhập username khác!");
            modelMap.addAttribute("success", "");
            return "userRegister";
        }

        if (userService.findEmail(user.getEmail())){
            modelMap.addAttribute("error","Email đã được sữ dụng, xin hãy nhập email khác!");
            modelMap.addAttribute("success", "");
            return "userRegister";
        }

        if (userService.findPhone(user.getPhone())){
            modelMap.addAttribute("error", "Số điện thoại đã được sữ dụng, xin hãy nhập số điện thoại khác!");
            modelMap.addAttribute("success", "");
            return "userRegister";
        }
        modelMap.addAttribute("error","");
        modelMap.addAttribute("success", "Đăng ký thành công");
        userService.addNewAccount(user);
        return "userRegister";
    }
}
