package com.asteroid.expression.demo.controller;

import com.asteroid.expression.demo.model.User;
import com.asteroid.expression.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author: YuSai
 * @date: 2020-09-23 14:59
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/add")
    public String add() {
        return "index";
    }

    @RequestMapping("/addUser")
    public void addUser(HttpServletRequest request) {
        String name = request.getParameter("name");
        String age = request.getParameter("age");
        User user = new User();
        user.setName(name);
        user.setAge(Integer.parseInt(age));
        userService.addUser(user);
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

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

}
