package com.cyyun.manage.controller.news.bean;

/** 
 * @author  SuoLiang  
 * @version 2016年6月16日
 */
public class NewsDynamicInfoParam {

	/**
	 * ID
	 */
	private Integer id;
	/**
	 * 缩略图URL地址
	 */
	private String thumbnailPicUrl;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 动态时间
	 */
	private String dynamicTime;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 关键词
	 */
	private String keyWords;
	/**
	 * 类型1-新闻;2-成功案例
	 */
	private String type;
	/**
	 * 状态 $ 1-未禁用;2-已禁用
	 */
	private String status;
	/**
	 * 描述
	 */
	private String remark;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getThumbnailPicUrl() {
		return thumbnailPicUrl;
	}

	public void setThumbnailPicUrl(String thumbnailPicUrl) {
		this.thumbnailPicUrl = thumbnailPicUrl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDynamicTime() {
		return dynamicTime;
	}

	public void setDynamicTime(String dynamicTime) {
		this.dynamicTime = dynamicTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(String keyWords) {
		this.keyWords = keyWords;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
