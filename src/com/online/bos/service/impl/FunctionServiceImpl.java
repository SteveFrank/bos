package com.online.bos.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.online.bos.dao.IFunctionDao;
import com.online.bos.domain.Function;
import com.online.bos.page.PageBean;
import com.online.bos.service.IFunctionService;

@Transactional
@Service("functionService")
public class FunctionServiceImpl implements IFunctionService {
	
	@Resource(name="functionDao")
	private IFunctionDao functionDao;

	@Override
	public void pageBean(PageBean<Function> pageBean) {
		functionDao.pageQuery(pageBean);
	}

	@Override
	public List<Function> findAll() {
		return functionDao.findAll();
	}

	@Override
	public void save(Function model) {
		
		Function parentFunction = model.getParentFunction();
		
		if (parentFunction != null) {
			String id = parentFunction.getId();
			//父权限设置为空
			if (StringUtils.isBlank(id)) {
				model.setParentFunction(null);
			}
		}
		
		functionDao.save(model);
	}
	
}
