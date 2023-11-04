package com.JEEProject.TableStore.controller;

import com.JEEProject.TableStore.Model.Account;
import com.JEEProject.TableStore.repositories.AccountRepository;
import com.JEEProject.TableStore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping(path = "user/login")
@Controller
//@SessionAttribute("User")
public class LoginController {
    @Autowired
    UserService userService;
    @RequestMapping(value = "")
    public String getLogin(ModelMap modelMap){
        modelMap.addAttribute("error","");
        return "userLogin";
    }
    @PostMapping(value = "/checkLogin")
    public String checkLogin(ModelMap modelMap, @RequestParam("username") String username, @RequestParam("password") String password) {
        if (userService.checkAccount(username,password)) {
            return "redirect:/user/profile";
        } else {
            modelMap.addAttribute("error","Tài khoản hoặc mật khẩu không đúng!");
            return "userLogin";
        }
    }
}

