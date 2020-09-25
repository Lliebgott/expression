package com.asteroid.expression.login.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.asteroid.expression.demo.model.User;

import java.util.Map;

/**
 * @author: YuSai
 * @date: 2020-09-24 14:53
 */
public interface LoginService {

    Map<String,Object> queryMenu();

    JSONObject checkLogin(User user);

    JSONArray queryLeftTreeData();

}
