package com.cyyun.manage.controller.news;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cyyun.common.core.bean.PageInfoBean;
import com.cyyun.manage.controller.news.bean.NewsDynamicInfoParam;
import com.cyyun.manage.controller.news.bean.NewsDynamicInfoQueryParam;
import com.cyyun.manage.utils.ManageContext;
import com.cyyun.reportonline.service.NewsDynamicInformationService;
import com.cyyun.reportonline.service.bean.NewsDynamicInformationBean;
import com.cyyun.reportonline.service.bean.query.NewsDynamicInformationQueryBean;
import com.cyyun.reportonline.service.exception.NewsDynamicInformationServiceException;

/** 
 * @author  SuoLiang  
 * @version 2016年6月15日
 */
@Component
public class NewsDynamicInformationSupport {
	
	private static final int PAGE_NO = 1;
	private static final int PAGE_SIZE = 10;
	
	@Autowired
	private NewsDynamicInformationService dynamicInformationService;

	public PageInfoBean<NewsDynamicInformationBean> queryNewsInfoPage(NewsDynamicInfoQueryParam queryParam) throws NewsDynamicInformationServiceException {
		if (queryParam.getPageNo() == null) {
			queryParam.setPageNo(PAGE_NO);
		}
		if (queryParam.getPageSize() == null) {
			queryParam.setPageSize(PAGE_SIZE);
		}
		NewsDynamicInformationQueryBean queryBean = new NewsDynamicInformationQueryBean();
		queryBean.setPageNo(queryParam.getPageNo());
		queryBean.setPageSize(queryParam.getPageSize());
		queryBean.setType(queryParam.getType());
		queryBean.setStatus(queryParam.getStatus());
		PageInfoBean<NewsDynamicInformationBean> pageInfoBean = dynamicInformationService.queryNewsPage(queryBean);
		return pageInfoBean;
	}

	public String getFileNameExt(String fileName){
		if(fileName == null){
			return "";
		}
		return fileName.substring(fileName.lastIndexOf('.')+1).toLowerCase();
	}
	
	/**
	 * 生成文件名
	 * 
	 * @param number	随机数的长度
	 * @return 编码
	 */
	public String getNewFileName(int number) {
		String strcode = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		strcode = sdf.format(new java.util.Date());
		if (number > 0) {
			strcode += getRandomNumByTime(number);
		}
		return strcode;
	}
	
	/**
	 * 以当前时间戳为参数获取指定位数的随机数
	 * 
	 * @param bit 随机数位数
	 * @return
	 */
	public static String getRandomNumByTime(int bit) {
		Random random = new Random();
		int num = 1;
		for (int i = 1; i < bit; i++) {
			num *= 10;
		}
		return (random.nextInt(9 * num) + num + "");
	}
	
	public void addNewsDyInfo(NewsDynamicInfoParam param,HttpServletRequest request) throws NewsDynamicInformationServiceException, ParseException {
		NewsDynamicInformationBean bean = convertParamToBean(param);
		bean.setCreaterId(ManageContext.getLoginUser(request).getId());
		dynamicInformationService.addNewsDynamicInformation(bean);
	}

	private NewsDynamicInformationBean convertParamToBean(NewsDynamicInfoParam param) throws ParseException {
		NewsDynamicInformationBean bean = new NewsDynamicInformationBean();
		bean.setThumbnailPicUrl(param.getThumbnailPicUrl());
		bean.setTitle(param.getTitle());
		bean.setType(param.getType()==null?1:Short.valueOf(param.getType()));
		bean.setStatus(param.getStatus()==null?1:Short.valueOf(param.getStatus()));
		if (StringUtils.isBlank(param.getDynamicTime())) {
			param.setDynamicTime(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
		}
		bean.setDynamicTime(DateUtils.parseDate(param.getDynamicTime(),"yyyy-MM-dd HH:mm:ss"));
		bean.setContent(param.getContent());
		bean.setKeyWords(param.getKeyWords());
		bean.setRemark(param.getRemark());
		return bean;
	}

	public void deleteNewsInfo(Integer id) throws NewsDynamicInformationServiceException {
		dynamicInformationService.deleteNewsDynamicInformation(id);
	}

	public NewsDynamicInformationBean queryNewsInfoById(Integer id) throws NewsDynamicInformationServiceException {
		NewsDynamicInformationBean dynamicInformationBean = dynamicInformationService.queryById(id);
		return dynamicInformationBean;
	}

	public void updateNewsInfo(NewsDynamicInfoParam newsDynamicInfoParam,HttpServletRequest request) throws ParseException, NewsDynamicInformationServiceException {
		NewsDynamicInformationBean bean = convertParamToBean(newsDynamicInfoParam);
		bean.setId(newsDynamicInfoParam.getId());
		bean.setUpdateTime(new Date());
		bean.setUpdaterId(ManageContext.getLoginUser(request).getId());
		dynamicInformationService.updateNewsDynamicInformation(bean);
	}
	
	
}
