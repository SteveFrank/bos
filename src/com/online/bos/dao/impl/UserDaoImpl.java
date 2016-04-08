package com.online.bos.dao.impl; 

import java.util.List;

import org.springframework.stereotype.Repository;

import com.online.bos.dao.IUserDao;
import com.online.bos.dao.base.impl.BaseDaoImpl;
import com.online.bos.domain.Function;
import com.online.bos.domain.User;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements IUserDao {
	
	/**
	 * 根据输入的用户名和用户密码查询用户
	 */
	@SuppressWarnings("unchecked")
	@Override
	public User findUserByUsernameAndPassword(String username, String password) {
		String hql = "from com.online.bos.domain.User user where user.username = ? and user.password = ?";
		List<User> list = this.getHibernateTemplate().find(hql,username,password);
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public User findUserByUsername(String username) {
		String hql = "from com.online.bos.domain.User user where user.username = ?";
		List<User> list = this.getHibernateTemplate().find(hql,username);
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<Function> findFunctionByUserId(String id) {
		return null;
	}
	
}
