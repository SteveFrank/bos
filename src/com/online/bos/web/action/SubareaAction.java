package com.online.bos.web.action;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.online.bos.domain.Region;
import com.online.bos.domain.Subarea;
import com.online.bos.utils.FileUtils;
import com.online.bos.web.action.base.BaseAction;

/**
 * 分区管理Action处理
 * @author YQ
 *
 */
@Controller
@Scope("prototype")
public class SubareaAction extends BaseAction<Subarea> {
	
	private static final long serialVersionUID = 1L;
	public SubareaAction() throws InstantiationException, IllegalAccessException {
		super();
	}
	
	/**
	 * 添加分区
	 * @return
	 */
	public String add() {
		subareaService.save(model);
		return "list";
	}
	
	/**
	 * 分页查询
	 * @return
	 */
	public String pageQuery() {
		DetachedCriteria subareaDC = pageBean.getDetachedCriteria();
		//1、从模型对象中获取提交的数据
		//关键字
		String addresskey = model.getAddresskey();
		//单表查询
		if (StringUtils.isNotBlank(addresskey)) {
			//2、添加查询条件，根据关键字模糊查询
			subareaDC.add(Restrictions.like("addresskey", "%"+addresskey.trim()+"%"));
		}
		//3、结合多表查询
		Region region = model.getRegion();
		//多表查询操作
		if (region != null) {
			String province = region.getProvince();
			String city     = region.getCity();
			String district = region.getDistrict();
			//根据属性名确定，根据subarea确定的
			DetachedCriteria regionDC = subareaDC.createCriteria("region");
			if (StringUtils.isNotBlank(province)) {
				regionDC.add(Restrictions.like("province", "%"+province+"%"));
			}
			if (StringUtils.isNotBlank(city)) {
				regionDC.add(Restrictions.like("city", "%"+city+"%"));
			}
			if (StringUtils.isNotBlank(district)) {
				regionDC.add(Restrictions.like("district", "%"+district+"%"));
			}
		}
		
		subareaService.pageQuery(pageBean);
		this.writePageBean2Json(pageBean, new String[] {"decidedzone","subareas"});
		
		return NONE;
	}
	
	/**
	 * 实现分区数据的导出
	 * 并且提供分区数据的下载
	 * @return
	 * @throws IOException 
	 */
	public String exportXls() throws IOException {
		List<Subarea> list = subareaService.findAll();
		
		//将数据写入到EXCEL文件
		//使用POI将查询的数据写入EXCEL文件中
		HSSFWorkbook workbook = new HSSFWorkbook();
		//在工作表中创建一个sheet
		HSSFSheet sheet = workbook.createSheet("分区数据(由BOS系统导出)");
		//创建标题行
		HSSFRow headRow = sheet.createRow(0);
		//创建单元格
		//标题行
		headRow.createCell(0).setCellValue("分区编号");
		headRow.createCell(1).setCellValue("关键字");
		headRow.createCell(2).setCellValue("地址");
		headRow.createCell(3).setCellValue("省市区");
		
		//分区数据的注入
		for (Subarea subarea : list) {
			//创建数据行
			HSSFRow dataRow = sheet.createRow(sheet.getLastRowNum() + 1);
			String id = subarea.getId();
			String addresskey = subarea.getAddresskey();
			String position   = subarea.getPosition();
			Region region     = subarea.getRegion();
			dataRow.createCell(0).setCellValue(id);
			dataRow.createCell(1).setCellValue(addresskey);
			dataRow.createCell(2).setCellValue(position);
			if (region != null) {
				String province = region.getProvince();
				String city     = region.getCity();
				String district = region.getDistrict();
				String info     = province + city + district;
				dataRow.createCell(3).setCellValue(info);
			}
		}
		Date date=new Date();
		DateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");
		String filename = "分区数据BOS系统导出文件"+dataFormat.format(date)+".xls";
		
		//获取用户浏览器类型
		String agent = ServletActionContext.getRequest().getHeader("user-agent");
		filename = FileUtils.encodeDownloadFilename(filename, agent);
		
		//根据文件名称动态获得文件类型
		//tomcat确定的（conf/wen.xml）
		String contentType = ServletActionContext.getServletContext().getMimeType(filename);
		//通知客户端下载文件类型
		ServletActionContext.getResponse().setContentType(contentType);
		//指定文件名称
		ServletActionContext.getResponse().setHeader("content-disposition", "attachment;filename="+filename);
		//通过输出流向客户端浏览器写EXCEL文件
		ServletOutputStream out = ServletActionContext.getResponse().getOutputStream();
		workbook.write(out);
		
		return NONE;
	}
	
	/**
	 * 查询未分配到定区的分区，返回json 
	 * @return
	 */
	public String listajax() {
		//未关联分区
		List<Subarea> list = subareaService.findListNotAssociation();
		writeList2Json(list, new String[]{"region","decidedzone"});
		return NONE;
	}
	
	private String decidedId;
	public void setDecidedId(String decidedId) {
		this.decidedId = decidedId;
	}
	
	/**
	 * 查询对应定区的分区数据JSON返回
	 * @return
	 */
	public String listajaxByDecidedId() {
		//查询与定区对应的分区信息
		List<Subarea> list = subareaService.findSubareaByDecidedId(decidedId);
		writeList2Json(list, new String[]{"decidedzone","subareas"});
		return NONE;
	}
	
}
