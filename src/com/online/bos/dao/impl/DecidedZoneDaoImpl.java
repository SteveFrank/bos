package com.online.bos.dao.impl;

import org.springframework.stereotype.Repository;

import com.online.bos.dao.IDecidedZoneDao;
import com.online.bos.dao.base.impl.BaseDaoImpl;
import com.online.bos.domain.Decidedzone;

@Repository("decidedZoneDao")
public class DecidedZoneDaoImpl extends BaseDaoImpl<Decidedzone> implements
		IDecidedZoneDao {

	@Override
	public Decidedzone findDecidedZoneById(String decidedzone_id) {
		return this.getHibernateTemplate().get(Decidedzone.class, decidedzone_id);
	}
	
}
