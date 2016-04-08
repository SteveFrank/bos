package com.online.bos.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 权限的集合
 * @author YQ
 *
 */
public class Function implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String id; //权限编号
	private Function parentFunction;  //对应的是当前权限对应的父权限
	private String name; //权限名称
	private String code; //权限关键字
	private String description; //描述
	private String page; //访问URL
	private String generatemenu; //当前权限是否生成到菜单 1：生成；0：不生成
	private Integer zindex; //排序 
	//对应的是当前权限下级权限
	private Set<Function> childFunctions = new HashSet<Function>(); //对应的多个下级权限
	private Set<Role> roles = new HashSet<Role>(); //对应的多个角色
	
	/** default constructor */
	public Function() {
	}

	/** minimal constructor */
	public Function(String id) {
		this.id = id;
	}
	
	public String getpId() {
		if (parentFunction == null) {
			return "0";
		}
		return parentFunction.getId();
	}
	
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Function getParentFunction() {
		return parentFunction;
	}

	public void setParentFunction(Function parentFunction) {
		this.parentFunction = parentFunction;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPage() {
		return this.page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getGeneratemenu() {
		return this.generatemenu;
	}

	public void setGeneratemenu(String generatemenu) {
		this.generatemenu = generatemenu;
	}

	public Integer getZindex() {
		return this.zindex;
	}

	public void setZindex(Integer zindex) {
		this.zindex = zindex;
	}

	public Set<Function> getChildFunctions() {
		return childFunctions;
	}

	public void setChildFunctions(Set<Function> childFunctions) {
		this.childFunctions = childFunctions;
	}
	
	public Set<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

}