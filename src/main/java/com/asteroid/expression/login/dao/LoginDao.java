package com.asteroid.expression.login.dao;

import com.asteroid.expression.user.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author: YuSai
 * @date: 2020-09-24 14:53
 */
@Mapper
public interface LoginDao {

    List<Group> queryGroupByUserId(int id);

    List<User> queryGroupUser(Friend friend);

    List<User> checkLogin(User user);

    List<Map<String, Object>> queryAllContent(int id);

    List<Map<String, Object>> queryContentById(int id);

    List<Map<String, Object>> queryFileByCId(ContentFile contentFile);

    List<Map<String, Object>> queryCommentByCId(int id);

    List<Map<String, Object>> queryShareByCId(int id);

}
