package com.online.bos.web.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.online.bos.domain.Function;
import com.online.bos.web.action.base.BaseAction;

/**
 * 权限管理Action
 * @author YQ
 *
 */
@Controller
@Scope("prototype")
public class FunctionAction extends BaseAction<Function> {

	private static final long serialVersionUID = 1L;
	/**
	 * 分页查询的方法
	 * @return
	 */
	public String pageQuery() {
		functionService.pageBean(pageBean);
		writePageBean2Json(pageBean, new String[]{"parentFunction","childern","roles","currentPage","pageSize"});
		return NONE;
	}
	
	/**
	 * 查询所有权限数据返回json
	 * @return
	 */
	public String listajax() {
		List<Function> list = functionService.findAll();
		writeList2Json(list, new String[]{"parentFunction","childern","roles"});
		return NONE;
	}
	
	/**
	 * 添加权限
	 * @return
	 */
	public String add() {
		functionService.save(model);
		return "list";
	}
	
}
