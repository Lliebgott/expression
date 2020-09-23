package com.asteroid.expression.demo.service.impl;

import com.asteroid.expression.demo.dao.UserDao;
import com.asteroid.expression.demo.model.User;
import com.asteroid.expression.demo.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: YuSai
 * @date: 2020-09-23 15:26
 */
@Service("UserService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public Boolean addUser(User user) {
        return userDao.addUser(user);
    }

    @Override
    public List<User> queryUser() {
        return userDao.queryUser();
    }

}
