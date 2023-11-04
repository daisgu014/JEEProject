package com.JEEProject.TableStore.controller;

import com.JEEProject.TableStore.Model.Account;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "user")
public class UserProfileController {
    @RequestMapping(value = "/profile")
    public String getAllUserProfile(ModelMap modelMap) {
        return "userProfile";
    }

    @RequestMapping(value = "/password")
    public String getAllUserPassword(ModelMap modelMap) {
        return "userPassword";
    }

    @RequestMapping(value = "/purchased")
    public String getAllUserPurchased(ModelMap modelMap) {
        return "userPurchased";
    }
}