package com.cyyun.manage.controller.acceptemail;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cyyun.common.core.bean.PageInfoBean;
import com.cyyun.manage.controller.acceptemail.bean.AcceptEmailQueryParam;
import com.cyyun.manage.utils.ManageContext;
import com.cyyun.reportonline.service.AcceptEmailService;
import com.cyyun.reportonline.service.bean.AcceptEmailBean;
import com.cyyun.reportonline.service.bean.query.AcceptEmailQueryBean;
import com.cyyun.reportonline.service.exception.AcceptEmailServiceException;

/** 
 * @author  SuoLiang  
 * @version 2016年6月21日
 */
@Component
public class AcceptEmailSupport {

	private static final int PAGE_NO = 1;
	private static final int PAGE_SIZE = 10;
	
	@Autowired
	private AcceptEmailService acceptEmailService;
	
	public PageInfoBean<AcceptEmailBean> queryEmailPage(AcceptEmailQueryParam queryParam) throws AcceptEmailServiceException {
		if (queryParam.getPageNo() == null) {
			queryParam.setPageNo(PAGE_NO);
		}
		if (queryParam.getPageSize() == null) {
			queryParam.setPageSize(PAGE_SIZE);
		}
		AcceptEmailQueryBean acceptEmailQueryBean = new AcceptEmailQueryBean();
		acceptEmailQueryBean.setEmail(queryParam.getEmail());
		acceptEmailQueryBean.setType(StringUtils.isBlank(queryParam.getType())?null:Short.valueOf(queryParam.getType()));
		PageInfoBean<AcceptEmailBean> pageInfoBean = acceptEmailService.queryEmailPage(acceptEmailQueryBean);
		return pageInfoBean;
	}

	public void deleteAcceptEmail(Integer id) throws AcceptEmailServiceException {
		acceptEmailService.delete(id);
	}

	public void addAcceptEmail(String email,HttpServletRequest request) throws AcceptEmailServiceException {
		AcceptEmailBean acceptEmailBean = new AcceptEmailBean();
		acceptEmailBean.setEmail(email);
		acceptEmailBean.setCreaterId(ManageContext.getLoginUser(request).getId());
		acceptEmailService.addAcceptEmail(acceptEmailBean);
	}
	
	
}
