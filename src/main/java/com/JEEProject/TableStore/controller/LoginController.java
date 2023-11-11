package com.JEEProject.TableStore.controller;

import com.JEEProject.TableStore.Model.Account;
import com.JEEProject.TableStore.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path = "user")
@Controller
@RequiredArgsConstructor
public class LoginController {
    private final PasswordEncoder passwordEncoder;
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
    public String getSendEmailPassword(ModelMap modelMap, HttpSession session){
        Account user = (Account) session.getAttribute("account");
        if (user == null){
            modelMap.addAttribute("success","");
            modelMap.addAttribute("error","");
            return "userGetPassword";
        } else {
            return "redirect:/user/profile";
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String checkLogin(ModelMap modelMap, @RequestParam("username") String username, @RequestParam("password") String password, HttpServletRequest request) {

        if (userService.checkAccount(username)) {
            Account user = userService.getAccountByUsername(username);
            if (passwordEncoder.matches(password, user.getPassword())) {
                HttpSession session = request.getSession();
                session.setAttribute("account", user);
                return "redirect:/catalog";
            } else {
                modelMap.addAttribute("error","Mật khẩu không đúng!");
                return "userLogin";
            }
        } else {
            modelMap.addAttribute("error","Tài khoản không tồn tại!");
            return "userLogin";
        }
    }

    @RequestMapping(value = "/login/getPassword", method = RequestMethod.POST)
    public String checkSendEmailPassword(ModelMap modelMap, @RequestParam("email") String email){
        if (!userService.findEmail(email)){
            modelMap.addAttribute("success","");
            modelMap.addAttribute("error","Email không tồn tại!");}
        else {
            String newPassword = userService.randomPassword();
            Account user = userService.getAccountByEmail(email);
            userService.sendEmailUserPassword(user, newPassword);
            user.setPassword(passwordEncoder.encode(newPassword));
            userService.updateAccount(user);
            modelMap.addAttribute("error","");
            modelMap.addAttribute("success", "Chúng tôi đã gửi cho bạn một email, xin hãy kiểm tra và đăng nhập lại tài khoản của bạn!");
        }
        return "userGetPassword";
    }
}

