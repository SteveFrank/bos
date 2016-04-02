package com.online.bos.domain;

/**
 * 分区实体
 * @author YQ
 *
 */
public class Subarea implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String id;		//分区编码
	private Decidedzone decidedzone; //分区对应的定区
	private Region region;  //分区对应的区域
	private String addresskey;//地区关键字
	private String startnum;  //起始编号
	private String endnum;    //结束编号
	private String single;    //单双号
	private String position;  //地址
	
	public String getSubareaId() {
		return id;
	}

	/** default constructor */
	public Subarea() {
	}
	
	/** minimal constructor */
	public Subarea(String id) {
		this.id = id;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Decidedzone getDecidedzone() {
		return this.decidedzone;
	}

	public void setDecidedzone(Decidedzone decidedzone) {
		this.decidedzone = decidedzone;
	}

	public Region getRegion() {
		return this.region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public String getAddresskey() {
		return this.addresskey;
	}

	public void setAddresskey(String addresskey) {
		this.addresskey = addresskey;
	}

	public String getStartnum() {
		return this.startnum;
	}

	public void setStartnum(String startnum) {
		this.startnum = startnum;
	}

	public String getEndnum() {
		return this.endnum;
	}

	public void setEndnum(String endnum) {
		this.endnum = endnum;
	}

	public String getSingle() {
		return this.single;
	}

	public void setSingle(String single) {
		this.single = single;
	}

	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

}