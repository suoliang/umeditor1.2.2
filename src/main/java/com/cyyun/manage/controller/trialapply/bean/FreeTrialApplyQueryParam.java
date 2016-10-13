package com.cyyun.manage.controller.trialapply.bean;

/** 
 * @description 用户申请试用信息查询参数
 * @author  SuoLiang  
 * @version 2016年6月2日
 */
public class FreeTrialApplyQueryParam {
	/**来源*/
	private String source;
	/**开始时间*/
	private String startTime;
	/**结束时间*/
	private String endTime;
	
	private String telephone;
	/**状态*/
	private String status;
	/**推广来源，推广渠道--从哪个渠道进来的*/
	private String promotionSource;
	/**第几页*/
	private Integer pageNo;
	/**页面条数*/
	private Integer pageSize;

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPromotionSource() {
		return promotionSource;
	}

	public void setPromotionSource(String promotionSource) {
		this.promotionSource = promotionSource;
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
