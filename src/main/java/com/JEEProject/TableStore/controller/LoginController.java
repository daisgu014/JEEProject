package com.JEEProject.TableStore.controller;

import com.JEEProject.TableStore.Model.Account;
import com.JEEProject.TableStore.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@RequestMapping(path = "user/login")
@Controller
public class LoginController {
    @Autowired
    private AccountRepository accountRepository;
    @RequestMapping(value = "")
    public String getLogin(ModelMap modelMap){
        modelMap.addAttribute("error","");
        return "userLogin";
    }
    @PostMapping(value = "/checkLogin")
    public String checkLogin(ModelMap modelMap, @RequestParam("username") String username, @RequestParam("password") String password) {
        Account account = accountRepository.findByUsername(username);
        if (account != null && account.getPassword().equals(password)) {
            return "redirect:/user/register";
        } else {
            modelMap.addAttribute("error","Tài khoản hoặc mật khẩu không đúng!");
            return "userLogin";
        }
    }
}

