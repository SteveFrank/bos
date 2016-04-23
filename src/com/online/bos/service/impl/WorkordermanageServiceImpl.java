package com.online.bos.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.ProcessEngine;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
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

	@Override
	public List<Workordermanage> findListNotStart() {
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Workordermanage.class);
		//添加条件：start == 0
		detachedCriteria.add(Restrictions.eq("start", "0"));
		List<Workordermanage> list = workordermanageDao.findByCondition(detachedCriteria);
		
		return list;
	}
	
	@Autowired
	private ProcessEngine processEngine;
	
	//启动物流配送流程
	@Override
	public void start(String id) {
		Workordermanage workordermanage = workordermanageDao.findById(id);
		workordermanage.setStart("1");
		String processDefinitionKey = "transfer"; //物流配送流程key
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("业务数据", workordermanage);
		processEngine.getRuntimeService().
			startProcessInstanceByKey(processDefinitionKey,variables);
	}

	@Override
	public void update(Workordermanage workordermanage) {
		workordermanageDao.update(workordermanage);
	}
	
}
