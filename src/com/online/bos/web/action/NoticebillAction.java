package com.online.bos.web.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.itcast.crm.domain.Customer;

import com.online.bos.crm.CustomerService;
import com.online.bos.domain.Noticebill;
import com.online.bos.web.action.base.BaseAction;

/**
 * 业务通知单管理Action
 * @author YQ
 *
 */
@Controller
@Scope("prototype")
public class NoticebillAction extends BaseAction<Noticebill> {
	private static final long serialVersionUID = 1L;
	
	public NoticebillAction() throws InstantiationException,
			IllegalAccessException {
		super();
	}
	
	@Resource(name="customerService")
	private CustomerService customerService;
	
	public String findCustomerByPhone() {
		Customer customer =	customerService.findCustomerByPhone(model.getTelephone());
		writeObject2Json(customer, new String[] {""});
		return NONE;
	}
	
	public String add() {
		Customer customer =	customerService.findCustomerByPhone(model.getTelephone());
		if(customer != null) {
			noticeBillService.save(model);
		} else {
			// 如果没有该客户则首先需要添加该客户
			Customer _customer = new Customer();
			_customer.setId(Integer.parseInt(model.getCustomerId()));
			_customer.setTelephone(model.getTelephone());
			_customer.setName(model.getCustomerName());
			_customer.setAddress(model.getPickaddress());
			customerService.saveCustomer(_customer);
			noticeBillService.save(model);
		}
		
		return "toAddUI";
	}
	
}
