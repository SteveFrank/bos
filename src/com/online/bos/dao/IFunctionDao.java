package com.online.bos.dao;

import java.util.List;

import com.online.bos.dao.base.IBaseDao;
import com.online.bos.domain.Function;

public interface IFunctionDao extends IBaseDao<Function> {

	public List<Function> findFunctionByUserId(String id);

	public List<Function> findAllMenu();

	public List<Function> findMenuByUserId(String id);
	
}
