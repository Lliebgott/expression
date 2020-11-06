package com.asteroid.expression.sys.dao;


import com.asteroid.expression.common.base.dao.GenericDao;
import com.asteroid.expression.sys.entity.QueryUserAssociateRole;
import com.asteroid.expression.sys.entity.User;
import com.asteroid.expression.sys.entity.UserRole;

/**
 *@author linzf
 **/
public interface UserAssociateRoleDao extends GenericDao<UserRole, QueryUserAssociateRole> {

    /**
     * 功能描述：根据用户的ID来删除用户的权限数据
     * @param user
     * @return
     */
    int removeUserRole(User user);
}