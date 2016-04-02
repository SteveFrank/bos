package com.online.bos.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * 定区实体
 * @author YQ
 *
 */
public class Decidedzone implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private String id;  //编号
	private Staff staff;//定区关联的取派员
	private String name;//定区名称
	//当前定区下对应的多个分区
	private Set<Subarea> subareas = new HashSet<Subarea>();
	
	/** default constructor */
	public Decidedzone() {
	}

	/** minimal constructor */
	public Decidedzone(String id) {
		this.id = id;
	}
	
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Staff getStaff() {
		return this.staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Subarea> getSubareas() {
		return this.subareas;
	}

	public void setSubareas(Set<Subarea> subareas) {
		this.subareas = subareas;
	}

}