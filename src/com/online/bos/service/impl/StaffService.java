package com.online.bos.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.online.bos.dao.IStaffDao;
import com.online.bos.domain.Staff;
import com.online.bos.page.PageBean;
import com.online.bos.service.IStaffService;

@Service("staffService")
@Transactional
public class StaffService implements IStaffService {
	
	@Resource(name="staffDao")
	private IStaffDao staffDao;
	
	@Override
	public void save(Staff staff) {
		staffDao.save(staff);
	}

	@Override
	public void pageQuery(PageBean<Staff> pageBean) {
		staffDao.pageQuery(pageBean);
	}

	@Override
	public void deleteBatch(String ids) {
		String[] sIds = ids.split(",");
		for(String id : sIds) {
			staffDao.executeUpdate("staff.delete", id);
		}
	}
	
	@Override
	public void restoreBatch(String ids) {
		String[] sIds = ids.split(",");
		for(String id : sIds) {
			staffDao.executeUpdate("staff.restore", id);
		}
	}

	@Override
	public Staff findById(String id) {
		return staffDao.findById(id);
	}

	@Override
	public void update(Staff staff) {
		staffDao.update(staff);
	}

	@Override
	public List<Staff> findListNotDelete() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Staff.class);
		//添加过滤条件 ==== deltag为0
		detachedCriteria.add(Restrictions.eq("deltag", "0"));
		return staffDao.findByCondition(detachedCriteria);
	}

}
