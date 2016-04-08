package com.online.bos.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.online.bos.dao.IUserDao;
import com.online.bos.domain.Role;
import com.online.bos.domain.User;
import com.online.bos.page.PageBean;
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

	@Override
	public void save(User model, String[] roleIds) {
		model.setPassword(MD5Utils.md5(model.getPassword()));
		userDao.save(model);//持久对象
		
		for(String id : roleIds) {
			Role role = new Role();
			role.setId(id);
			//用户关联角色
			model.getRoles().add(role);
		}
	}

	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

	@Override
	public void pageQuery(PageBean<User> pageBean) {
		userDao.pageQuery(pageBean);
	}

}
