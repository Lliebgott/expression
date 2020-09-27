package com.asteroid.expression.login.controller;

import com.alibaba.fastjson.JSONObject;
import com.asteroid.expression.user.model.User;
import com.asteroid.expression.login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @author: YuSai
 * @date: 2020-09-24 14:53
 */
@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping("/login")
    public String login() {
        return "login/login";
    }

    @RequestMapping("/register")
    public String register() {
        return "login/register";
    }

    @RequestMapping(value = {"/checkLogin"}, method = RequestMethod.POST)
    @ResponseBody
    public JSONObject index(User user) {
        return loginService.checkLogin(user);
    }

    @RequestMapping("/main")
    public String main() {
        return "main/main";
    }

    @RequestMapping("/home")
    public String home() {
        return "main/home";
    }

    @RequestMapping("/index")
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("main/index");
        view.addObject("zNodes", loginService.queryLeftTreeData());
        return view;
    }

}
