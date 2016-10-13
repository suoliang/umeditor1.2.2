package com.cyyun.manage.controller.acceptemail;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cyyun.common.core.base.BaseController;
import com.cyyun.common.core.bean.MessageBean;
import com.cyyun.common.core.bean.PageInfoBean;
import com.cyyun.manage.controller.acceptemail.bean.AcceptEmailQueryParam;
import com.cyyun.reportonline.service.bean.AcceptEmailBean;
import com.cyyun.reportonline.service.exception.AcceptEmailServiceException;

/** 
 * @author  SuoLiang  
 * @version 2016年6月21日
 */
@Controller
@RequestMapping("/acceptEmail")
public class AcceptEmailController extends BaseController {
	
	@Autowired
	private AcceptEmailSupport support;
	
	@RequestMapping("index")
	public ModelAndView acceptEmailIndex(AcceptEmailQueryParam queryParam) {
		try {
			PageInfoBean<AcceptEmailBean> pageInfo = support.queryEmailPage(queryParam);
			return view("/acceptemail/acceptemail-index").addObject("pageInfo", pageInfo);
		} catch (AcceptEmailServiceException e) {
			e.printStackTrace();
			log.error("加载收件人信息失败", e);
			return message(MESSAGE_TYPE_ERROR, "加载列表信息失败");
		}
	}
	
	@RequestMapping("queryList")
	public ModelAndView queryList(AcceptEmailQueryParam queryParam) {
		try {
			PageInfoBean<AcceptEmailBean> pageInfo = support.queryEmailPage(queryParam);
			return view("/acceptemail/acceptemail-index-paging").addObject("pageInfo", pageInfo);
		} catch (AcceptEmailServiceException e) {
			e.printStackTrace();
			log.error("加载收件人信息失败", e);
			return message(MESSAGE_TYPE_ERROR, "加载列表信息失败");
		}
	}
	
	@RequestMapping("toInputAcceptEmail")
	public ModelAndView toAddNewsInfo(Integer id){
		ModelAndView view = view("/acceptemail/acceptemail-input");
		return view;
	}
	
	@ResponseBody
	@RequestMapping("addAcceptEmail")
	public MessageBean addAcceptEmail(String email,HttpServletRequest request){
		try {
			support.addAcceptEmail(email,request);
		} catch (AcceptEmailServiceException e) {
			e.printStackTrace();
			log.error("操作接收人邮箱失败", e);
			return buildMessage(MESSAGE_TYPE_ERROR, "操作出错");
		}
		return buildMessage(MESSAGE_TYPE_SUCCESS, "操作成功");
	}
	
	@ResponseBody
	@RequestMapping("deleteAcceptEmail")
	public MessageBean deleteAcceptEmail(Integer id){
		try {
			support.deleteAcceptEmail(id);
		} catch (AcceptEmailServiceException e) {
			e.printStackTrace();
			log.error("删除接收邮箱信息失败", e);
			return buildMessage(MESSAGE_TYPE_ERROR, "删除接收邮箱出错");
		}
		return buildMessage(MESSAGE_TYPE_SUCCESS, "操作成功");
	}
	
}
