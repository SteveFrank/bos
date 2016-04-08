package com.online.bos.service;

import java.util.List;

import com.online.bos.domain.Role;
import com.online.bos.page.PageBean;

public interface IRoleService {

	public void save(Role model, String functionIds);

	public void pageQuery(PageBean<Role> pageBean);

	public List<Role> findAll();
	
}
