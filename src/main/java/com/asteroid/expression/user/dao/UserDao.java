package com.asteroid.expression.user.dao;

import com.asteroid.expression.user.model.Content;
import com.asteroid.expression.user.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author: YuSai
 * @date: 2020-09-23 15:29
 */
@Mapper
public interface UserDao {

    int addUser(User user);

    int checkExist(User user);

    int publish(Content content);

}
