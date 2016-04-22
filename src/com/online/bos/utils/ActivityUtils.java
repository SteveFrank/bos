package com.online.bos.utils;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;

public class ActivityUtils {
	
	public static Map<String,Object> findDataByInstanceId(ProcessEngine processEngine ,String instanceId) {
		//根据流程实例ID查询流程实例对象
		ProcessInstanceQuery query = 
				processEngine.getRuntimeService().createProcessInstanceQuery();
		query.processInstanceId(instanceId);
		ProcessInstance processInstance = 
				query.singleResult();

		//根据流程实例对象查询流程定义ID
		String processDefinitionId = 
				processInstance.getProcessDefinitionId();
		//根据流程定义ID查询流程定义对象
		ProcessDefinitionQuery queryDefinitionQuery = 
				processEngine.getRepositoryService().createProcessDefinitionQuery();
		queryDefinitionQuery.processDefinitionId(processDefinitionId);
		ProcessDefinition processDefinition = 
				queryDefinitionQuery.singleResult();

		//查询部署ID;png图片名称;当前流程任务的坐标(存在值栈中)
		String deploymentId = 
				processDefinition.getDeploymentId();
		String imageName 	= 
				processDefinition.getDiagramResourceName(); 
		//获得当前流程实例执行到了哪一个任务
		String activityId 	= 
				processInstance.getActivityId();
		
		//根据流程定义ID查询流程定义对象(包含有坐标信息)
		//这一个对象definitionEntity具有最为完整的信息
		ProcessDefinitionEntity definitionEntity = 
				(ProcessDefinitionEntity) processEngine.getRepositoryService().getProcessDefinition(processDefinitionId);
		ActivityImpl activityImpl = 
				definitionEntity.findActivity(activityId);
		int x = activityImpl.getX();
		int y = activityImpl.getY();
		int width = activityImpl.getWidth();
		int height = activityImpl.getHeight();
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("deploymentId", deploymentId);
		map.put("imageName", imageName);
		map.put("x", x);
		map.put("y", y);
		map.put("width", width);
		map.put("height", height);
		
		return map;
	}
	
}