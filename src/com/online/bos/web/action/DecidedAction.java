package com.online.bos.web.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.crm.domain.Customer;

import com.online.bos.crm.CustomerService;
import com.online.bos.domain.Decidedzone;
import com.online.bos.web.action.base.BaseAction;

@Controller
@Scope("prototype")
public class DecidedAction extends BaseAction<Decidedzone> {
	private static final long serialVersionUID = 1L;
	public DecidedAction() throws InstantiationException,
			IllegalAccessException {
		super();
	}
	//属性注入
	//属性驱动，接收分区id
	public String[] subareaId;
	public void setSubareaId(String[] subareaId) {
		this.subareaId = subareaId;
	}
	
	public String staffId;
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	/**
	 * 添加定区
	 * @return
	 */
	public String add() {
		model.setStaff(staffService.findById(staffId));
		decidedZoneService.add(model,subareaId);
		return "list";
	}
	
	//注入代理对象
	@Resource(name="customerService")
	private CustomerService customerService;
	
	/**
	 * 定区分页显示
	 * @return
	 */
	public String pageQuery() {
		decidedZoneService.pageQuery(pageBean);
		writePageBean2Json(pageBean, new String[]{"subareas","decidedzones"});
		return NONE;
	}
	
	/**
	 * 获取为关联到定区的客户数据（JSON数据）
	 * @return
	 */
	public String findCustomerNotAssociation() {
		//使用代理对象远程获取CRM服务，获取客户数据JSON
		List<Customer> list = customerService.findnoassociationCustomers();
		writeList2Json(list, new String[]{""});
		return NONE;
	}
	
	/**
	 * 获取与定区ID相互关联的用户
	 * @return
	 */
	public String findCustomerAssociation() {
		String id = model.getId();
		List<Customer> list = customerService.findhasassociationCustomers(id);
		writeList2Json(list, new String[]{""});
		return NONE;
	}
	
	private Integer[] customerIds;
	public void setCustomerIds(Integer[] customerIds) {
		this.customerIds = customerIds;
	}

	/**
	 * 实现客户关联操作
	 * @return
	 */
	public String assigncustomerstodecidedzone() {
		customerService.assignCustomersToDecidedZone(customerIds, model.getId());
		return "list";
	}
	
	private String decidedId;
	public void setDecidedId(String decidedId) {
		this.decidedId = decidedId;
	}

	/**
	 * 查询关联的客户返回JSON数据
	 * @return
	 */
	public String listajaxCustomer() {
		List<Customer> list = customerService.findhasassociationCustomers(decidedId);
		writeList2Json(list, new String[]{"telephone","address","decidedzone_id"});
		return NONE;
	}
}
