package com.asteroid.expression.common.base;

import com.asteroid.expression.user.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * @author: YuSai
 * @date: 2020-09-28 11:06
 */
public class BaseService {

    public static Integer id = 9;

    public User getLoginUser() {
        Subject subject = SecurityUtils.getSubject();
        return (User)subject.getSession().getAttribute("user");
    }

}
