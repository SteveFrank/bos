package com.online.bos.test;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.junit.Test;

/**
 * 测试POI解析Excel文件
 * @author YQ
 *
 */
public class POITest {
	
	@Test
	public void test1() throws Exception {
		HSSFWorkbook workbook = 
				new HSSFWorkbook(new FileInputStream(new File("d:\\区域导入测试数据.xls")));
		//读取Excel第一个Sheet1页面
		HSSFSheet sheet = workbook.getSheetAt(0);
		for (Row row : sheet) { //循环读取每一行
			String v1 = row.getCell(0).getStringCellValue(); //获取当前行的第一个单元格文字内容
			String v2 = row.getCell(1).getStringCellValue();
			String v3 = row.getCell(2).getStringCellValue();
			String v4 = row.getCell(3).getStringCellValue();
			String v5 = row.getCell(4).getStringCellValue();
			System.out.print(v1+"\t"+v2+"\t"+v3+"\t"+v4+"\t"+v5);
			System.out.println();
		}
	}
	
}
