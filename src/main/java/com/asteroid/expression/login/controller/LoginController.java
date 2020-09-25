package com.asteroid.expression.login.controller;

import com.alibaba.fastjson.JSONObject;
import com.asteroid.expression.demo.model.User;
import com.asteroid.expression.login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
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

    /**
     * 功能描述：加载首页菜单节点的数据
     * @return
     */
    @RequestMapping(value="/mainTree",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String,Object> mainTree(){
        return loginService.queryMenu();
    }

    @RequestMapping("/home")
    public String home() {
        return "main/home";
    }

    @RequestMapping("/main")
    public String main() {
        return "main/main";
    }

    @RequestMapping("/index")
    public ModelAndView index() {
        ModelAndView view = new ModelAndView("main/index");
        view.addObject("zNodes", loginService.queryLeftTreeData());
        return view;
    }

    @RequestMapping(value = {"/checkLogin"}, method = RequestMethod.POST)
    @ResponseBody
    public JSONObject index(User user) {
        return loginService.checkLogin(user);
    }

}
