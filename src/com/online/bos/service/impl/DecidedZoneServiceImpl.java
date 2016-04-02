package com.online.bos.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.online.bos.dao.IDecidedZoneDao;
import com.online.bos.dao.ISubareaDao;
import com.online.bos.domain.Decidedzone;
import com.online.bos.domain.Subarea;
import com.online.bos.page.PageBean;
import com.online.bos.service.IDecidedZoneService;

@Service("decidedZoneService")
@Transactional
public class DecidedZoneServiceImpl implements IDecidedZoneService {
	
	@Resource(name="decidedZoneDao")
	private IDecidedZoneDao decidedZoneDao;
	@Resource(name="subareaDao")
	private ISubareaDao subareaDao;

	@Override
	public void add(Decidedzone model, String[] subareaId) {
		for (String id : subareaId) {
			//当前查出也是持久状态
			Subarea subarea = subareaDao.findById(id);
			//所以使用分区关联定区
			//一对多的关系Subarea是一,decidedZone是多
			subarea.setDecidedzone(model);
		}
		decidedZoneDao.save(model);//持久化定区对象
	}

	@Override
	public void pageQuery(PageBean<Decidedzone> pageBean) {
		decidedZoneDao.pageQuery(pageBean);
	}
	
}
