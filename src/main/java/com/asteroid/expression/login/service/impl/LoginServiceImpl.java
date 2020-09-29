package com.asteroid.expression.login.service.impl;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.asteroid.expression.common.eenum.StatusEnum;
import com.asteroid.expression.common.util.JsonUtil;
import com.asteroid.expression.login.dao.LoginDao;
import com.asteroid.expression.login.service.LoginService;
import com.asteroid.expression.user.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author: YuSai
 * @date: 2020-09-24 14:53
 */

@Service("LoginService")
public class LoginServiceImpl implements LoginService {

    @Autowired
    private Environment environment;

    @Resource
    private LoginDao loginDao;

    @Override
    public JSONObject checkLogin(User user) {
        queryAllContent();
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

    @Override
    public JSONArray queryAllContent() {
        JSONArray result = new JSONArray();
        String dirPath = environment.getProperty("uploadPath");
        List<Map<String, Object>> contents = loginDao.queryAllContent(9);
        for (Map<String, Object> content: contents) {
            JSONObject json = JsonUtil.mapToJson(content);
            int id = json.getInteger("id");
            if (null != json.get("thumb")) {
                json.put("thumb_class", "glyphicon-heart");
                json.put("thumb_text", "取消点赞");
            } else {
                json.put("thumb_class", "glyphicon-heart-empty");
                json.put("thumb_text", "点赞");
            }
            if (null != json.get("collect")) {
                json.put("collect_class", "glyphicon-star");
                json.put("collect_text", "取消收藏");
            } else {
                json.put("collect_class", "glyphicon-star-empty");
                json.put("collect_text", "收藏");
            }
            // 查询图片
            ContentFile contentFile = new ContentFile();
            contentFile.setId(dirPath.length());
            contentFile.setContent_id(id);
            if (null != json.get("p_id")) {
                contentFile.setContent_id(json.getInteger("p_id"));
            }
            List<Map<String, Object>> files = loginDao.queryFileByCId(contentFile);
            json.put("imgs", JsonUtil.listMapToArray(files));
            List<Map<String, Object>> commentLists = loginDao.queryCommentByCId(id);
            json.put("commentnum", "评论(" + commentLists.size() + ")");
            json.put("comments", JsonUtil.listMapToArray(commentLists));
            List<Map<String, Object>> shareLists = loginDao.queryShareByCId(id);
            json.put("sharenum", "转发(" + shareLists.size() + ")");
            json.put("shares", shareLists);
            result.add(json);
        }
        return result;
    }

}
