package com.cyyun.manage.controller.trialapply;

import java.text.ParseException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cyyun.common.core.base.BaseController;
import com.cyyun.common.core.bean.MessageBean;
import com.cyyun.common.core.bean.PageInfoBean;
import com.cyyun.manage.controller.trialapply.bean.FreeTrialApplyQueryParam;
import com.cyyun.reportonline.service.bean.FreeTrialApplyBean;
import com.cyyun.reportonline.service.common.SourceEnum;
import com.cyyun.reportonline.service.exception.FreeTrialApplyServiceException;

/** 
 * @description 保存申请的用户信息
 * @author  SuoLiang  
 * @version 2016年6月16日
 */
@Controller
@RequestMapping("/rpt")
public class FreeTrialApplyController extends BaseController {

	@Autowired
	private FreeTrialApplySupport freeTrialApplySupport;
	
	@RequestMapping("trialApply/index")
	public ModelAndView trialApplyIndex(FreeTrialApplyQueryParam queryParam){
		try {
			PageInfoBean<FreeTrialApplyBean> pageInfo = freeTrialApplySupport.queryTrialApplyByPage(queryParam);
			Map<String, String> sourceMap = SourceEnum.getSourceMap();
			return view("/freetrialapply/trialapply-index").addObject("pageInfo", pageInfo).addObject("sourceMap", sourceMap);
		} catch (FreeTrialApplyServiceException | ParseException e) {
			e.printStackTrace();
			log.error("加载试用申请信息失败", e);
			return message(MESSAGE_TYPE_ERROR, "加载试用申请信息失败");
		}
	}
	
	@RequestMapping("trialApply/queryList")
	public ModelAndView queryList(FreeTrialApplyQueryParam queryParam){
		try {
			PageInfoBean<FreeTrialApplyBean> pageInfo = freeTrialApplySupport.queryTrialApplyByPage(queryParam);
			Map<String, String> sourceMap = SourceEnum.getSourceMap();
			return view("/freetrialapply/trialapply-index-paging").addObject("pageInfo", pageInfo).addObject("sourceMap", sourceMap);
		} catch (FreeTrialApplyServiceException | ParseException e) {
			e.printStackTrace();
			log.error("加载试用申请信息失败", e);
			return message(MESSAGE_TYPE_ERROR, "加载试用申请信息失败");
		}
	}
	
	@ResponseBody
	@RequestMapping("trialApply/delete")
	public MessageBean deleteTrialApply(Integer id){
		try {
			freeTrialApplySupport.deleteTrialApply(id);
			return buildMessage(MESSAGE_TYPE_SUCCESS, "删除成功");
		} catch (FreeTrialApplyServiceException e) {
			e.printStackTrace();
			log.error("删除失败", e);
			return buildMessage(MESSAGE_TYPE_ERROR, "删除失败");
		}
	}
	
	@ResponseBody
	@RequestMapping("trialApply/changeStatus")
	public MessageBean changeStatus(Integer id){
		try {
			freeTrialApplySupport.changeTrialApplyStatus(id);
			return buildMessage(MESSAGE_TYPE_SUCCESS, "处理成功");
		} catch (FreeTrialApplyServiceException e) {
			e.printStackTrace();
			log.error("处理失败", e);
			return buildMessage(MESSAGE_TYPE_ERROR, "处理失败");
		}
	}
	
}
