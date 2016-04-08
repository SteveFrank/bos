package com.online.bos.web.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.online.bos.domain.Role;
import com.online.bos.web.action.base.BaseAction;

/**
 * 角色管理Action
 * @author YQ
 *
 */
@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role> {
	
	private static final long serialVersionUID = 1L;
	
	private String functionIds; // 权限ID
	public void setFunctionIds(String functionIds) {
		this.functionIds = functionIds;
	}

	/**
	 * 添加角色的方法
	 * @return
	 */
	
	public String add() {
		roleService.save(model,functionIds);
		return "list";
	}
	
	public String pageQuery() {
		roleService.pageQuery(pageBean);
		writePageBean2Json(pageBean, new String[]{"functions","users","currentPage","pageSize"});
		return NONE;
	}
	
	/**
	 * 查询所有角色，返回所有角色的json数据
	 * @return
	 */
	public String listajax() {
		List<Role> list = roleService.findAll();
		String[] excludes = new String[]{"functions","users"};
		this.writeList2Json(list, excludes);
		return NONE;
	}
}
