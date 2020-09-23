package com.asteroid.expression.demo.service;

import com.asteroid.expression.demo.model.User;

import java.util.List;

/**
 * @author: YuSai
 * @date: 2020-09-23 15:25
 */
public interface UserService {

    Boolean addUser(User user);

    List<User> queryUser();

}
