package com.online.bos.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.online.bos.domain.Region;
import com.online.bos.utils.PinYin4jUtils;
import com.online.bos.web.action.base.BaseAction;

/**
 * 区域管理Action
 * @author YQ
 *
 */
@Controller
@Scope("prototype")
public class RegionAction extends BaseAction<Region> {
	private static final long serialVersionUID = 1L;
	public RegionAction() throws InstantiationException, IllegalAccessException {
		super();
	}
	
	//${pageContext.request.contextPath}/region/regionAction_importXls.action
	//接收上传的文件(XLS表格文件)
	private File uploadXLSFile;
	public void setUploadXLSFile(File uploadXLSFile) {
		this.uploadXLSFile = uploadXLSFile;
	}
	
	/**
	 * 批量导入的方法
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public String importXls() throws FileNotFoundException, IOException {
		
		List<Region> list = new ArrayList<Region>();
		
		//使用POI进行文件的解析
		HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(uploadXLSFile));
		//获取Sheet1
		HSSFSheet sheet = workbook.getSheetAt(0);
		//循环读取数据
		
		for (Row row : sheet) {
			int rowNum = row.getRowNum();
			if(rowNum == 0) {
				continue;
			}
			String id = row.getCell(0).getStringCellValue(); //获取当前行的第一个单元格文字内容
			String province = row.getCell(1).getStringCellValue();
			String city = row.getCell(2).getStringCellValue();
			String district = row.getCell(3).getStringCellValue();
			String postcode = row.getCell(4).getStringCellValue();
			//城市编码
			String citycode = PinYin4jUtils.hanziToPinyin(city.substring(0, city.length()-1),"");
			
			String info = province.substring(0, province.length() -1) 
					+ city.substring(0, province.length() -1)
					+ district.substring(0,district.length() - 1);
			System.out.println(info);
			
			String[] strings = PinYin4jUtils.getHeadByString(info);
			//程式简码
			String shortcode = StringUtils.join(strings,"");
			
			Region region   = new Region(id, province, city, district, postcode, shortcode, citycode, null);
			list.add(region);
		}
		
		regionService.saveBatch(list);
		
		return "list";
	}
	
	/**
	 * 分页查询方法
	 * @return
	 * @throws IOException
	 */
	@RequiresPermissions(value="region")
	public String pageQuery() throws IOException {
		regionService.pageQuery(pageBean);
		writePageBean2Json(pageBean, new String[] {"currentPage","pageSize","detachedCriteria","subareas"});
		return NONE;
	}
	
	//模糊查询的参数
	private String q;
	public void setQ(String q) {
		this.q = q;
	}

	/**
	 * 查询所有区域返回JSON
	 * @return
	 */
	public String list() {
		if (q != null) {
			if (StringUtils.isNotBlank(q.trim())) {
				List<Region> _list = regionService.findByQ(q.trim());
				this.writeList2Json(_list, new String[] 
						{"province","city","district","postcode","shortcode","citycode","subareas"});
				return NONE;
			} else {
				//序列化最主要是找get方法
				List<Region> list = regionService.findAll();
				this.writeList2Json(list, new String[] 
						{"province","city","district","postcode","shortcode","citycode","subareas"});
				return NONE;
			}
		} else {
			//序列化最主要是找get方法
			List<Region> list = regionService.findAll();
			this.writeList2Json(list, new String[] 
					{"province","city","district","postcode","shortcode","citycode","subareas"});
			return NONE;
		}
		
	}
	
}
