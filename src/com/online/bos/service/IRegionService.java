package com.online.bos.service;

import java.util.List;

import com.online.bos.domain.Region;
import com.online.bos.page.PageBean;

public interface IRegionService {
	
	/**
	 * 批量保存Region
	 * @param list
	 */
	public void saveBatch(List<Region> list);

	public void pageQuery(PageBean<Region> pageBean);
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<Region> findAll();
	
	/**
	 * 模糊查询
	 * @param q
	 * @return
	 */
	public List<Region> findByQ(String q);

}
