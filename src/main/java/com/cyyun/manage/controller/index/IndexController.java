package com.cyyun.manage.controller.index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cyyun.common.core.base.BaseController;

/** 
 * @author  SuoLiang  
 * @version 2016年6月21日
 */
@Controller
@RequestMapping("/")
public class IndexController extends BaseController {
	
	@RequestMapping("index")
	public String index() {
		String url = "/rpt/trialApply/index.htm";
		return "redirect:" + url;
	}
	
}
