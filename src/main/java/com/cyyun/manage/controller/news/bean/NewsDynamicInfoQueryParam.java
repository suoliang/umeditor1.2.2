package com.cyyun.manage.controller.news.bean;
/** 
 * @author  SuoLiang  
 * @version 2016年6月16日
 */
public class NewsDynamicInfoQueryParam {
	
	private Short type;
	
	private Short status;
	/**第几页*/
	private Integer pageNo;
	/**页面条数*/
	private Integer pageSize;

	public Short getType() {
		return type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

}
