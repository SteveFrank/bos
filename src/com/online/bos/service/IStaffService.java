package com.online.bos.service;

import java.util.List;

import com.online.bos.domain.Staff;
import com.online.bos.page.PageBean;

public interface IStaffService {
	/**
	 * 保存取派员
	 * @param staff
	 */
	public void save(Staff staff);
	
	/**
	 * 始终传的是pageBean对象的引用
	 * @param pageBean
	 */
	public void pageQuery(PageBean<Staff> pageBean);
	
	/**
	 * 批量删除操作
	 * @param array_id
	 */
	public void deleteBatch(String ids);
	
	/**
	 * 通过id查找Staff数据
	 * @param id
	 * @return
	 */
	public Staff findById(String id);
	
	/**
	 * 修改Staff数据
	 * @param staff
	 */
	public void update(Staff staff);
	
	/**
	 * 还原被删除的取派员
	 * @param ids
	 */
	public void restoreBatch(String ids);
	
	/**
	 * 查询没有被删除的取派员
	 * @return
	 */
	public List<Staff> findListNotDelete();
	
}
