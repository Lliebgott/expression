package com.asteroid.expression.login.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.asteroid.expression.common.base.constant.SystemStaticConst;
import com.asteroid.expression.common.util.node.NodeUtil;
import com.asteroid.expression.demo.dao.UserDao;
import com.asteroid.expression.demo.model.User;
import com.asteroid.expression.demo.model.UserGroup;
import com.asteroid.expression.demo.model.UserUser;
import com.asteroid.expression.login.dao.LoginDao;
import com.asteroid.expression.login.model.Menu;
import com.asteroid.expression.login.service.LoginService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: YuSai
 * @date: 2020-09-24 14:53
 */

@Service("LoginService")
public class LoginServiceImpl implements LoginService {

    @Resource
    private LoginDao loginDao;

    @Override
    public JSONObject checkLogin(User user) {
        JSONObject result = new JSONObject();
        result.put("success", true);
        List<User> users = loginDao.checkLogin(user);
        if (users.size() == 0) {
            result.put("success", false);
        }
        return result;
    }

    @Override
    public JSONArray queryLeftTreeData() {
        // 查询用户组
        List<UserGroup> userGroups = loginDao.queryGroupByUserId(9);
        JSONArray array = new JSONArray();
        if (userGroups.size() == 0) {
            JSONObject group = new JSONObject();
            group.put("id", "1");
            group.put("pId", "0");
            group.put("name", "我的好友");
            array.add(group);
        } else {
            for (UserGroup userGroup: userGroups) {
                JSONObject group = new JSONObject();
                group.put("id", userGroup.getId());
                group.put("pId", "0");
                group.put("name", userGroup.getGroup_name());
                array.add(group);
                UserUser userUser = new UserUser();
                userUser.setGroup_id(9);
                userUser.setGroup_id(userGroup.getId());
                List<User> users = loginDao.queryGroupUser(userUser);
                if (users.size() > 0) {
                    for (User u: users) {
                        JSONObject user = new JSONObject();
                        user.put("id", u.getId());
                        user.put("pId", userGroup.getId());
                        user.put("name", u.getName() + "[" + u.getUsername() + "]");
                        array.add(user);
                    }
                }
            }
        }
        System.out.println(array);
        return array;
    }


    /**
     * 功能描述：加载菜单节点数据
     *
     * @return 菜单节点数据
     */
    @Override
    public Map<String,Object> queryMenu(){
        Map<String,Object> result = new HashMap<>();
        List<Menu> menuList = new ArrayList<>();
        Map<Long,Menu> treeMap = new HashMap<>();
        // todo 获取登录用户信息
        User user = new User();
        List<Menu> menus = loginDao.queryMenu(user);
        if (menus.size() > 0) {
            for(Menu tree: menus){
                treeMap.put(tree.getId(),tree);
            }
            menuList = NodeUtil.getChildNodes(new ArrayList<>(treeMap.values()),0l);
        }
        result.put("data", menus);
        result.put(SystemStaticConst.RESULT, SystemStaticConst.SUCCESS);
        return result;
    }

}
