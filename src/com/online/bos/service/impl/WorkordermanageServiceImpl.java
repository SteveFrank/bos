package com.online.bos.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.online.bos.dao.IWorkordermanageDao;
import com.online.bos.domain.Workordermanage;
import com.online.bos.page.PageBean;
import com.online.bos.service.IWorkordermanageService;

@Service("workordermanageService")
@Transactional
public class WorkordermanageServiceImpl implements IWorkordermanageService {
	
	@Resource(name="workordermanageDao")
	private IWorkordermanageDao workordermanageDao;
	
	@Override
	public void add(Workordermanage model) {
		model.setUpdatetime(new Date());
		workordermanageDao.save(model);
	}
	
	@Override
	public void pageQuery(PageBean<Workordermanage> pageBean) {
		workordermanageDao.pageQuery(pageBean);;
	}
	
}
