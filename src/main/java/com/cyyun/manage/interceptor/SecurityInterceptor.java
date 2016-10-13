package com.cyyun.manage.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import com.cyyun.manage.constants.CookieConstant;
import com.cyyun.manage.utils.CookieUtil;

/**
 * @description 后台管理系统的登录拦截
 * @author SuoLiang
 * @date   2016年6月21日
 */
public class SecurityInterceptor extends HandlerInterceptorAdapter{
	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler) throws Exception {
		String cookieValue = CookieUtil.getCookieValue(request, CookieConstant.LOGIN_MANAGE_USER);
		if (StringUtils.isBlank(cookieValue)) {
			response.sendRedirect(request.getContextPath() +"/login/login.htm");
			return false;
		}
		return super.preHandle(request, response,handler);
	}
}
