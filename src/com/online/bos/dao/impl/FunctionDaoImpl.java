package com.online.bos.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.online.bos.dao.IFunctionDao;
import com.online.bos.dao.base.impl.BaseDaoImpl;
import com.online.bos.domain.Function;

@Repository("functionDao")
public class FunctionDaoImpl extends BaseDaoImpl<Function> implements IFunctionDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Function> findFunctionByUserId(String id) {
		String hql = "select distinct function from Function function "
					+ "	left outer join function.roles role left outer join "
					+ " role.users user where user.id = ?";
		return this.getHibernateTemplate().find(hql, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Function> findAllMenu() {
		String hql = "from Function function where function.generatemenu = '1' "
				+ " order by function.zindex";
		return this.getHibernateTemplate().find(hql);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Function> findMenuByUserId(String id) {
		String hql = "select distinct function from Function function "
				+ "	left outer join function.roles role left outer join "
				+ " role.users user where user.id = ? "
				+ " and "
				+ " function.generatemenu = '1' "
				+ " order by function.zindex ";
		return this.getHibernateTemplate().find(hql, id);
	}
	
}
