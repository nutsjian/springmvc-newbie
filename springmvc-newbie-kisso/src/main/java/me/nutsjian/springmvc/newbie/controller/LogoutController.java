package me.nutsjian.springmvc.newbie.controller;

import com.baomidou.kisso.SSOHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 退出登录
 */
@Controller
public class LogoutController extends BaseController {

    /**
     * 退出登录
     */
    @RequestMapping("/logout")
    public String logout() {
        /**
         * <p>
         * SSO 退出，清空退出状态即可
         * </p>
         *
         * <p>
         * 子系统退出 SSOHelper.logout(request, response); 注意 sso.properties 包含 退出到
         * SSO 的地址 ， 属性 sso.logout.url 的配置
         * </p>
         */
        SSOHelper.clearLogin(request, response);
        return redirectTo("/login");
    }

}
