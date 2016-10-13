package com.cyyun.manage.controller.trialapply;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cyyun.common.core.bean.PageInfoBean;
import com.cyyun.manage.controller.trialapply.bean.FreeTrialApplyQueryParam;
import com.cyyun.reportonline.service.FreeTrialApplyService;
import com.cyyun.reportonline.service.bean.FreeTrialApplyBean;
import com.cyyun.reportonline.service.bean.query.FreeTrialApplyQueryBean;
import com.cyyun.reportonline.service.exception.FreeTrialApplyServiceException;

/** 
 * @author SuoLiang
 * @date   2016年6月16日
 */
@Component
public class FreeTrialApplySupport {
	
	private static final int PAGE_NO = 1;
	private static final int PAGE_SIZE = 10;
	
	@Autowired
	private FreeTrialApplyService freeTrialApplyService;

	public PageInfoBean<FreeTrialApplyBean> queryTrialApplyByPage(FreeTrialApplyQueryParam queryParam) throws FreeTrialApplyServiceException, ParseException {
		if (queryParam.getPageNo() == null) {
			queryParam.setPageNo(PAGE_NO);
		}
		if (queryParam.getPageSize() == null) {
			queryParam.setPageSize(PAGE_SIZE);
		}
		Date endTime = null;
		Date startTime = null;
		if (StringUtils.isNotBlank(queryParam.getStartTime())){
			startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(queryParam.getStartTime());
		}
		if (StringUtils.isNotBlank(queryParam.getEndTime())){
			endTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(queryParam.getEndTime());
			/**由于数据库存放数据精确到毫秒,此处结束时间加1S*/
			Calendar calendar = Calendar.getInstance();    
		    calendar.setTime(endTime);    
		    calendar.add(Calendar.SECOND, 1);    
			endTime = calendar.getTime();
		}
		FreeTrialApplyQueryBean trialApplyQueryBean = new FreeTrialApplyQueryBean();
		trialApplyQueryBean.setPageNo(queryParam.getPageNo());
		trialApplyQueryBean.setPageSize(queryParam.getPageSize());
		trialApplyQueryBean.setStartTime(startTime);
		trialApplyQueryBean.setEndTime(endTime);
		trialApplyQueryBean.setSource(StringUtils.isBlank(queryParam.getSource())?null:Short.valueOf(queryParam.getSource()));
		trialApplyQueryBean.setPhone(queryParam.getTelephone());
		trialApplyQueryBean.setStatus(StringUtils.isBlank(queryParam.getStatus())?null:Short.valueOf(queryParam.getStatus()));
		trialApplyQueryBean.setPromotionSource(StringUtils.isBlank(queryParam.getPromotionSource())?null:Short.valueOf(queryParam.getPromotionSource()));
		PageInfoBean<FreeTrialApplyBean> pageInfoBean = freeTrialApplyService.queryApplyPage(trialApplyQueryBean);
		return pageInfoBean;
	}

	public void deleteTrialApply(Integer id) throws FreeTrialApplyServiceException {
		freeTrialApplyService.deleteFreeTrialApply(id);
	}

	public void changeTrialApplyStatus(Integer id) throws FreeTrialApplyServiceException {
		FreeTrialApplyBean freeTrialApplyBean = new FreeTrialApplyBean();
		freeTrialApplyBean.setUpdateTime(new Date());
		freeTrialApplyBean.setStatus((short) 2);
		freeTrialApplyBean.setId(id);
		freeTrialApplyService.updateFreeTrialApply(freeTrialApplyBean);
	}

}
