package com.online.bos.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.online.bos.dao.IRegionDao;
import com.online.bos.dao.base.impl.BaseDaoImpl;
import com.online.bos.domain.Region;

@Repository("regionDao")
public class RegionDaoImpl extends BaseDaoImpl<Region> implements IRegionDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Region> findByCondition(String info) {
		String hql = "from Region region where region.province like ? "
				+ "or region.city like ? "
				+ "or region.district like ? ";
		return this.getHibernateTemplate().find(hql,"%"+info+"%","%"+info+"%","%"+info+"%");
	}
	
}
