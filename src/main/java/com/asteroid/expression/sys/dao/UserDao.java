package com.asteroid.expression.sys.dao;

import com.asteroid.expression.common.base.dao.GenericDao;
import com.asteroid.expression.sys.entity.*;

import java.util.List;
import java.util.Map;

/**
 * @author: YuSai
 * @date: 2020-10-21 17:06
 */
public interface UserDao extends GenericDao<User, QueryUser> {

    /**
     * 功能描述：根据账号来获取用户信息
     * @param login
     * @return
     */
    User findByUserName(String login);

    int checkExist(User user);

    int publish(Content content);

    int saveContextFile(ContentFile contentFile);

    int saveThumb(Thumb thumb);

    int cancelThumb(Thumb thumb);

    int saveComment(Comment comment);

    int saveCollect(CollectContent collectContent);

    int cancelCollect(CollectContent collectContent);

    List<Map<String, Object>> searchUser(User user);

}