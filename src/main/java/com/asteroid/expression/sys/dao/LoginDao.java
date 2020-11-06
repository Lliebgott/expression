package com.asteroid.expression.sys.dao;

import com.asteroid.expression.common.base.dao.GenericDao;
import com.asteroid.expression.sys.entity.*;

import java.util.List;
import java.util.Map;

/**
 * @author: YuSai
 * @date: 2020-10-21 17:06
 */
public interface LoginDao extends GenericDao<User, QueryUser> {


    List<Group> queryGroupByUserId(int id);

    List<User> queryGroupUser(Friend friend);

    List<Map<String, Object>> queryAllContent(int id);

    List<Map<String, Object>> queryContentById(int id);

    List<Map<String, Object>> queryFileByCId(ContentFile contentFile);

    List<Map<String, Object>> queryCommentByCId(int id);

    List<Map<String, Object>> queryShareByCId(int id);

}