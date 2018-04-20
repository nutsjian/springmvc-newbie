package me.nutsjian.springmvc.newbie.controller;

import com.google.gson.Gson;
import me.nutsjian.springmvc.newbie.dto.UserDTO;
import me.nutsjian.springmvc.newbie.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/users")
public class UserController {

    private final static Logger logger = LogManager.getLogger(UserController.class);

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @ResponseBody
    public String listAll() {
        logger.debug("user controller listAll() ");
        List<UserDTO> dtos = this.userService.listAll();

        Map<String, Object> result = new HashMap<>(3);
        result.put("username", "nutsjian");
        result.put("password", "123456");
        result.put("age", 28);
        return new Gson().toJson(result);
    }

}
