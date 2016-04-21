package com.online.bos.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
public class ProcessDefinitionAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	private ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
	/**
	 * 查询最新版本的流程定义列表数据
	 */
	public String list() {
		ProcessDefinitionQuery query = processEngine.getRepositoryService().createProcessDefinitionQuery();
		query.latestVersion();
		List<ProcessDefinition> list = query.list();
		//压栈
		ActionContext.getContext().getValueStack().set("list", list);
		return "list";
	}
	
	//接收上传的文件
	private File deploy;
	public File getDeploy() {
		return deploy;
	}
	public void setDeploy(File deploy) {
		this.deploy = deploy;
	}

	/**
	 * 流程定义上传方法
	 * @return
	 * @throws FileNotFoundException 
	 */
	public String deploy() throws FileNotFoundException {
		
		DeploymentBuilder deploymentBuilder = processEngine.getRepositoryService().createDeployment();
		ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(deploy));
		deploymentBuilder.addZipInputStream(zipInputStream);
		deploymentBuilder.deploy();
		
		return "toList";
	}
	
	private String pdId;
	public String getPdId() {
		return pdId;
	}
	public void setPdId(String pdId) {
		this.pdId = pdId;
	}
	
	/**
	 * 查看流程png图片
	 * @return
	 */
	public String showpng() {
		//获取对应的png图片的相应输入流
		InputStream pngStream = processEngine.getRepositoryService().getProcessDiagram(pdId);
		//使用struts2的文件下载功能展示png图片
		ActionContext.getContext().getValueStack().set("pngStream", pngStream);
		return "showpng";
	}
	
}
