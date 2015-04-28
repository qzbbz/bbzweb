package com.wisdom.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wisdom.web.utils.SessionConstant;

@Controller
public class HTMLViewController {

	@RequestMapping("/home")
	public String getHomeHtml() {
		return "redirect:/views/webviews/home.html";
	}
	
	@RequestMapping("/feature")
	public String getFeatureHtml() {
		return "redirect:/views/webviews/feature.html";
	}
	
	@RequestMapping("/register")
	public String getRegisterHtml() {
		return "redirect:/views/webviews/register.html";
	}
	
	@RequestMapping("/community")
	public String getCommunityHtml() {
		return "redirect:/views/webviews/community.html";
	}
	
	@RequestMapping("/team")
	public String getTeamHtml() {
		return "redirect:/views/webviews/team.html";
	}
	
	@RequestMapping("/contact")
	public String getContactHtml() {
		return "redirect:/views/webviews/contact.html";
	}
	
	@RequestMapping("/company/selectAccounter")
	public String getSelectAccounterHtml() {
		return "redirect:/views/webviews/select_accounter.html";
	}
	
	@RequestMapping("/companyUser/staffInfo")
	public String getCompanyStaffHtml() {
		return "redirect:/views/webviews/staff_info.html";
	}
	
	@RequestMapping("/accounter/accounterInfo")
	public String getAccounterInfoHtml() {
		return "redirect:/views/webviews/accounter_info.html";
	}
	
	@RequestMapping("/company/companyInfo")
	public String getCompanyInfoHtml() {
		return "redirect:/views/webviews/company_info.html";
	}
	
	@RequestMapping("/company/companyDetailRegister")
	public String getCompanyDetailRegisterHtml() {
		return "redirect:/views/webviews/company_detail_register.html";
	}
	
	@RequestMapping("/")
	public String getRootHtml() {
		return "redirect:/views/webviews/home.html";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession httpSession) {
		httpSession.removeAttribute(SessionConstant.SESSION_USER_ID);
		httpSession.removeAttribute(SessionConstant.SESSION_COMPANY_NOT_FINISH_REGISTER);
		httpSession.removeAttribute(SessionConstant.SESSION_USER_TYPE);
		return "redirect:/views/webviews/home.html";
	}
}