package com.online.bos.service;

import com.online.bos.domain.Decidedzone;
import com.online.bos.page.PageBean;

public interface IDecidedZoneService {

	public void add(Decidedzone model, String[] subareaId);

	public void pageQuery(PageBean<Decidedzone> pageBean);
	
}
