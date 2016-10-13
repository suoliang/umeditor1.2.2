package com.cyyun.manage.controller.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cyyun.manage.utils.MD5Util;
import com.cyyun.reportonline.service.UsersService;
import com.cyyun.reportonline.service.bean.UsersBean;
import com.cyyun.reportonline.service.bean.query.UsersLoginQueryBean;
import com.cyyun.reportonline.service.exception.UsersServiceException;

/** 
 * @author  SuoLiang  
 * @version 2016年6月15日
 */
@Component
public class LoginSupport {
	
	@Autowired
	private UsersService usersService;

	public UsersBean login(String username, String password) throws UsersServiceException {
		UsersLoginQueryBean loginQueryBean = new UsersLoginQueryBean();
		loginQueryBean.setUsername(username);
		password = MD5Util.MD5(password);
		loginQueryBean.setPassword(password);
		return usersService.queryByNameAndPwd(loginQueryBean);
	}
	
	
}
