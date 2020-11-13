package com.asteroid.expression.sys.controller;

import com.asteroid.expression.common.config.security.CustomUserService;
import com.asteroid.expression.sys.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.text.DateFormat;
import java.util.Date;

/**
 * @author: YuSai
 * @date: 2020-10-23 15:54
 */
@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/register")
    public ModelAndView register(){
        return new ModelAndView("login/register");
    }

    @RequestMapping(value = "/zeroModal")
    public ModelAndView zeroModal(){
        return new ModelAndView("login/zeroModal");
    }

    @RequestMapping(value = "/home")
    public ModelAndView home(@RequestParam("id") Integer id, @RequestParam("flag") boolean flag) {
        ModelAndView view = new ModelAndView("login/home");
        view.addObject("contents", loginService.queryAllContent(id));
        view.addObject("flag", flag);
        return view;
    }

    @RequestMapping("/index")
    public ModelAndView hello(){
        ModelAndView view = new ModelAndView();
        // 普通用户
        if (CustomUserService.onlyNormalUser()) {
            view.setViewName("login/index");
            view.addObject("zNodes", loginService.queryLeftTreeData());
        } else {
            view.setViewName("hello");
            view.addObject("now", DateFormat.getDateTimeInstance().format(new Date()));
        }
        return view;
    }

}
