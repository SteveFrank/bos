package com.online.bos.crm;

import java.util.List;

import cn.itcast.crm.domain.Customer;

/**
 * 调用远程项目
 * 创建联系关联接口
 * @author YQ
 *
 */
public interface CustomerService {
	// 未关联定区客户
		public List<Customer> findnoassociationCustomers();

		// 查询已经关联指定定区的客户
		public List<Customer> findhasassociationCustomers(String decidedZoneId);

		// 将未关联定区客户关联到定区上
		public void assignCustomersToDecidedZone(Integer[] customerIds, String decidedZoneId);
	}
