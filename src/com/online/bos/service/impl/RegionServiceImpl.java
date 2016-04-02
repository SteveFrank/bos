package com.online.bos.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.online.bos.dao.IRegionDao;
import com.online.bos.domain.Region;
import com.online.bos.page.PageBean;
import com.online.bos.service.IRegionService;

@Service("regionService")
@Transactional
public class RegionServiceImpl implements IRegionService {
	
	@Resource(name="regionDao")
	private IRegionDao regionDao;
	
	@Override
	public void saveBatch(List<Region> list) {
		for (Region region : list) {
			/**
			 * 使用保存或者更新操作是的不会出现重复导入重复读取出错的情况
			 */
			regionDao.saveOrUpdate(region);
		}
	}

	@Override
	public void pageQuery(PageBean<Region> pageBean) {
		regionDao.pageQuery(pageBean);
	}

	@Override
	public List<Region> findAll() {
		return regionDao.findAll();
	}

	@Override
	public List<Region> findByQ(String q) {
		//离线模糊查询
//		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Region.class);
		//包装查询条件 (以下条件是与关系)
//		detachedCriteria.add(Restrictions.like("province", "%"+q+"%"));
//		detachedCriteria.add(Restrictions.like("city", "%"+q+"%"));
//		detachedCriteria.add(Restrictions.like("district", "%"+q+"%"));
		
		//包装查询条件 (以下条件是或者关系)
//		detachedCriteria.add(Restrictions.or(Restrictions.like("province", "%"+q+"%"), 
//				Restrictions.like("city", "%"+q+"%")));
		
		return regionDao.findByCondition(q);
	}
	
}
