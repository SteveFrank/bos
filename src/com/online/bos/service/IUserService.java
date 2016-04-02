package com.online.bos.service;

import com.online.bos.domain.User;

public interface IUserService {

	public User login(User model);

	public void editPassword(String password, String id);
	
}
