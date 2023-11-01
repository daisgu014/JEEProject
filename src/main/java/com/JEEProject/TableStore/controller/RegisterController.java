package com.JEEProject.TableStore.controller;

import com.JEEProject.TableStore.Model.Account;
import com.JEEProject.TableStore.Model.Category;
import com.JEEProject.TableStore.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "user/register")
public class RegisterController {
    @Value("${default.role:customer}")
    private String role;
    @Autowired //Inject "AccountRepository"
    private AccountRepository accountRepository;
    // return name of "jsp file"
//    @RequestMapping(value = "", method = RequestMethod.GET)
    @RequestMapping(value = "")
    public String getAllRegister(ModelMap modelMap) {
        Iterable<Account> account = accountRepository.findAll();
        modelMap.addAttribute("account", account);
        modelMap.addAttribute("error","");
        return "userRegister";
    }

    @PostMapping(value = "/create")
    public String insertCategory(ModelMap modelMap,@ModelAttribute("account") Account user) {
        modelMap.addAttribute("account", new Account());
        Account username = accountRepository.findByUsername(user.getUsername());
        if (username != null){
            modelMap.addAttribute("account", user);
            modelMap.addAttribute("error","Username đã được sữ dụng, xin hãy nhập username khác!");
            return "userRegister";
        }
        user.setRole(role);
        accountRepository.save(user);
        return "redirect:/user/register";
    }
}
