package com.JEEProject.TableStore.controller;

import com.JEEProject.TableStore.Model.Account;
import com.JEEProject.TableStore.repositories.AccountRepository;
import com.JEEProject.TableStore.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(path = "admin/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "",method = RequestMethod.GET)
    public String getAllProviders(ModelMap modelMap){
        Iterable<Account> accounts = accountService.getAll();
        modelMap.addAttribute("accounts",accounts);
        return "adminAccount";
    }
}
