package com.online.bos.service;

import com.online.bos.domain.Workordermanage;
import com.online.bos.page.PageBean;

public interface IWorkordermanageService {
	
	//添加一行信息
	public void add(Workordermanage model);
	
	//分页显示所有添加的工作单信息
	public void pageQuery(PageBean<Workordermanage> pageBean);

}
