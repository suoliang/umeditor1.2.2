package com.cyyun.manage.controller.news;

import java.io.File;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cyyun.common.core.base.BaseController;
import com.cyyun.common.core.bean.MessageBean;
import com.cyyun.common.core.bean.PageInfoBean;
import com.cyyun.manage.controller.news.bean.NewsDyInfoStatusEnum;
import com.cyyun.manage.controller.news.bean.NewsDyInfoTypeEnum;
import com.cyyun.manage.controller.news.bean.NewsDynamicInfoParam;
import com.cyyun.manage.controller.news.bean.NewsDynamicInfoQueryParam;
import com.cyyun.reportonline.service.bean.NewsDynamicInformationBean;
import com.cyyun.reportonline.service.exception.NewsDynamicInformationServiceException;

/** 
 * @author  SuoLiang  
 * @version 2016年6月15日
 */
@Controller
@RequestMapping("/news")
public class NewsDynamicInformationController extends BaseController {
	
	@Autowired
	private NewsDynamicInformationSupport newsSupport;
	
	@Value("${path.image.upload}")
	private String uploadImagePath;
	
	@Value("${path.image.visit}")
	private String visitImagePath;
	/**缩略图文件路径名*/
	private final String THUMBNAIL = "thumbnail";
	
	@RequestMapping("index")
	public ModelAndView newsDynamicInfoIndex(NewsDynamicInfoQueryParam queryParam){
		try {
			PageInfoBean<NewsDynamicInformationBean> pageInfo = newsSupport.queryNewsInfoPage(queryParam);
			return view("/newsdyinfo/newsdyinfo-index").addObject("pageInfo", pageInfo).addObject("visitImagePath", visitImagePath + THUMBNAIL + "/");
		} catch (NewsDynamicInformationServiceException e) {
			e.printStackTrace();
			log.error("加载新闻列表信息失败", e);
			return message(MESSAGE_TYPE_ERROR, "加载新闻列表信息失败");
		}
	}
	
	@RequestMapping("queryList")
	public ModelAndView queryList(NewsDynamicInfoQueryParam queryParam){
		try {
			PageInfoBean<NewsDynamicInformationBean> pageInfo = newsSupport.queryNewsInfoPage(queryParam);
			return view("/newsdyinfo/newsdyinfo-index-paging").addObject("pageInfo", pageInfo).addObject("visitImagePath", visitImagePath + THUMBNAIL + "/");
		} catch (NewsDynamicInformationServiceException e) {
			e.printStackTrace();
			log.error("加载新闻列表信息失败", e);
			return message(MESSAGE_TYPE_ERROR, "加载新闻列表信息失败");
		}
	}
	
	@RequestMapping("toInputNewsInfo")
	public ModelAndView toAddNewsInfo(Integer id){
		ModelAndView view = view("/newsdyinfo/newsdyinfo-input");
		try {
			if (ObjectUtils.notEqual(id, null)) {
				NewsDynamicInformationBean bean = newsSupport.queryNewsInfoById(id);
				view.addObject("newsInfoBean", bean).addObject("visitImagePath", visitImagePath + THUMBNAIL + "/");
			}
			Map<String, String> newsTypeMap = NewsDyInfoTypeEnum.getNewsTypeMap();
			Map<String, String> newsStatusMap = NewsDyInfoStatusEnum.getNewsStatusMap();
			view.addObject("newsTypeMap", newsTypeMap).addObject("newsStatusMap", newsStatusMap);
			return view;
		} catch (NewsDynamicInformationServiceException e) {
			e.printStackTrace();
			log.error("编辑新闻出错", e);
			return message(MESSAGE_TYPE_ERROR, "编辑新闻失败");
		}
	}
	
	@ResponseBody
	@RequestMapping("addNewsInfo")
	public MessageBean addNewsInfo(@RequestParam(value = "file", required = false) MultipartFile file,NewsDynamicInfoParam newsDynamicInfoParam,HttpServletRequest request){
		String path = uploadImagePath;
		try {
			String fileName = "";
			String fileNameExt = "";
			String newFileName = "";
			if(file!=null && ObjectUtils.notEqual(file.getOriginalFilename(), null)) {
				fileName = file.getOriginalFilename();
				fileNameExt = newsSupport.getFileNameExt(fileName);
				newFileName = newsSupport.getNewFileName(2);
				MessageBean messageBean = null;
				if ((messageBean=checkAndUploadImage(file, path, fileNameExt, newFileName)) != null) {
					return messageBean;
				}
				newsDynamicInfoParam.setThumbnailPicUrl(newFileName + "." + fileNameExt);
			} 
			if (ObjectUtils.notEqual(newsDynamicInfoParam.getId(), null)) {
				try {
					newsSupport.updateNewsInfo(newsDynamicInfoParam,request);
				} catch (NewsDynamicInformationServiceException e) {
					e.printStackTrace();
					log.error("修改新闻信息失败", e);
					return buildMessage(MESSAGE_TYPE_ERROR, "修改失败");
				}
			} else {
				if (StringUtils.isBlank(newsDynamicInfoParam.getThumbnailPicUrl())) {
					return buildMessage(MESSAGE_TYPE_ERROR, "缩略图文件未上传");
				}
				try {
					newsSupport.addNewsDyInfo(newsDynamicInfoParam,request);
				} catch (NewsDynamicInformationServiceException e) {
					e.printStackTrace();
					log.error("添加新闻信息失败", e);
					return buildMessage(MESSAGE_TYPE_ERROR, "添加失败");
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
			log.error("新闻时间解析出错", e);
			return buildMessage(MESSAGE_TYPE_ERROR, "时间解析出错");
		}
		return buildMessage(MESSAGE_TYPE_SUCCESS, "操作成功");
	}

	private MessageBean checkAndUploadImage(MultipartFile file, String path, String fileNameExt, String newFileName) {
		List<String> arrowType = Arrays.asList("image/png","image/gif","image/jpg","image/jpeg","image/pjpeg");
		List<String> arrowExtension = Arrays.asList("png","gif","jpg","jpeg","pjpeg");
		if (! (arrowType.contains(file.getContentType().toLowerCase()) && arrowExtension.contains(fileNameExt)) ) {
			return buildMessage(MESSAGE_TYPE_ERROR, "只支持gif,jpg,jpeg,png,bmp格式图片");
		} 
		if(file.getSize() > 5 * 1024 * 1024) {
			return buildMessage(MESSAGE_TYPE_ERROR, "只支持最大为5M的图片");
		}
		if(!"".equals(newFileName)){
			try {
				/**保存上传图片*/
				String targetPath = path + THUMBNAIL + "/";
				File targetFile = new File(targetPath, newFileName + "." + fileNameExt);
				if(!targetFile.exists()) {
					targetFile.mkdirs();
				}
				file.transferTo(targetFile);
			} catch (Exception e) {
				log.error("===transferTo==="+e.getMessage(), e);
				return buildMessage(MESSAGE_TYPE_ERROR, "缩略图上传失败，系统异常，请联系管理员。");
			}
		}
		return null;
	}

	@ResponseBody
	@RequestMapping("deleteNewsInfo")
	public MessageBean deleteNewsInfo(Integer id){
		try {
			newsSupport.deleteNewsInfo(id);
		} catch (NewsDynamicInformationServiceException e) {
			e.printStackTrace();
			log.error("删除新闻信息失败", e);
			return buildMessage(MESSAGE_TYPE_ERROR, "删除新闻信息出错");
		}
		return buildMessage(MESSAGE_TYPE_SUCCESS, "删除成功");
	}
	
}
