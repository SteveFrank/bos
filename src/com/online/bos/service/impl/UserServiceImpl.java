package com.online.bos.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.online.bos.dao.IRoleDao;
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
	@Resource(name = "roleDao")
	private IRoleDao roleDao;
	@Autowired
	private ProcessEngine processEngine;
	
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
	
	//添加用户，并且将用户信息同步到activiti的act_id_user act_id_membership的表中
	@Override
	public void save(User model, String[] roleIds) {
		model.setPassword(MD5Utils.md5(model.getPassword()));
		userDao.save(model);//持久对象
		
		org.activiti.engine.identity.User actUser = new UserEntity();
		actUser.setId(model.getId());
		//操作act_id_user表
		processEngine.getIdentityService().saveUser(actUser);
		
		for(String id : roleIds) {
			Role role = roleDao.findById(id);
//			Role role = new Role();
//			role.setId(id);
			//用户关联角色
			model.getRoles().add(role);
			
			processEngine.getIdentityService().createMembership(actUser.getId(), role.getName());
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
