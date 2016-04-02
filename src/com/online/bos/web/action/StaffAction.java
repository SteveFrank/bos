package com.online.bos.web.action;

import java.io.IOException;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.online.bos.domain.Staff;
import com.online.bos.web.action.base.BaseAction;

/**
 * 取派员Action实现类
 * @author YQ
 *
 */
@Controller
@Scope("prototype")
public class StaffAction extends BaseAction<Staff> {
	
	private static final long serialVersionUID = 1L;
	
	public StaffAction() throws InstantiationException, IllegalAccessException {
		super();
	}
	
	public String add() {
		staffService.save(this.model);
		return "list";
	}

	
	/**
	 * 分页查询的方法不需要跳转页面，需要的是JSON数据
	 * @return
	 * @throws IOException 
	 */
	public String pageQuery() throws IOException {
		staffService.pageQuery(pageBean);
		writePageBean2Json(pageBean, new String[] {"currentPage","pageSize","detachedCriteria","decidedzones"});
		return NONE;
	}
	
	/**
	 * 取派员的id集合
	 */
	private String ids;
	public void setIds(String ids) {
		this.ids = ids;
	}
	
	/**
	 * 删除
	 * @return
	 */
	public String delete() {
		staffService.deleteBatch(ids);
		return "list";
	}
	
	/**
	 * 还原已经被删除的取派员
	 * @return
	 */
	public String restore() {
		staffService.restoreBatch(ids);
		return "list";
	}
	
	/**
	 * 修改取派员数据
	 * @return
	 */
	public String edit() {
		Staff staff = staffService.findById(model.getId());
		//使用表单提交数据覆盖原始数据
		staff.setName(model.getName());
		staff.setStandard(model.getStandard());
		staff.setStation(model.getStation());
		staff.setTelephone(model.getTelephone());
		staff.setHaspda(model.getHaspda());
		
		staffService.update(staff);
		return "list";
	}
	
	/**
	 * 查询没有作废的取派员
	 * @return
	 */
	public String listajax() {
		List<Staff> list = staffService.findListNotDelete();
		writeList2Json(list, new String[] 
				{"telephone","haspda","deltag","station","standard","decidedzones"});
		return NONE;
	}
	
}
