package com.asteroid.expression.login.controller;

import com.alibaba.fastjson.JSONObject;
import com.asteroid.expression.common.base.BaseService;
import com.asteroid.expression.login.service.LoginService;
import com.asteroid.expression.user.model.User;
import com.asteroid.expression.user.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author: YuSai
 * @date: 2020-09-24 14:53
 */
@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login2(@Param("username") String username, @Param("password") String password) {
        ModelAndView view = new ModelAndView("main/index");
        Subject subject = SecurityUtils.getSubject();
        // 添加用户认证信息
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
        try {
            // 进行验证，这里可以捕获异常，然后返回对应信息
            subject.login(usernamePasswordToken);
        } catch (UnknownAccountException e) {
            view.addObject("result", "用户不存在");
            view.setViewName("login/login");
            return view;
        } catch (IncorrectCredentialsException e) {
            view.addObject("username", username);
            view.addObject("result", "密码错误");
            view.setViewName("login/login");
            return view;
        }
        // 登录用户信息放入session
        subject.getSession().setAttribute("user", userService.findByName(username));
        view.addObject("zNodes", loginService.queryLeftTreeData());
        return view;
    }

    @RequestMapping(value = "/register")
    public String register() {
        return "login/register";
    }

    @RequestMapping(value = "/checkLogin", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject index(User user) {
        return loginService.checkLogin(user);
    }

    @RequestMapping(value = "/main")
    public String main() {
        return "main/main";
    }

    @RequestMapping(value = "/home")
    public ModelAndView home(@RequestParam("id") Integer id) {
        ModelAndView view = new ModelAndView("main/home");
        view.addObject("contents", loginService.queryAllContent(id));
        return view;
    }

}
