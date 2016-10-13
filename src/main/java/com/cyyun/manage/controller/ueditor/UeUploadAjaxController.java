package com.cyyun.manage.controller.ueditor;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author SuoLiang
 * @version 2016年6月15日
 */
@Controller
@RequestMapping("ajax")
public class UeUploadAjaxController extends AjaxController {

	Logger log = LoggerFactory.getLogger(UeUploadAjaxController.class);

	@Value("${path.image.upload}")
	String imageUploadPath;

	@Value("${path.image.visit}")
	String imageVisitPath;

	@ResponseBody
	@RequestMapping(value = "upload", method = RequestMethod.POST)
	public UploadResponse upload(@RequestParam("upfile") MultipartFile file,
			String editorid) {

		log.info("===upload===begin===editorid===" + editorid);
		UploadResponse response = new UploadResponse();
		response.setState("ERROR");
		try {
			if (file == null
					|| (file.getOriginalFilename() == null || "".equals(file
							.getOriginalFilename()))) {
				log.error("no file found: " + file);
				response.setState("文件图片失败");
				return response;
			}
			// 校验文件大小
			if (!checkFileSize(file.getSize())) {
				log.error("file's size allowed: " + file.getSize());
				response.setState("图片大小超出限制，最大支持5M");
				return response;
			}
			String fileName = file.getOriginalFilename();
			// 校验文件类型
			if (!checkImageType(fileName)) {
				response.setState("只支持gif,jpg,jpeg,png,bmp格式图片");
				return response;
			}

			String path = imageUploadPath;
			String visitPath = imageVisitPath;
			String fileNameExt = "";
			String newFileName = "";
			fileNameExt = getFileExt(fileName);
			newFileName = getNewFileName(2);
			log.info("===path===" + path + "===fileName===" + fileName
					+ "===fileNameExt===" + fileNameExt + "===newFileName==="
					+ newFileName + "." + fileNameExt);

			String targetPath = path + "content_pic" + "/";
			File targetFile = new File(targetPath, newFileName + "." + fileNameExt);
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			file.transferTo(targetFile);

			String url = visitPath + "content_pic" + "/" + newFileName + "." + fileNameExt;
			
			log.info("===url===" + url);

			response.setUrl(url);
			response.setState("SUCCESS");
			response.setTitle(fileNameExt); // 采用空白字段传输文件后缀
			return response;
		} catch (Exception e) {
			log.error("upload file error: ", e);
			response.setState("文件上传失败");
			return response;
		}
	}

	// 图片文件格式
	private static final String[] IMAGE_FILES = { ".gif", ".png", ".jpg", ".jpeg", ".bmp" };

	// 文件大小限制，单位KB
	private static final long MAX_SIZE = 5 * 1024;

	/**
	 * 图片类型判断
	 * 
	 * @param fileName
	 * @return
	 */
	private boolean checkImageType(String fileName) {
		Iterator<String> type = Arrays.asList(IMAGE_FILES).iterator();
		while (type.hasNext()) {
			String ext = type.next();
			if (fileName.toLowerCase().endsWith(ext)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 文件大小判断
	 * 
	 * @param fileName
	 * @return
	 */
	private boolean checkFileSize(long size) {
		if (size > 0L && size < MAX_SIZE * 1024L) {
			return true;
		}
		return false;
	}

	/**
	 * 获取文件扩展名
	 * 
	 * @return string
	 */
	private String getFileExt(String fileName) {
		return fileName.substring(fileName.lastIndexOf(".") + 1);
	}

	/**
	 * 生成文件名
	 * 
	 * @param number
	 *            随机数的长度
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
	 * @param bit
	 *            随机数位数
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

}
