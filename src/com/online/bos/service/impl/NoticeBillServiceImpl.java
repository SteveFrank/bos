package com.online.bos.service.impl;

import java.sql.Timestamp;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.online.bos.crm.CustomerService;
import com.online.bos.dao.IDecidedZoneDao;
import com.online.bos.dao.INoticeBillDao;
import com.online.bos.dao.IWorkBillDao;
import com.online.bos.domain.Decidedzone;
import com.online.bos.domain.Noticebill;
import com.online.bos.domain.Staff;
import com.online.bos.domain.User;
import com.online.bos.domain.Workbill;
import com.online.bos.service.INoticeBillService;
import com.online.bos.utils.BOSContext;

@Service("noticeBillService")
@Transactional
public class NoticeBillServiceImpl implements INoticeBillService {
	
	@Resource(name="noticeBillDao")
	private INoticeBillDao noticeBillDao;
	@Resource(name="customerService")
	private CustomerService customerService;
	@Resource(name="decidedZoneDao")
	private IDecidedZoneDao decidedZoneDao;
	@Resource(name="workBillDao")
	private IWorkBillDao workBillDao;
	
	@Override
	public void save(Noticebill model) {
		User user = BOSContext.getLoginUser();
		noticeBillDao.save(model);
		//自动进行分单操作 ---->>>> 为当前客户查找一个取派员进行取件操作
		//取件地址
		String pickAddress = model.getPickaddress();
		//根据取件地址获取定区ID
		String decidedzone_id = customerService.findDecidedzoneByPickAddress(pickAddress);
		if(decidedzone_id != null && decidedzone_id != "") {
			//匹配成功，可以自动分单
			model.setOrdertype("自动分单");
			
			Decidedzone decidedzone = decidedZoneDao.findDecidedZoneById(decidedzone_id);
			Staff staff = decidedzone.getStaff();
			model.setStaff(staff);
			//绑定操作员
			model.setUser(user);
			
			//更新model持久状态
			noticeBillDao.update(model);
			
			//为当前取派员产生一个工单
			Workbill workbill = new Workbill();
			//关联业务通知单
			workbill.setNoticebill(model); //关联通知单
			workbill.setStaff(staff);//取派员
			workbill.setType("新");   //工单类型
			workbill.setPickstate("未取件");
			workbill.setBuildtime(new Timestamp(System.currentTimeMillis()));//获取系统时间
			workbill.setAttachbilltimes(0); //追单次数当前为0次
			workbill.setRemark(model.getRemark()); //备注
			
			//保存工单
			workBillDao.save(workbill);
			
			//调用短信接口向取派员发送短信
			System.out.println("====================================================================================");
			System.out.println(staff.getName()+",您有新的工单,取派地址:"+model.getArrivecity()+",请前往取件！");
			System.out.println("====================================================================================");
		} else {
			//匹配失败，转入人工分单
			model.setOrdertype("人工分单");
			
			noticeBillDao.update(model);
		}
	}
}
