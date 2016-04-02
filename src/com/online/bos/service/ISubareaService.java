package com.online.bos.service;

import java.util.List;

import com.online.bos.domain.Subarea;
import com.online.bos.page.PageBean;

public interface ISubareaService {
	
	public void save(Subarea model);

	public void pageQuery(PageBean<Subarea> pageBean);

	public List<Subarea> findAll();

	public List<Subarea> findListNotAssociation();

	public List<Subarea> findSubareaByDecidedId(String decidedId);
	
}
