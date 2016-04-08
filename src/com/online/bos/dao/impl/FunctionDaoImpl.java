package com.online.bos.dao.impl;

import org.springframework.stereotype.Repository;

import com.online.bos.dao.IFunctionDao;
import com.online.bos.dao.base.impl.BaseDaoImpl;
import com.online.bos.domain.Function;

@Repository("functionDao")
public class FunctionDaoImpl extends BaseDaoImpl<Function> implements IFunctionDao {
	
}
