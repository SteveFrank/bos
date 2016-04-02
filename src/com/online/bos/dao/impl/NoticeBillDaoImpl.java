package com.online.bos.dao.impl;

import org.springframework.stereotype.Repository;

import com.online.bos.dao.INoticeBillDao;
import com.online.bos.dao.base.impl.BaseDaoImpl;
import com.online.bos.domain.Noticebill;

@Repository("noticeBillDao")
public class NoticeBillDaoImpl extends BaseDaoImpl<Noticebill> implements INoticeBillDao {
	
}
