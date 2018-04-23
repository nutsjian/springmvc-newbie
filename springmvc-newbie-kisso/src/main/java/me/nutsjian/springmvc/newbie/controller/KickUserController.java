package me.nutsjian.springmvc.newbie.controller;

import com.baomidou.kisso.SSOHelper;
import com.baomidou.kisso.annotation.Action;
import com.baomidou.kisso.annotation.Login;
import me.nutsjian.springmvc.newbie.dto.Response;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class KickUserController extends BaseController {

    @Login(action = Action.Skip)
    @RequestMapping("/kick")
    @ResponseBody
    public String kick(String userid) {
        SSOHelper.kickLogin(userid);
        return Response.ok();
    }

}
