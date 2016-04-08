package com.online.bos.dao.impl;

import org.springframework.stereotype.Repository;

import com.online.bos.dao.IRoleDao;
import com.online.bos.dao.base.impl.BaseDaoImpl;
import com.online.bos.domain.Role;

@Repository("roleDao")
public class RoleDao extends BaseDaoImpl<Role> implements IRoleDao {

}
