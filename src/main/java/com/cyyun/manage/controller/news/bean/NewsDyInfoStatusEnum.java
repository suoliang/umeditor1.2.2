package com.cyyun.manage.controller.news.bean;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/** 
 * @description 状态
 * @author  SuoLiang  
 * @version 2016年6月17日
 */
public enum NewsDyInfoStatusEnum {
	
	NEWSDYINFOSTATUS1("1","启用"),
	
	NEWSDYINFOSTATUS2("2","停用");
	
	private String code;
	
	private String name;

	private NewsDyInfoStatusEnum(String code,String name){
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
		for (NewsDyInfoStatusEnum newsStatus : NewsDyInfoStatusEnum.values()) {
			if (newsStatus.getCode().equals(code)) {
				return newsStatus.getName();
			}
		}
		return "";
	}
	
	/***
	 * 得到该枚举类的map集合
	 * @return
	 */
	public static Map<String, String> getNewsStatusMap(){
		Map<String, String> map = new TreeMap<String, String>(
				new Comparator<String>() {
					public int compare(String obj1, String obj2) {
						/** 升序排序 */
						return obj1.compareTo(obj2);
					}
				});
		for (NewsDyInfoStatusEnum newsStatus : NewsDyInfoStatusEnum.values()) {
			map.put(newsStatus.getCode(), newsStatus.getName());
		}
		return map;
	}
	
	public static void main(String[] args) {
		Map<String, String> map = NewsDyInfoStatusEnum.getNewsStatusMap();
		System.out.println(map);
		System.out.println(NewsDyInfoStatusEnum.parseCode("1"));
	}
	
}
