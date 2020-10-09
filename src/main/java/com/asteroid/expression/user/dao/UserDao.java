package com.asteroid.expression.user.dao;

import com.asteroid.expression.user.model.*;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author: YuSai
 * @date: 2020-09-23 15:29
 */
@Mapper
public interface UserDao {

    User findByName(String username);

    int addUser(User user);

    int checkExist(User user);

    int publish(Content content);

    int saveContextFile(ContentFile contentFile);

    int saveThumb(Thumb thumb);

    int cancelThumb(Thumb thumb);

    int saveComment(Comment comment);

    int saveCollect(CollectContent collectContent);

    int cancelCollect(CollectContent collectContent);

}
