package com.online.bos.web.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.online.bos.domain.Workordermanage;
import com.online.bos.service.IWorkordermanageService;
import com.online.bos.utils.BOSContext;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
public class TaskAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ProcessEngine processEngine;
	@Resource
	private IWorkordermanageService workordermanageService;
	/**
	 * 查询组任务
	 */
	public String findGroupTask() {
		//任务查询
		TaskQuery query = processEngine.getTaskService().createTaskQuery();
		//当前账号对应的组任务
		String candidateUser = BOSContext.getLoginUser().getId();
		//根据人员的组任务过滤
		query.taskCandidateUser(candidateUser);
		query.orderByTaskCreateTime().desc();
		
		List<Task> list = query.list();
		ActionContext.getContext().getValueStack().set("list", list);
		
		return "groupTaskList";
	}
	
	//接收任务ID
	private String taskId;
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	
	/**
	 * 查询流程变量
	 * @return
	 * @throws IOException 
	 */
	public String showData() throws IOException {
		Map<String, Object> map = processEngine.getTaskService().getVariables(taskId);
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().print(map);
		return NONE;
	}
	
	/**
	 * 拾取任务
	 * @return
	 */
	public String takeTask() {
		processEngine.getTaskService().claim(taskId,BOSContext.getLoginUser().getId());
		return "toGroupTaskList";
	}
	
	/**
	 * 查询当前登录人的个人任务
	 * @return
	 */
	public String findPersonalTask() {
		TaskQuery query = processEngine.getTaskService().createTaskQuery();
		query.taskAssignee(BOSContext.getLoginUser().getId());
		query.orderByTaskCreateTime().desc();
		
		List<Task> list = query.list();
		ActionContext.getContext().getValueStack().set("list", list);
		return "personalTaskList";
	}
	
	private Integer check; //审核结果
	public Integer getCheck() {
		return check;
	}
	public void setCheck(Integer check) {
		this.check = check;
	}
	
	/**
	 * 办理审核工作单的任务
	 * 办理审核工作单，跳转到相应的check的页面上
	 * @throws IOException 
	 */
	public String checkWorkOrderManage() throws IOException {
		if (check == null) {
			//跳转到审核工作单页面
			Map<String, Object> map = processEngine.getTaskService().getVariables(taskId);
			ServletActionContext.getContext().getValueStack().set("map", map);
			return "checkUI";
		} else {
			//在审核工作单页面跳转过来的，需要真正的办理任务
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("check", check);
			Workordermanage workordermanage = (Workordermanage) processEngine.getTaskService().getVariable(taskId, "业务数据");
			//1、根据任务ID办理任务
			processEngine.getTaskService().complete(taskId,map);
			
			//2、修改工作单重的manageCheck属性为1
			
			workordermanage.setManagerCheck("1");
			
			//3、如果审核不通过，则说明流程结束，如果修改正确则又要重新流程，所以需要将start改为0
			if (check == 0) {
				workordermanage.setStart("0");
			}
			
			//更新数据
			workordermanageService.update(workordermanage);
			
			return "toPersonalTaskList";
		}
	}
	
	/**
	 * 出库任务的办理
	 * @return
	 */
	public String outStore() {
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("仓库管理员", BOSContext.getLoginUser().getUsername());
		processEngine.getTaskService().complete(taskId,variables);
		return "toPersonalTaskList";
	}
	
	/**
	 * 出库配送的办理
	 * @return
	 */
	public String transferGoods() {
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("取派员", BOSContext.getLoginUser().getUsername());
		processEngine.getTaskService().complete(taskId,variables);
		return "toPersonalTaskList";
	}
	
	/**
	 * 签收任务办理
	 * @return
	 */
	public String receive() {
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("取派员", BOSContext.getLoginUser().getUsername());
		processEngine.getTaskService().complete(taskId,variables);
		return "toPersonalTaskList";
	}
	
}
