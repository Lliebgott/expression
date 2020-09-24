package com.asteroid.expression.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.asteroid.expression.demo.model.User;
import com.asteroid.expression.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author: YuSai
 * @date: 2020-09-23 14:59
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/addUser"}, method = RequestMethod.POST)
    @ResponseBody
    public JSONObject addUser(User user) {
        return userService.addUser(user);
    }

    @RequestMapping(value = {"/checkExist"}, method = RequestMethod.POST)
    @ResponseBody
    public JSONObject checkExist(String username) {
        return userService.checkExist(null, username);
    }

    @RequestMapping("/add")
    public String add() {
        return "index";
    }

    @RequestMapping("/queryUser")
    public String queryUser() {
        return "selectList";
    }

    @RequestMapping("/select")
    @ResponseBody
    public List<User> select() {
        return userService.queryUser();
    }

    @RequestMapping("/test")
    public String test() {
        return "test";
    }

}
