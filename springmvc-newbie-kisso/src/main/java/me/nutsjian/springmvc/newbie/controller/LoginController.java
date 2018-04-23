package me.nutsjian.springmvc.newbie.controller;

import com.baomidou.kisso.SSOHelper;
import com.baomidou.kisso.annotation.Action;
import com.baomidou.kisso.annotation.Login;
import com.baomidou.kisso.security.token.SSOToken;
import com.baomidou.kisso.web.waf.request.WafRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 登录
 */
@Controller
public class LoginController extends BaseController {
    /**
     * 登录 （注解跳过权限验证）
     */
    @Login(action = Action.Skip)
    @RequestMapping("/login")
    public String login() {
        SSOToken st = SSOHelper.getSSOToken(request);
        if (st != null) {
            return redirectTo("/index");
        }
        return "login";
    }

    /**
     * 登录 （注解跳过权限验证）
     */
    @Login(action = Action.Skip)
    @RequestMapping("/loginpost")
    public String loginpost() {
        /**
         * 生产环境需要过滤sql注入
         */
        WafRequestWrapper req = new WafRequestWrapper(request);
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if ("kisso".equals(username) && "123".equals(password)) {

            //记住密码，设置 cookie 时长 1 周 = 604800 秒 【动态设置 maxAge 实现记住密码功能】
            //String rememberMe = req.getParameter("rememberMe");
            //if ( "on".equals(rememberMe) ) {
            //	request.setAttribute(SSOConfig.SSO_COOKIE_MAXAGE, 604800);
            //}
            SSOHelper.setCookie(request, response,
                    SSOToken.create().setId(12306L).setIssuer("12306"),
                    true);// true 会销毁当前 JsessionId 如果用到了 session 相关改为 false

            /*
             * 登录需要跳转登录前页面，自己处理 ReturnURL 使用
             * HttpUtil.decodeURL(xx) 解码后重定向
             */
            return redirectTo("/index");
        }
        return "login";
    }

    @Login(action = Action.Skip)
    @RequestMapping("/loginpost2")
    public String loginpost2() {
        /**
         * 生产环境需要过滤sql注入
         */
        WafRequestWrapper req = new WafRequestWrapper(request);
        String userid = req.getParameter("userid");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        //记住密码，设置 cookie 时长 1 周 = 604800 秒 【动态设置 maxAge 实现记住密码功能】
        //String rememberMe = req.getParameter("rememberMe");
        //if ( "on".equals(rememberMe) ) {
        //	request.setAttribute(SSOConfig.SSO_COOKIE_MAXAGE, 604800);
        //}
        SSOHelper.setCookie(request, response,
                SSOToken.create().setId(Long.parseLong(userid)).setIssuer(username),
                true);// true 会销毁当前 JsessionId 如果用到了 session 相关改为 false

        /*
         * 登录需要跳转登录前页面，自己处理 ReturnURL 使用
         * HttpUtil.decodeURL(xx) 解码后重定向
         */
        return redirectTo("/index");
    }
}
