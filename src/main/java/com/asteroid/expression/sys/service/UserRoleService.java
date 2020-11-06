package com.asteroid.expression.sys.service;

import com.asteroid.expression.common.base.dao.GenericDao;
import com.asteroid.expression.common.base.service.GenericService;
import com.asteroid.expression.sys.dao.UserAssociateRoleDao;
import com.asteroid.expression.sys.entity.QueryUserAssociateRole;
import com.asteroid.expression.sys.entity.User;
import com.asteroid.expression.sys.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("UserRoleService")
@Transactional(rollbackFor={IllegalArgumentException.class})
public class UserRoleService extends GenericService<UserRole, QueryUserAssociateRole> {
	@Autowired
	@SuppressWarnings("SpringJavaAutowiringInspection")
	private UserAssociateRoleDao userAssociateRoleDao;
	@Override
	protected GenericDao<UserRole, QueryUserAssociateRole> getDao() {
		return userAssociateRoleDao;
	}

	/**
	 * 功能描述：根据用户的ID来删除用户的权限数据
	 * @param user
	 * @return
	 */
	public boolean removeUserRole(User user){
		return userAssociateRoleDao.removeUserRole(user)>0;
	}
}