package com.cyyun.manage.controller.news.bean;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/** 
 * @description 类型
 * @author  SuoLiang  
 * @version 2016年6月17日
 */
public enum NewsDyInfoTypeEnum {
	
	NEWSDYINFOTYPE1("1","新闻动态"),
	
	NEWSDYINFOTYPE2("2","成功案例");
	
	private String code;
	
	private String name;

	private NewsDyInfoTypeEnum(String code,String name){
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
		for (NewsDyInfoTypeEnum newsType : NewsDyInfoTypeEnum.values()) {
			if (newsType.getCode().equals(code)) {
				return newsType.getName();
			}
		}
		return "";
	}
	
	/***
	 * 得到该枚举类的map集合
	 * @return
	 */
	public static Map<String, String> getNewsTypeMap(){
		Map<String, String> map = new TreeMap<String, String>(
				new Comparator<String>() {
					public int compare(String obj1, String obj2) {
						/** 升序排序 */
						return obj1.compareTo(obj2);
					}
				});
		for (NewsDyInfoTypeEnum newsType : NewsDyInfoTypeEnum.values()) {
			map.put(newsType.getCode(), newsType.getName());
		}
		return map;
	}
	
	public static void main(String[] args) {
		Map<String, String> map = NewsDyInfoTypeEnum.getNewsTypeMap();
		System.out.println(map);
		System.out.println(NewsDyInfoTypeEnum.parseCode("1"));
	}
	
}
