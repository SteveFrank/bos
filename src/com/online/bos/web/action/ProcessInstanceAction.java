package com.online.bos.web.action;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.online.bos.utils.ActivityUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
public class ProcessInstanceAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	@Autowired
	private ProcessEngine processEngine;
	/**
	 * 查询正在运行的实例
	 */
	public String list() {
		//流程实例查询对象，查询表act_ru_execution
		ProcessInstanceQuery query =processEngine.getRuntimeService().createProcessInstanceQuery();
		query.orderByProcessDefinitionKey().desc();
		List<ProcessInstance> list = query.list();
		ActionContext.getContext().getValueStack().set("list", list);
		return "list";
	}
	//接收流程实例ID
	private String id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 根据流程实例ID查询流程变量
	 * @return
	 * @throws IOException 
	 */
	public String findData() throws IOException {
		Map<String, Object> variables = processEngine.getRuntimeService().getVariables(id);
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().print(variables);
		return NONE;
	}
	
	/**
	 * 需要通过查坐标进行节点运行位置的确认
	 * @return
	 */
	public String showPng() {
		Map<String,Object> map = ActivityUtils.findDataByInstanceId(processEngine, id);
		//ValueStack中都是键值对，所以这样封装会很方便
		ActionContext.getContext().getValueStack().push(map);
		return "showPng";
	} 
	
	private String deploymentId;
	private String imageName;
	public String getDeploymentId() {
		return deploymentId;
	}
	public void setDeploymentId(String deploymentId) {
		this.deploymentId = deploymentId;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	/**
	 * 查询png图片的输入流，展示图片
	 * @return
	 */
	public String viewImage() {
		InputStream pngStream = processEngine.getRepositoryService().getResourceAsStream(deploymentId, imageName);
		ActionContext.getContext().getValueStack().set("pngStream", pngStream);
		return "viewImage";
	}
}
