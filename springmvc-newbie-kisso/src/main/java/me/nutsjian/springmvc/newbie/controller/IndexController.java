package me.nutsjian.springmvc.newbie.controller;

import com.baomidou.kisso.SSOConfig;
import com.baomidou.kisso.SSOHelper;
import com.baomidou.kisso.annotation.Action;
import com.baomidou.kisso.annotation.Login;
import com.baomidou.kisso.security.token.SSOToken;
import me.nutsjian.springmvc.newbie.dto.Response;
import me.nutsjian.springmvc.newbie.kisso.WebKissoConfigurer;
import me.nutsjian.springmvc.newbie.utils.CaptchaUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
@RequestMapping
public class IndexController extends BaseController {

    @GetMapping
    @ResponseBody
    public String index() {
        return Response.ok();
    }

    /**
     * <p>
     * SSOHelper.getToken(request)
     *
     * 从 Cookie 解密 token 使用场景，拦截器
     * </p>
     *
     * <p>
     * SSOHelper.attrToken(request)
     *
     * 非拦截器使用减少二次解密
     * </p>
     */
    @RequestMapping("/index")
    public String index(Model model) {
        SSOToken st = SSOHelper.attrToken(request);
        if (st != null) {
            System.err.println(" Long 类型 ID: " + st.getId());
            model.addAttribute("userName", st.getIssuer());
        }
        System.err.println(" 启动 cookie name ：" + SSOConfig.getInstance().getCookieName());
        return "index";
    }

    /**
     * 验证码 （注解跳过权限验证）
     */
    @Login(action = Action.Skip)
    @ResponseBody
    @RequestMapping("/verify")
    public void verify() {
        try {
            String verifyCode = CaptchaUtil.outputImage(response.getOutputStream());
            System.out.println("验证码:" + verifyCode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 异常 404 提示页
     */
    @RequestMapping("/404")
    public String error_404() {
        return "error/404";
    }
}
