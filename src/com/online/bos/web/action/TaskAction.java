package com.online.bos.web.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.online.bos.utils.BOSContext;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
public class TaskAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ProcessEngine processEngine;
	
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
		
		System.out.println(list);
		
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

}
