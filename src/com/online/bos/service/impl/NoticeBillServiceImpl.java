package com.online.bos.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.online.bos.dao.INoticeBillDao;
import com.online.bos.domain.Noticebill;
import com.online.bos.service.INoticeBillService;

@Service("noticeBillService")
@Transactional
public class NoticeBillServiceImpl implements INoticeBillService {
	@Resource(name="noticeBillDao")
	private INoticeBillDao noticeBillDao;

	@Override
	public void save(Noticebill model) {
		noticeBillDao.save(model);
		//自动进行分单操作 ---->>>> 为当前客户查找一个取派员进行取件操作
		
	}
}
