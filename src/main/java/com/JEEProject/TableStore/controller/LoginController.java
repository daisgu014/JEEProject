package com.JEEProject.TableStore.controller;

import com.JEEProject.TableStore.Model.Account;
import com.JEEProject.TableStore.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path = "user")
@Controller
//@SessionAttribute("User")
public class LoginController {
    @Autowired
    UserService userService;
    @RequestMapping(value = "/login")
    public String getLogin(ModelMap modelMap, HttpSession session){
        Account user = (Account) session.getAttribute("account");
        if (user == null){
            modelMap.addAttribute("error","");
            return "userLogin";
        } else {
            return "redirect:/user/profile";
        }
    }

    @RequestMapping(value = "/login/getPassword")
    public String getSendEmailPassword(ModelMap modelMap){
        modelMap.addAttribute("error","");
        return "userGetPassword";
    }
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String checkLogin(ModelMap modelMap, @RequestParam("username") String username, @RequestParam("password") String password, HttpServletRequest request) {
        if (userService.checkAccount(username,password)) {
            Account user = userService.getAccountByUsername(username);
            HttpSession session = request.getSession();
            session.setAttribute("account", user);
            return "redirect:/user/profile";
        } else {
            modelMap.addAttribute("error","Tài khoản hoặc mật khẩu không đúng!");
            return "userLogin";
        }
    }
}

