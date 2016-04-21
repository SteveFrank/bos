package com.online.bos.dao.base;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.online.bos.page.PageBean;

/**
 * 项目通用Dao接口设计
 * @author YQ
 * @param <T>
 * 
 */
public interface IBaseDao<T> {
	public void save(T entity);
	public void saveOrUpdate(T entity);
	public void update(T entity);
	public void delete(T entity);
	public T findById(String id);
	public List<T> findAll();
	/**
	 * 执行数据改变
	 * @param queryName
	 * @param args
	 */
	public void executeUpdate(String queryName, Object...args);
	/**
	 * 使用的是引用的思想
	 * @param pageBean
	 */
	public void pageQuery(PageBean<T> pageBean);
	/**
	 * 根据任意条件查询
	 * @param detachedCriteria
	 * @return
	 */
	public List<T> findByCondition(DetachedCriteria detachedCriteria);
	
}
