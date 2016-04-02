package com.online.bos.dao;

import java.util.List;

import com.online.bos.dao.base.IBaseDao;
import com.online.bos.domain.Region;

public interface IRegionDao extends IBaseDao<Region> {
	/**
	 * 下拉表进行条件查询
	 * @param info
	 * @return
	 */
	public List<Region> findByCondition(String info);
	
}
