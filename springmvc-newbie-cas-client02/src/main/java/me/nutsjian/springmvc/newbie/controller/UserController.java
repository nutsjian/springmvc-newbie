package me.nutsjian.springmvc.newbie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class UserController {

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

}
