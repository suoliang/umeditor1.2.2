package com.cyyun.manage.controller.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.cyyun.common.core.base.BaseController;
import com.cyyun.common.core.bean.MessageBean;
import com.cyyun.manage.constants.CookieConstant;
import com.cyyun.manage.utils.CookieUtil;
import com.cyyun.manage.utils.ManageContext;
import com.cyyun.reportonline.service.bean.UsersBean;
import com.cyyun.reportonline.service.exception.UsersServiceException;

/** 
 * @author  SuoLiang  
 * @version 2016年6月15日
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {
	
	@Autowired
	private LoginSupport loginSupport;

	@RequestMapping("login")
	public String toLogin(){
		return "/login";
	}
	
	@ResponseBody
	@RequestMapping("doLogin")
	public MessageBean doLogin(String username,String password,HttpServletResponse response){
		try {
			UsersBean usersBean = loginSupport.login(username,password);
			if (ObjectUtils.notEqual(usersBean, null)) {
				if (usersBean.getStatus() != 0) {
					CookieUtil.setCookie(response, CookieConstant.LOGIN_MANAGE_USER, JSON.toJSONString(usersBean));
					return super.buildMessage(MESSAGE_TYPE_SUCCESS, "用户登录成功");
				}
				return super.buildMessage(MESSAGE_TYPE_ERROR, "账户已停用");
			}
			return super.buildMessage(MESSAGE_TYPE_ERROR, "用户名密码不匹配");
		} catch (UsersServiceException e) {
			e.printStackTrace();
			log.error("登录出错", e);
			return buildMessage(MESSAGE_TYPE_ERROR, "登录错误");
		}
	}
	
	@ResponseBody
	@RequestMapping("userInfo")
	public MessageBean getUserInfo(HttpServletRequest request) {
		UsersBean usersBean = ManageContext.getLoginUser(request);
		return super.buildMessage(MESSAGE_TYPE_SUCCESS, "用户信息", usersBean);
	}
	
}
