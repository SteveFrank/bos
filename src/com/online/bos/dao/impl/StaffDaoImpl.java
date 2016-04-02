package com.online.bos.dao.impl;

import org.springframework.stereotype.Repository;

import com.online.bos.dao.IStaffDao;
import com.online.bos.dao.base.impl.BaseDaoImpl;
import com.online.bos.domain.Staff;

@Repository("staffDao")
public class StaffDaoImpl extends BaseDaoImpl<Staff> implements IStaffDao {
	
}
