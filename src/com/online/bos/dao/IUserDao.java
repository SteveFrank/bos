package com.online.bos.dao;

import java.util.List;

import com.online.bos.dao.base.IBaseDao;
import com.online.bos.domain.Function;
import com.online.bos.domain.User;

public interface IUserDao extends IBaseDao<User> {

	public User findUserByUsernameAndPassword(String username, String password);

	public User findUserByUsername(String username);

	public List<Function> findFunctionByUserId(String id);

}
