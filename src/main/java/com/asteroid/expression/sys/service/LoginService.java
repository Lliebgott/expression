package com.asteroid.expression.sys.service;

import com.asteroid.expression.common.config.druid.DruidConfiguration;
import com.asteroid.expression.common.config.properties.YamlProps;
import com.asteroid.expression.common.config.security.CustomUserService;
import com.asteroid.expression.common.util.JsonUtil;
import com.asteroid.expression.sys.dao.LoginDao;
import com.asteroid.expression.sys.entity.ContentFile;
import com.asteroid.expression.sys.entity.Friend;
import com.asteroid.expression.sys.entity.Group;
import com.asteroid.expression.sys.entity.User;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * @author: YuSai
 * @date: 2020-10-23 15:56
 */
@Service("LoginService")
@Transactional(rollbackFor={IllegalArgumentException.class})
public class LoginService {

    @Autowired
    private YamlProps yamlProps;

    @Autowired
    private LoginDao loginDao;

    public JSONArray queryLeftTreeData() {
        int userId = CustomUserService.user.getId();
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

    public JSONArray queryAllContent(Integer id) {
        JSONArray result = new JSONArray();
        String dirPath = yamlProps.getProps().getUploadPath();
        if (id == 0) {
            id = CustomUserService.user.getId();
        }
        List<Map<String, Object>> contents = loginDao.queryAllContent(id);
        for (Map<String, Object> content: contents) {
            JSONObject json = JSONObject.fromObject(JsonUtil.map2json(content));
            int contentId = json.getInt("id");
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
                JSONObject j = getPId(json.getInt("p_id"), forward);
                contentFile.setContent_id(j.getInt("id"));
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

    public JSONObject getComment(Integer contentId) {
        JSONObject retJson = new JSONObject();
        List<Map<String, Object>> commentLists = loginDao.queryCommentByCId(contentId);
        retJson.put("commentnum", "评论(" + commentLists.size() + ")");
        retJson.put("comments", JsonUtil.listMapToArray(commentLists));
        return retJson;
    }

    private JSONObject getPId(Integer pid, String forward) {
        JSONObject json = new JSONObject();
        Integer id = pid;
        while (null != pid) {
            List<Map<String, Object>> lists = loginDao.queryContentById(pid);
            if (lists.size() > 0) {
                JSONObject j = JsonUtil.mapToJson(lists.get(0));
                if (null == j.get("p_id")) {
                    pid = null;
                    forward += "&nbsp;&nbsp;&nbsp;<a>" + j.get("name") + ":<a/>" + j.get("content");
                } else {
                    pid = j.getInt("p_id");
                    forward += "&nbsp;&nbsp;&nbsp;<a>" + j.get("name") + ":<a/>" + j.get("content");
                    id = j.getInt("p_id");
                }
            }
        }
        json.put("id", id);
        json.put("forward", forward);
        return json;
    }

}
