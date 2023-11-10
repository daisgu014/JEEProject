package com.JEEProject.TableStore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(path = "/accessDenied")
public class AccessDenied {
    @RequestMapping(value = "",method = RequestMethod.GET)
    public String getAceessDenied(){
        return "AccessDenied";
    }
}
