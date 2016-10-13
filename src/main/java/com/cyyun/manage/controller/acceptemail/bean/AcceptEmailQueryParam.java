package com.cyyun.manage.controller.acceptemail.bean;
/** 
 * @author  SuoLiang  
 * @version 2016年6月22日
 */
public class AcceptEmailQueryParam {
	
	private String email;
	
	private String type;
	/**第几页*/
	private Integer pageNo;
	/**页面条数*/
	private Integer pageSize;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
