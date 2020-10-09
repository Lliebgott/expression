package com.asteroid.expression.login.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.asteroid.expression.common.base.BaseService;
import com.asteroid.expression.common.eenum.StatusEnum;
import com.asteroid.expression.common.util.JsonUtil;
import com.asteroid.expression.login.dao.LoginDao;
import com.asteroid.expression.login.service.LoginService;
import com.asteroid.expression.user.model.ContentFile;
import com.asteroid.expression.user.model.Friend;
import com.asteroid.expression.user.model.Group;
import com.asteroid.expression.user.model.User;
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
public class LoginServiceImpl extends BaseService implements LoginService {

    @Autowired
    private Environment environment;

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
        Integer userId = super.getLoginUser().getId();
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
    public JSONArray queryAllContent(Integer id) {
        JSONArray result = new JSONArray();
        String dirPath = environment.getProperty("uploadPath");
        if (id == 0) {
            id = super.getLoginUser().getId();
        }
        List<Map<String, Object>> contents = loginDao.queryAllContent(id);
        for (Map<String, Object> content: contents) {
            JSONObject json = JsonUtil.mapToJson(content);
            int contentId = json.getInteger("id");
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
            contentFile.setContent_id(contentId);
            String forward = "";
            if (null != json.get("p_id")) {
                forward += json.get("content");
                JSONObject j = getPId(json.getInteger("p_id"), forward);
                contentFile.setContent_id(j.getInteger("id"));
                json.put("content", j.getString("forward"));
            }
            List<Map<String, Object>> files = loginDao.queryFileByCId(contentFile);
            json.put("imgs", JsonUtil.listMapToArray(files));
            List<Map<String, Object>> commentLists = loginDao.queryCommentByCId(contentId);
            json.put("commentnum", "评论(" + commentLists.size() + ")");
            json.put("comments", JsonUtil.listMapToArray(commentLists));
            List<Map<String, Object>> shareLists = loginDao.queryShareByCId(contentId);
            json.put("sharenum", "转发(" + shareLists.size() + ")");
            json.put("shares", shareLists);
            result.add(json);
        }
        return result;
    }

    private JSONObject getPId(Integer pid, String forward) {
        JSONObject json = new JSONObject();
        Integer id = pid;
        while (null != pid) {
            List<Map<String, Object>> lists = loginDao.queryContentById(pid);
            if (lists.size() > 0) {
                JSONObject j = JsonUtil.mapToJson(lists.get(0));
                pid = j.getInteger("p_id");
                forward += "&nbsp;&nbsp;&nbsp;<a>" + j.get("name") + ":<a/>" + j.get("content");
                if (null != pid) {
                    id = j.getInteger("p_id");
                }
            } else {
                pid = null;
            }
        }
        json.put("id", id);
        json.put("forward", forward);
        return json;
    }

}
