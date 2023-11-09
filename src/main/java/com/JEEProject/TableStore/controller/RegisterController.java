package com.JEEProject.TableStore.controller;

import com.JEEProject.TableStore.Model.Account;
import com.JEEProject.TableStore.Model.Category;
import com.JEEProject.TableStore.repositories.AccountRepository;
import com.JEEProject.TableStore.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(path = "user")
public class RegisterController {

    @Autowired
    UserService userService;
    // return name of "jsp file"
//    @RequestMapping(value = "", method = RequestMethod.GET)
    @RequestMapping(value = "/register")
    public String getAllRegister(ModelMap modelMap, HttpSession session) {
        Account user = (Account) session.getAttribute("account");
        if (user == null) {
            modelMap.addAttribute("error", "");
            return "userRegister";
        } else {
            return "redirect:/user/profile";
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String insertCategory(ModelMap modelMap,@ModelAttribute("account") Account user) {
        user.setCreateAt(new Date());


        userService.addNewAccount(user);
        return "redirect:/user/login";
    }
}
