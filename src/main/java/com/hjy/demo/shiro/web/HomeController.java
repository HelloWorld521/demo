package com.hjy.demo.shiro.web;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * example
 *
 * @author hjy
 * @date 2018/11/30
 **/
@Controller
public class HomeController {

    @RequestMapping({"/", "/index"})
    public String index() {
        return "index";
    }

    /**
     * 登录处理, 此方法不处理登录成功, 由shiro 处理
     *
     * @param request
     * @param map
     * @return
     * @throws Exception
     */
    @RequestMapping("/login")
    public String login(HttpServletRequest request, Map<String, Object> map) throws Exception {
        System.out.println("HomeController.login()");
        // 登录失败从 request 中获取 shiro 处理的异常信息
        // shiroLoginFailure: 就是 shiro 异常类的全类名
        String exception = (String) request.getAttribute("shiroLoginFailure");
        System.out.println("exception = " + exception);
        String msg = "";
        if (exception != null) {
            if (UnknownAccountException.class.getName().equals(exception)) {
                System.out.println("UnknownAccountException --> 账号不存在:");
                msg = "UnknownAccountException --> 账号不存在:";
            } else if (IncorrectCredentialsException.class.getName().equals(exception)) {
                System.out.println("IncorrectCredenrialsException --> 密码不正确");
                msg = "IncorrectCredenrialsException --> 密码不正确";
            } else if ("kaptchaValidateFailed".equals(exception)) {
                System.out.println("kaptchaValidateFailed --> 验证码错误");
                msg = "kaptchaValidateFailed --> 验证码错误";
            } else {
                msg = "else >> " + exception;
                System.out.println("else --> " + exception);
            }
        }
        map.put("msg", msg);
        return "login";
    }

    @RequestMapping("/403")
    public String unauthorizedRole() {
        System.out.println("------ 没有权限 ------");
        return "403";
    }
}
