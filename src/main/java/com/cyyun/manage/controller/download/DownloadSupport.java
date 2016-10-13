package com.cyyun.manage.controller.download;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.cyyun.reportonline.service.ReportDownloadDetailService;
import com.cyyun.reportonline.service.bean.ReportDownloadResultBean;
import com.cyyun.reportonline.service.exception.ReportDownloadDetailServiceException;

/** 
 * 
 * @author SuoLiang
 * @date   2016年6月16日
 */
@Component
public class DownloadSupport {

	@Autowired
	private ReportDownloadDetailService reportDownloadDetailService; 
	
	/**
	 * List<Integer>统计下载量-IP相同的算作一个下载量
	 * @return
	 * @throws ReportDownloadDetailServiceException
	 */
	public List<ReportDownloadResultBean> countDownloadAmount() throws ReportDownloadDetailServiceException {
		List<ReportDownloadResultBean> list = reportDownloadDetailService.queryDownloadCount();
		return list;
	}
	
}
