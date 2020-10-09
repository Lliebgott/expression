package com.asteroid.expression.user.service;

import com.alibaba.fastjson.JSONObject;
import com.asteroid.expression.user.model.User;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author: YuSai
 * @date: 2020-09-23 15:25
 */
public interface UserService {

    User findByName(String username);

    JSONObject addUser(User user);

    JSONObject checkExist(Integer id, String username);

    JSONObject publish(String content, MultipartFile files[]);

    JSONObject comment(Integer contentId, Integer friendId, String comment);

    JSONObject forward(Integer contentId, String contentText);

    JSONObject collect(Integer contentId, Integer collectId, Integer friendId, boolean state);

    JSONObject thumb(Integer contentId, Integer friendId, boolean state);

}
