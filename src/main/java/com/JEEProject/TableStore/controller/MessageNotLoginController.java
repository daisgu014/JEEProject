package com.JEEProject.TableStore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "messageNotLogin")
public class MessageNotLoginController {

    @RequestMapping(value = "")
    public String getMessageNotLogin() {
        return "messageNotLogin";
    }
}
