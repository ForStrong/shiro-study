package com.yogurt.springbootshiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
    @RequestMapping("/")
    public String hello(Model model) {
        model.addAttribute("msg", "hello shiro");
        return "index";
    }

    @RequestMapping("/user/add")
    public String userAdd() {
        return "user/add";
    }

    @RequestMapping("/user/update")
    public String userUpdate() {
        return "user/update";
    }

    @RequestMapping("/toLogin")
    public String toLogin() {
        return "login";
    }

    @RequestMapping("/login")
    public String login(String username, String password, Model model) {
        Subject currentUser = SecurityUtils.getSubject();

        if (!currentUser.isAuthenticated()) {//用户未授权就进行登陆操作
            AuthenticationToken authenticationToken = new UsernamePasswordToken(username, password);
            try {
                //底层实现是通过Realm来实现认证的 doGetAuthenticationInfo
                currentUser.login(authenticationToken);
            } catch (UnknownAccountException e) {
                model.addAttribute("msg", "用户名错误");
                return "login";
            } catch (IncorrectCredentialsException e) {
                model.addAttribute("msg", "密码错误");
                return "login";
            }
        }
        //model.addAttribute("msg", "登陆成功");
        return "index";
    }
}
