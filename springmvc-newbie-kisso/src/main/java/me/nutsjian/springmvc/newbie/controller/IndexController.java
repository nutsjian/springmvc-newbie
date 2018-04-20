package me.nutsjian.springmvc.newbie.controller;

import me.nutsjian.springmvc.newbie.dto.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping
public class IndexController {

    @GetMapping
    @ResponseBody
    public String index() {
        return Response.ok();
    }

}
