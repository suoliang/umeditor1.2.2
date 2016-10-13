package com.cyyun.manage.controller.download;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cyyun.common.core.base.BaseController;
import com.cyyun.manage.controller.download.bean.ReportCodeEnum;
import com.cyyun.reportonline.service.bean.ReportDownloadResultBean;
import com.cyyun.reportonline.service.exception.ReportDownloadDetailServiceException;

/** 
 * @author SuoLiang
 * @date   2016年6月16日
 */
@Controller
@RequestMapping("/download")
public class DownloadController extends BaseController {
	
	@Autowired
	private DownloadSupport downloadSupport;
	
	@RequestMapping("trialApply/countDownloadAmount")
	public ModelAndView countDownloadAmount(){
		try {
			List<ReportDownloadResultBean> list = downloadSupport.countDownloadAmount();
			Map<String, String> reportCodeMap = ReportCodeEnum.getReportMap();
			return view("/download/download-index").addObject("downloadList", list).addObject("reportCodeMap",reportCodeMap);
		} catch (ReportDownloadDetailServiceException e) {
			e.printStackTrace();
			log.error("加载下载量信息失败", e);
			return message(MESSAGE_TYPE_ERROR, "加载下载量信息失败");
		}
	}
	
}
