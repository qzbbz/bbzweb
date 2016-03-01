package com.weixin.campaign;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JspController {

	@RequestMapping("/getJspPage")
	public String getOpenIdRedirect(HttpSession httpSession,
			HttpServletRequest request) {
		String page = request.getParameter("page");
		return "/views/weixinviews/" + page;
	}
	
}