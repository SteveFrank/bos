package com.online.bos.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.online.bos.dao.IFunctionDao;
import com.online.bos.dao.IRoleDao;
import com.online.bos.domain.Function;
import com.online.bos.domain.Role;
import com.online.bos.page.PageBean;
import com.online.bos.service.IRoleService;

@Transactional
@Service("roleService")
public class RoleService implements IRoleService {
	
	@Resource(name="roleDao")
	private IRoleDao roleDao;
	@Resource(name="functionDao")
	private IFunctionDao functionDao;
	
	@Override
	public void save(Role model, String functionIds) {
		//首先持久化角色对象
		roleDao.save(model);
		
		String[] ids = functionIds.split(",");
		//维护的是关系，所以最终操作的是角色权限的关系表
		for (String id : ids) {
			Function function = new Function();
			function.setId(id);
			//自动更新
			model.getFunctions().add(function);
		}
	}

	@Override
	public void pageQuery(PageBean<Role> pageBean) {
		roleDao.pageQuery(pageBean);
	}

	@Override
	public List<Role> findAll() {
		return roleDao.findAll();
	}
}
