package com.asteroid.expression.common.base;

import com.asteroid.expression.login.dao.LoginDao;
import com.asteroid.expression.user.dao.UserDao;
import com.asteroid.expression.user.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import javax.annotation.Resource;

/**
 * @author: YuSai
 * @date: 2020-09-28 11:06
 */
public class BaseService {

    public static Integer id = 9;

    @Resource
    private UserDao userDao;

    public void initLoginUser() {
        Subject subject = SecurityUtils.getSubject();
        subject.getSession().setAttribute("user", userDao.findByName(subject.getPrincipal() + ""));
    }

    public User getLoginUser() {
        Subject subject = SecurityUtils.getSubject();
        return (User)subject.getSession().getAttribute("user");
    }

}
