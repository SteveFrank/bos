package com.online.bos.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.online.bos.dao.IUserDao;
import com.online.bos.domain.User;
import com.online.bos.service.IUserService;
import com.online.bos.utils.MD5Utils;

@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {
	
	@Resource(name = "userDao")
	private IUserDao userDao;

	@Override
	public User login(User model) {
		
		String password = model.getPassword();
		password = MD5Utils.md5(password);
		
		User user = userDao.findUserByUsernameAndPassword(model.getUsername(), password);
		return user;
	}

	@Override
	public void editPassword(String password, String id) {
		password = MD5Utils.md5(password);
		userDao.executeUpdate("editPassword", password, id);
	}
	
}
