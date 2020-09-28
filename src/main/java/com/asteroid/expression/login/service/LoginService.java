package com.asteroid.expression.login.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.asteroid.expression.user.model.User;

/**
 * @author: YuSai
 * @date: 2020-09-24 14:53
 */
public interface LoginService {

    JSONObject checkLogin(User user);

    JSONArray queryLeftTreeData();

    JSONArray queryAllContent();

}
