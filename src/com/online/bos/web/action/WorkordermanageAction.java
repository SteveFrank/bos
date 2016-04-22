package com.online.bos.web.action;

import java.io.IOException;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.online.bos.domain.Workordermanage;
import com.online.bos.web.action.base.BaseAction;
import com.opensymphony.xwork2.ActionContext;

/**
 * 工作单相关操作
 * @author YQ
 *
 */
@Controller
@Scope("prototype")
public class WorkordermanageAction extends BaseAction<Workordermanage> {
	private static final long serialVersionUID = 1L;
	
	public WorkordermanageAction() throws InstantiationException,
			IllegalAccessException {
		super();
	}
	
	/**
	 * 信息每录入一行则添加一行
	 * @return
	 * @throws IOException
	 */
	public String add() throws IOException {
		String flag = "1";
		try {
			workordermanageService.add(model);
		} catch (Exception e) {
			flag = "0";
		}
		
		ServletActionContext.getResponse().setContentType("text/json;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().print(flag);
		
		return NONE;
	}
	
	/**
	 * 所有添加的工作单信息分页显示
	 * @return
	 */
	public String pageQuery() {
		workordermanageService.pageQuery(pageBean);
		writePageBean2Json(pageBean, new String[]{""});
		return NONE;
	}
	
	/**
	 * 查询未启动的工作单
	 * @return
	 */
	public String list() {
		
		List<Workordermanage> list = workordermanageService.findListNotStart();
		ActionContext.getContext().getValueStack().set("list", list);
		
		return "list";
	}
	
	/**
	 * 启动物流配送流程
	 * @return
	 */
	public String start() {
		String id = model.getId();
		workordermanageService.start(id);
		return "toList";
	}
	
	
}
