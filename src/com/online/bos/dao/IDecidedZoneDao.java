package com.online.bos.dao;

import com.online.bos.dao.base.IBaseDao;
import com.online.bos.domain.Decidedzone;

public interface IDecidedZoneDao extends IBaseDao<Decidedzone>{
	//通过ID查询定区
	public Decidedzone findDecidedZoneById(String decidedzone_id);

}
