package com.asteroid.expression.login.dao;

import com.asteroid.expression.user.model.Friend;
import com.asteroid.expression.user.model.User;
import com.asteroid.expression.user.model.Group;
import com.asteroid.expression.login.model.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author: YuSai
 * @date: 2020-09-24 14:53
 */
@Mapper
public interface LoginDao {

    List<Group> queryGroupByUserId(int id);

    List<User> queryGroupUser(Friend friend);

    List<User> checkLogin(User user);

}
