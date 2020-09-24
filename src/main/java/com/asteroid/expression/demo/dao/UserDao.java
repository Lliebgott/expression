package com.asteroid.expression.demo.dao;

import com.asteroid.expression.demo.model.User;
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

    List<User> queryUser();

}
