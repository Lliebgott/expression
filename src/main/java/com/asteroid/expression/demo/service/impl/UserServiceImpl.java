package com.asteroid.expression.demo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.asteroid.expression.common.eenum.StatusEnum;
import com.asteroid.expression.demo.dao.UserDao;
import com.asteroid.expression.demo.model.User;
import com.asteroid.expression.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
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
    public JSONObject addUser(User user) {
        JSONObject result = new JSONObject();
        result.put("success", false);
        user.setAddress(user.getProvince() + user.getCity() + user.getDistrict() + user.getAddress());
        user.setStatus(StatusEnum.EFFECTIVE.getId());
        user.setLast_login_time(new Date());
        int n = userDao.addUser(user);
        if (n > 0) {
            result.put("success", true);
        }
        return result;
    }


    @Override
    public JSONObject checkExist(Integer id, String username) {
        JSONObject result = new JSONObject();
        result.put("valid", true);
        User user = new User();
        if (null != id){
            user.setId(id);
        }
        user.setUsername(username);
        if (userDao.checkExist(user) > 0) {
            result.put("valid", false);
        }
        return result;
    }

    @Override
    public List<User> queryUser() {
        return userDao.queryUser();
    }

}
