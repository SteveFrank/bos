package com.online.bos.service;

import java.util.List;

import com.online.bos.domain.Function;
import com.online.bos.page.PageBean;

public interface IFunctionService {

	public void pageBean(PageBean<Function> pageBean);

	public List<Function> findAll();

	public void save(Function model);

	public List<Function> findAllMenu();

	public List<Function> findMenuByUserId(String id);
	
}
