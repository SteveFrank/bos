package com.online.bos.test;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import com.online.bos.utils.PinYin4jUtils;

public class Pinyin4JTest {
	
	@Test
	public void test1() {
		String city = "北京市";
		String province = "河北省";
		//城市编码 北京市 ====》 beijingshi
		
		String str1 = PinYin4jUtils.hanziToPinyin(city.substring(0, city.length()-1),"");
		System.out.println(str1);
		
		String info = province.substring(0, province.length() -1) + city.substring(0, province.length() -1);
		System.out.println(info);
		
		String[] strings = PinYin4jUtils.getHeadByString(info);
		String join = StringUtils.join(strings,"");
		System.out.println(join);
		for (String s : strings) {
			System.out.print(s);
		}
		
	}
}
