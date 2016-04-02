package com.online.bos.domain;

import java.sql.Timestamp;

/**
 * 工单
 * @author YQ
 *
 */
public class Workbill implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	// Fields
	private String id;
	private Noticebill Noticebill; //工单关联的业务受理通知单
	private Staff staff; //这是谁的任务，也就是关联的取派员
	private String type; //工单的类型：新，追，改，销
	private String pickstate; //取件状态：未取件，取件中，已取件
	private Timestamp buildtime; //工作单产生时间
	private Integer attachbilltimes; //追单次数
	private String remark; //备注（实际应用十分重要）

	// Constructors
	/** default constructor */
	public Workbill() {
	}

	/** minimal constructor */
	public Workbill(String id, Timestamp buildtime) {
		this.id = id;
		this.buildtime = buildtime;
	}
	
	public Workbill(String id, Noticebill noticebill,
			Staff staff, String type, String pickstate, Timestamp buildtime,
			Integer attachbilltimes, String remark) {
		super();
		this.id = id;
		Noticebill = noticebill;
		this.staff = staff;
		this.type = type;
		this.pickstate = pickstate;
		this.buildtime = buildtime;
		this.attachbilltimes = attachbilltimes;
		this.remark = remark;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Noticebill getNoticebill() {
		return Noticebill;
	}

	public void setNoticebill(Noticebill noticebill) {
		Noticebill = noticebill;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPickstate() {
		return pickstate;
	}

	public void setPickstate(String pickstate) {
		this.pickstate = pickstate;
	}

	public Timestamp getBuildtime() {
		return buildtime;
	}

	public void setBuildtime(Timestamp buildtime) {
		this.buildtime = buildtime;
	}

	public Integer getAttachbilltimes() {
		return attachbilltimes;
	}

	public void setAttachbilltimes(Integer attachbilltimes) {
		this.attachbilltimes = attachbilltimes;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}