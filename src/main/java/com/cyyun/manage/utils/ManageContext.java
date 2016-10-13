package com.cyyun.manage.utils;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import com.alibaba.fastjson.JSON;
import com.cyyun.manage.constants.CookieConstant;
import com.cyyun.reportonline.service.bean.UsersBean;

/** 
 * @description 获取登录用户对象
 * @author  SuoLiang  
 * @version 2016年6月21日
 */
public class ManageContext {
	
	public static UsersBean getLoginUser(HttpServletRequest request) {
		String cookieValue = CookieUtil.getCookieValue(request, CookieConstant.LOGIN_MANAGE_USER);
		if (StringUtils.isBlank(cookieValue)) {
			return null;
		}
		return JSON.parseObject(cookieValue, UsersBean.class);
	}
	
}
