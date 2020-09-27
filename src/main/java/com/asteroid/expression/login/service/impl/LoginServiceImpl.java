package com.asteroid.expression.login.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.asteroid.expression.common.base.constant.SystemStaticConst;
import com.asteroid.expression.common.eenum.StatusEnum;
import com.asteroid.expression.common.util.node.NodeUtil;
import com.asteroid.expression.user.model.Friend;
import com.asteroid.expression.user.model.User;
import com.asteroid.expression.user.model.Group;
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

    @Override
    public JSONObject checkLogin(User user) {
        JSONObject result = new JSONObject();
        List<User> users = loginDao.checkLogin(user);
        if (users.size() == 0) {
            result.put("success", false);
            result.put("msg", "用户名或密码不正确");
        } else {
            Integer status = users.get(0).getStatus();
            if (status.equals(StatusEnum.INVALID.getId())) {
                result.put("success", false);
                result.put("msg", "用户已注销");
            } else {
                result.put("success", true);
            }
        }
        return result;
    }

    @Override
    public JSONArray queryLeftTreeData() {
        // todo 查询用户组
        Integer userId = 9;
        List<Group> groups = loginDao.queryGroupByUserId(userId);
        JSONArray array = new JSONArray();
        if (groups.size() == 0) {
            JSONObject group = new JSONObject();
            group.put("id", "1");
            group.put("pId", "0");
            group.put("name", "我的好友");
            array.add(group);
        } else {
            for (Group userGroup: groups) {
                JSONObject group = new JSONObject();
                group.put("id", userGroup.getId());
                group.put("pId", "0");
                group.put("name", userGroup.getGroup_name());
                group.put("icon", "../../../static/image/group.png");
                array.add(group);
                Friend friend = new Friend();
                friend.setUser_id(userId);
                friend.setGroup_id(userGroup.getId());
                List<User> users = loginDao.queryGroupUser(friend);
                if (users.size() > 0) {
                    for (User u: users) {
                        JSONObject user = new JSONObject();
                        user.put("id", u.getId());
                        user.put("pId", userGroup.getId());
                        user.put("name", u.getName() + "[" + u.getUsername() + "]");
                        user.put("icon", "../../../static/image/user.png");
                        array.add(user);
                    }
                }
            }
        }
        return array;
    }

}
