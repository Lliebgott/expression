package com.asteroid.expression.login.service.impl;

import com.asteroid.expression.common.base.constant.SystemStaticConst;
import com.asteroid.expression.common.util.node.NodeUtil;
import com.asteroid.expression.demo.dao.UserDao;
import com.asteroid.expression.demo.model.User;
import com.asteroid.expression.login.dao.LoginDao;
import com.asteroid.expression.login.model.Menu;
import com.asteroid.expression.login.service.LoginService;
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
