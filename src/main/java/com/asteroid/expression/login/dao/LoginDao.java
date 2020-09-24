package com.asteroid.expression.login.dao;

import com.asteroid.expression.demo.model.User;
import com.asteroid.expression.login.model.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author: YuSai
 * @date: 2020-09-24 14:53
 */
@Mapper
public interface LoginDao {

    List<Menu> queryMenu(User user);

}
