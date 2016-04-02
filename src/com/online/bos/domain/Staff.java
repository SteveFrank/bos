package com.online.bos.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * 取派员实体
 * @author YQ
 *
 */
public class Staff implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String id;  //编号
	private String name;//姓名
	private String telephone;   //电话
	private String haspda = "0";//是否有PDA:1有，0无
	private String deltag = "0";//删除标志：1已经删除，0未删除
	private String station;     //单位
	private String standard;	//收派标准
	//一个取派员对应多个定区
	private Set<Decidedzone> decidedzones = new HashSet<Decidedzone>();
	
	public String getStaffId(){
		return id;
	}
	
	/** default constructor */
	public Staff() {
	}

	/** minimal constructor */
	public Staff(String id) {
		this.id = id;
	}

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

	public String getTelephone() {
		return this.telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getHaspda() {
		return this.haspda;
	}
	public void setHaspda(String haspda) {
		this.haspda = haspda;
	}

	public String getDeltag() {
		return this.deltag;
	}
	public void setDeltag(String deltag) {
		this.deltag = deltag;
	}

	public String getStation() {
		return this.station;
	}
	public void setStation(String station) {
		this.station = station;
	}

	public String getStandard() {
		return this.standard;
	}
	public void setStandard(String standard) {
		this.standard = standard;
	}

	public Set<Decidedzone> getDecidedzones() {
		return this.decidedzones;
	}
	public void setDecidedzones(Set<Decidedzone> decidedzones) {
		this.decidedzones = decidedzones;
	}

}