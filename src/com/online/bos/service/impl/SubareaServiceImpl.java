package com.online.bos.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.online.bos.dao.ISubareaDao;
import com.online.bos.domain.Subarea;
import com.online.bos.page.PageBean;
import com.online.bos.service.ISubareaService;

@Service("subareaService")
@Transactional
public class SubareaServiceImpl implements ISubareaService {
	
	//注入dao
	@Resource(name="subareaDao")
	private ISubareaDao subareaDao;

	@Override
	public void save(Subarea model) {
		subareaDao.save(model);
	}

	@Override
	public void pageQuery(PageBean<Subarea> pageBean) {
		subareaDao.pageQuery(pageBean);
	}

	@Override
	public List<Subarea> findAll() {
		return subareaDao.findAll();
	}

	@Override
	public List<Subarea> findListNotAssociation() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Subarea.class);
		//没有关联的过滤条件添加
		detachedCriteria.add(Restrictions.isNull("decidedzone"));
		
		return subareaDao.findByCondition(detachedCriteria);
	}

	@Override
	public List<Subarea> findSubareaByDecidedId(String decidedId) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Subarea.class);
		detachedCriteria.add(Restrictions.eq("decidedzone.id",decidedId));
		return subareaDao.findByCondition(detachedCriteria);
	}
	
}
