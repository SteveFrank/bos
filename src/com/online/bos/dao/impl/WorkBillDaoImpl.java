package com.online.bos.dao.impl;

import org.springframework.stereotype.Repository;

import com.online.bos.dao.IWorkBillDao;
import com.online.bos.dao.base.impl.BaseDaoImpl;
import com.online.bos.domain.Workbill;

@Repository("workBillDao")
public class WorkBillDaoImpl extends BaseDaoImpl<Workbill> implements IWorkBillDao {

}
