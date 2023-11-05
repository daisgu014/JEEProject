package com.JEEProject.TableStore.controller;

import com.JEEProject.TableStore.Model.Account;
import com.JEEProject.TableStore.Model.Provider;
import com.JEEProject.TableStore.repositories.AccountRepository;
import com.JEEProject.TableStore.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

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
    @RequestMapping(value = "/add-account",method = RequestMethod.POST)
    @ResponseBody
    public String addAccount(@RequestBody Account data) {
        try {
            data.setCreateAt(new Date());
            if (accountService.getAccountByName(data) == null){
                if (accountService.getAccountByPhone(data)==null){
                    accountService.create(data);
                    return "success";
                }else {
                    return "Số điện thoại đã tồn tại";
                }
            }else {
                return "Username đã tồn tại";
            }
        }catch (Exception e){
            return "Thất bại";
        }
    }
    @RequestMapping(value = "/update-account",method = RequestMethod.POST)
    @ResponseBody
    public String updateAccount(@RequestBody Account data) {
        try{
            accountService.update(data);
            return "success";
        }catch (Exception e){
            return e.toString();
        }
    }
    @RequestMapping(value = "/delete-account", method = RequestMethod.POST)
    @ResponseBody
    public  String deleteAccount(@RequestBody Account data){

        try{
            data.setDeleteAt(new Date());
            accountService.delete(data);
            return "success";
        }catch (Exception e){
            return e.toString();
        }
    }
}
