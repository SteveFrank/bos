package com.online.bos.dao;

import com.online.bos.dao.base.IBaseDao;
import com.online.bos.domain.User;

public interface IUserDao extends IBaseDao<User> {

	public User findUserByUsernameAndPassword(String username, String password);

}
