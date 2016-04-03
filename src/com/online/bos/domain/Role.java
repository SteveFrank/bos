package com.online.bos.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * 角色类
 * @author YQ
 *
 */
public class Role implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	private String id;  //角色编号
	private String name;//角色名称
	private String code;//角色关键字
	private String description;//角色描述
	private Set<Function> functions = new HashSet<Function>(); //对应的角色权限
	private Set<User> users = new HashSet<User>(); //角色对应的多个用户
	
	/** default constructor */
	public Role() {
	}

	/** minimal constructor */
	public Role(String id) {
		this.id = id;
	}

	/** full constructor */
	public Role(String id, String name, String code, String description,
			Set<Function> functions, Set<User> users) {
		this.id = id;
		this.name = name;
		this.code = code;
		this.description = description;
		this.functions = functions;
		this.users = users;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
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

	public Set<Function> getFunctions() {
		return this.functions;
	}

	public void setFunctions(Set<Function> functions) {
		this.functions = functions;
	}

	public Set<User> getUsers() {
		return this.users;
	}
	
	public void setUsers(Set<User> users) {
		this.users = users;
	}

}