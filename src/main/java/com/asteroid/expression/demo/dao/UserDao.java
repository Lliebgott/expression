package com.asteroid.expression.demo.dao;

import com.asteroid.expression.demo.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: YuSai
 * @date: 2020-09-23 15:29
 */
@Repository("UserDao")
public interface UserDao {

    Boolean addUser(User user);

    List<User> queryUser();

}
