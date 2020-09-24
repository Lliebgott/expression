package com.asteroid.expression.login.controller;

import com.asteroid.expression.login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping("/main")
    public String main() {
        return "main/main";
    }

}
