package com.online.bos.service;

import java.util.List;

import com.online.bos.domain.User;
import com.online.bos.page.PageBean;

public interface IUserService {

	public User login(User model);
	
	public void editPassword(String password, String id);
	
	public void save(User model, String[] roleIds);

	public List<User> findAll();

	public void pageQuery(PageBean<User> pageBean);

}
