package com.online.bos.page;

import java.util.List;

import org.hibernate.criterion.*;
/**
 * 封装分页信息
 * @author YQ
 */
public class PageBean<T> {
	
	//在本项目中因为使用了easyui所以在pageBean中的数据会比没有使用datagrid的情况要少很多
	private int currentPage; //当前页码
	private int pageSize;	 //页面显示数量
	
	private int total;	//所有需要进行分页的记录数
	private DetachedCriteria detachedCriteria;//需要进行运行的查询条件
	
	private List<T> rows;  //需要进行显示的查询结果集合

	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}

	public DetachedCriteria getDetachedCriteria() {
		return detachedCriteria;
	}
	public void setDetachedCriteria(DetachedCriteria detachedCriteria) {
		this.detachedCriteria = detachedCriteria;
	}

	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
}
