package com.JEEProject.TableStore.controller;

import com.JEEProject.TableStore.Model.Account;
import com.JEEProject.TableStore.Model.Category;
import com.JEEProject.TableStore.repositories.AccountRepository;
import com.JEEProject.TableStore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "user/register")
public class RegisterController {

    @Autowired
    UserService userService;
    // return name of "jsp file"
//    @RequestMapping(value = "", method = RequestMethod.GET)
    @RequestMapping(value = "")
    public String getAllRegister(ModelMap modelMap) {
        modelMap.addAttribute("error","");
        return "userRegister";
    }

    @PostMapping(value = "/register_fail")
    public String insertCategory(ModelMap modelMap,@ModelAttribute("account") Account user) {
        if (!userService.checkEmptyUsername(user)){
            modelMap.addAttribute("error","Username đã được sữ dụng, xin hãy nhập username khác!");
            return "userRegister";
        }
        if (!userService.checkEmptyEmail(user)){
            modelMap.addAttribute("error","Email đã được sữ dụng, xin hãy nhập email khác!");
            return "userRegister";
        }
        if (!userService.checkEmptyPhone(user)){
            modelMap.addAttribute("error","Số điện thoại đã được sữ dụng, xin hãy nhập số điện thoại khác!");
            return "userRegister";
        }
        userService.addNewAccount(user);
        return "redirect:/user/login";
    }
}
