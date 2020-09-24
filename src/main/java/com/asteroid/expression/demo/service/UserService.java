package com.asteroid.expression.demo.service;

import com.alibaba.fastjson.JSONObject;
import com.asteroid.expression.demo.model.User;

import java.util.List;

/**
 * @author: YuSai
 * @date: 2020-09-23 15:25
 */
public interface UserService {

    JSONObject addUser(User user);

    JSONObject checkExist(Integer id, String username);

    List<User> queryUser();

}
