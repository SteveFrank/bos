package com.online.bos.service;

import java.util.List;

import com.online.bos.domain.Workordermanage;
import com.online.bos.page.PageBean;

public interface IWorkordermanageService {
	
	//添加一行信息
	public void add(Workordermanage model);
	
	//分页显示所有添加的工作单信息
	public void pageQuery(PageBean<Workordermanage> pageBean);
	
	//查询所有没有开始的工作单任务
	public List<Workordermanage> findListNotStart();
	
	//启动物流配送工作流程
	public void start(String id);

}
