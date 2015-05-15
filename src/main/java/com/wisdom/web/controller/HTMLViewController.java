package com.wisdom.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wisdom.web.utils.SessionConstant;

@Controller
public class HTMLViewController {

	//门户网站
	@RequestMapping("/frontpage/{page}")
	public String getFrontPages(@PathVariable String page) {
		return "redirect:/views/webviews/" + page + ".html";
	}
	
	@RequestMapping("/")
	public String getRootHtml() {
		return "redirect:/views/webviews/index.html";
	}
	
	//后台网站	
	@RequestMapping("/logout")
	public String logout(HttpSession httpSession) {
		httpSession.removeAttribute(SessionConstant.SESSION_USER_ID);
		httpSession.removeAttribute(SessionConstant.SESSION_COMPANY_NOT_FINISH_REGISTER);
		httpSession.removeAttribute(SessionConstant.SESSION_USER_TYPE);
		return "redirect:/views/webviews/index.html";
	}
}