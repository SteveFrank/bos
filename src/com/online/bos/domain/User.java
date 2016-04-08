package com.online.bos.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * User生成实体类
 * @author YQ
 *
 */
public class User  implements Serializable{
	private static final long serialVersionUID = 1L;

	private String id;	  	 //uuid
	private String username;	
	private String password;			
	private String salary;			
	private String birthday;			
	private String gender;			
	private String station;			
	private String telephone;			
	private String remark;	 //简介
	private Set<Role> roles = new HashSet<Role>(); //用户对应的角色
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getUsername() {
		return this.username;
	}
	public void setUsername(String username) {
		this.username = username;
	}	
	
	public String getPassword() {
		return this.password;
	}
	public void setPassword(String password) {
		this.password = password;
	}	
	
	public String getSalary() {
		return this.salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}	
	
	public String getBirthday() {
		return this.birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}	
	
	public String getGender() {
		return this.gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}	
	
	public String getStation() {
		return this.station;
	}
	public void setStation(String station) {
		this.station = station;
	}	
	
	public String getTelephone() {
		return this.telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}	
	
	public String getRemark() {
		return this.remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}	
	
	//角色名称
	public String getRoleNames() {
		String roleNames = "";
		if (roles != null && roles.size() > 0) {
			for (Role role : roles) {
				String name = role.getName();
				roleNames += name + "  ";
			}
		}
		return roleNames;
	}
	
}
