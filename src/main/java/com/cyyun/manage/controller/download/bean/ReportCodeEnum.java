package com.cyyun.manage.controller.download.bean;

import java.util.HashMap;
import java.util.Map;

/** 
 * @author SuoLiang
 * @date   2016年6月16日
 */
public enum ReportCodeEnum {
	
	REPORT1("2016年度1~4月度双微“网红”排行20160422.pdf","1"),
	
	REPORT2("“哈尔滨天价鱼宰客”传播分析报告.doc","2"),
	
	REPORT3("【数据看两会】聚焦两会中国绘就未来五年路线图.docx","3");
	
	private String code;
	
	private String name;
	
	private ReportCodeEnum(String code,String name){
		this.code = code;
		this.name = name;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	/** 根据枚举code得到对应的name */
	public static String parseCode(String code) {
		for (ReportCodeEnum c : ReportCodeEnum.values()) {
			if (c.getCode().equals(code)) {
				return c.getName();
			}
		}
		return "";
	}
	
	
	/***
	 * 得到该枚举类的map集合
	 * @return
	 */
	public static Map<String, String> getReportMap(){
		Map<String, String> map=new HashMap<String, String>();
		for(ReportCodeEnum o:ReportCodeEnum.values()){
			map.put(o.getCode(), o.getName());
		}
		return map;
	}
	
	public static void main(String[] args) {
		Map<String, String> categoryMap = ReportCodeEnum.getReportMap();
		System.err.println(categoryMap);
		System.out.println(categoryMap.get(ReportCodeEnum.REPORT1.getCode()));
	}
	
}
