package com.online.bos.dao.base.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.online.bos.dao.base.IBaseDao;
import com.online.bos.page.PageBean;

/**
 * 基础Dao包实现类
 * @author YQ
 *
 */
public class BaseDaoImpl<T> extends HibernateDaoSupport implements IBaseDao<T> {
	//实体类型
	Class<T> entityClass;
	
	//注入sessionFactory对象
	//利用Resource注解查找sessionFactory对象
	@Resource
	public void setSF(SessionFactory sessionFactory) {
		/**
		 * 利用Resource的方式完成注解
		 */
		super.setSessionFactory(sessionFactory);
	}
	
	/**
	 * 在构造方法中获取实体类型
	 */
	@SuppressWarnings("unchecked")
	public BaseDaoImpl() {
		// 获取父类类型
		ParameterizedType superClass = (ParameterizedType) this.getClass().getGenericSuperclass();
		// 获取泛型数组
		Type[] typeArguments = superClass.getActualTypeArguments();
		entityClass = (Class<T>) typeArguments[0];
	}
	
	@Override
	public void save(T entity) {
		this.getHibernateTemplate().save(entity);
	}

	@Override
	public void update(T entity) {
		this.getHibernateTemplate().update(entity);
	}

	@Override
	public void delete(T entity) {
		this.getHibernateTemplate().delete(entity);
	}

	@Override
	public T findById(String id) {
		return this.getHibernateTemplate().get(entityClass, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {
		//注意hql中的空格
		String hql = "from " + entityClass.getName();
		return this.getHibernateTemplate().find(hql);
	}
	
	/**
	 * 可以执行任意的HQL语句
	 */
	@Override
	public void executeUpdate(String queryName, Object... args) {
		//Hibernate中的Session
		Session session = this.getSession();
		//命名查询
		Query query = session.getNamedQuery(queryName);
		
//		int length = args.length;
		int i = 0;
		
		for (Object arg : args) {
			//就是为？赋值的方法
			query.setParameter(i++, arg);
		}
		query.executeUpdate();
	}
	
	/**
	 * 通用的分页查询方法
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void pageQuery(PageBean<T> pageBean) {
		//离线对象查询条件
		DetachedCriteria detachedCriteria = pageBean.getDetachedCriteria();	
		//指定有hibernate框架发出select count(id) from XXX ...
		detachedCriteria.setProjection(Projections.rowCount());
		//查询总记录数据
		List<Long> countList = this.getHibernateTemplate().findByCriteria(detachedCriteria);
		int total = countList.get(0).intValue();
		pageBean.setTotal(total);
		//总记录数据包装完毕
		
		////////////////////////////////////////////////////////////////////////////////////
		
		//指定有hibernate框架发出select * from XXX ... 的SQL语句
		detachedCriteria.setProjection(null);
		//改变hibernate的映射行为，从表中查询的数据对应包装成为pojo对象
		detachedCriteria.setResultTransformer(DetachedCriteria.ROOT_ENTITY);
		
		int currentPage = pageBean.getCurrentPage();
		int pageSize    = pageBean.getPageSize();
		
		int firstResult = (currentPage - 1) * pageSize;
		int maxResults  = pageSize;
		
		List<T> rows = this.getHibernateTemplate().findByCriteria(detachedCriteria,firstResult,maxResults);
		pageBean.setRows(rows);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByCondition(DetachedCriteria detachedCriteria) {
		return this.getHibernateTemplate().findByCriteria(detachedCriteria);
	}

	@Override
	public void saveOrUpdate(T entity) {
		this.getHibernateTemplate().saveOrUpdate(entity);
	}
	
}
