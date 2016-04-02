package com.online.bos.service;

import com.online.bos.domain.Noticebill;

public interface INoticeBillService {
	/**
	 * 新建业务受理单并且进行保存以及自动分单到业务员
	 * @param model
	 */
	public void save(Noticebill model);
	
}
