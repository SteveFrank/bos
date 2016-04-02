package com.online.bos.web.action.base;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;

import com.online.bos.page.PageBean;
import com.online.bos.service.IDecidedZoneService;
import com.online.bos.service.INoticeBillService;
import com.online.bos.service.IRegionService;
import com.online.bos.service.IStaffService;
import com.online.bos.service.ISubareaService;
import com.online.bos.service.IUserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 重要点1：整合了分页的框架，使得代码十分的简介
 * @author YQ
 *
 * @param <T>
 */
public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {
	
	private static final long serialVersionUID = 1L;

	private Class<T> entityClass;
	
	protected T model;       //模型对象
	
	@Resource(name="userService")
	protected IUserService userService;
	@Resource(name="staffService")
	protected IStaffService staffService;
	@Resource(name="regionService")
	protected IRegionService regionService;
	@Resource(name="subareaService")
	protected ISubareaService subareaService;
	@Resource(name="decidedZoneService")
	protected IDecidedZoneService decidedZoneService;
	@Resource(name="noticeBillService")
	protected INoticeBillService noticeBillService;
	
	
	protected PageBean<T> pageBean = new PageBean<T>();
	//离线查询对象，用于包装查询条件
	protected DetachedCriteria detachedCriteria = null;
	//直接使用了属性驱动进行了直接的注入操作
	public void setPage(int page) {
		pageBean.setCurrentPage(page); //当前页码
	}
	public void setRows(int rows) {
		pageBean.setPageSize(rows);    //每页显示记录数
	}
	
	/**
	 * 利用反射机制为model赋值
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	@SuppressWarnings("unchecked")
	public BaseAction() throws InstantiationException, IllegalAccessException {
		ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();
		Type[] types = parameterizedType.getActualTypeArguments();
		entityClass = (Class<T>) types[0];
		//获得实体类型后创建离线条件查询对象
		detachedCriteria = DetachedCriteria.forClass(entityClass);
		detachedCriteria.addOrder(Order.desc("id"));
		pageBean.setDetachedCriteria(detachedCriteria);
		//entityClass 实体类型
		model = entityClass.newInstance();
	}
	
	/**
	 * 将PageBean序列化为json进行返回
	 */
	public void writePageBean2Json(PageBean<T> pageBean, String[] excludes) {
		//使用jsonlib的包进行转化
		//重点就是JSON的使用
		JsonConfig jsonConfig = new JsonConfig();
		//一定需要注意这一块儿，否则必然会造成死循环
		jsonConfig.setExcludes(excludes);
		String json = JSONObject.fromObject(pageBean,jsonConfig).toString();
		ServletActionContext.getResponse().setContentType("text/json;charset=UTF-8");
		try {
			ServletActionContext.getResponse().getWriter().print(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 将List数据序列化到JSON
	 * @param list
	 * @param excludes
	 */
	public void writeList2Json(List<?> list, String[] excludes) { 
		//使用jsonlib的包进行转化
		//重点就是JSON的使用
		JsonConfig jsonConfig = new JsonConfig();
		//一定需要注意这一块儿，否则必然会造成死循环
		jsonConfig.setExcludes(excludes);
		String json = JSONArray.fromObject(list,jsonConfig).toString();
		ServletActionContext.getResponse().setContentType("text/json;charset=UTF-8");
		try {
			ServletActionContext.getResponse().getWriter().print(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 将Object数据序列化到JSON
	 * @param list
	 * @param excludes
	 */
	public void writeObject2Json(Object object, String[] excludes) { 
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(excludes);
		String json = JSONObject.fromObject(object, jsonConfig).toString();
		
		ServletActionContext.getResponse().setContentType("text/json;charset=UTF-8");
		
		try {
			ServletActionContext.getResponse().getWriter().print(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public T getModel() {
		return model;
	}

}
