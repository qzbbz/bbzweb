package com.wisdom.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wisdom.web.utils.SessionConstant;

@Controller
public class HTMLViewController {

	@RequestMapping("/homeHtml")
	public String getHomeHtml() {
		return "redirect:/views/webviews/home.html";
	}
	
	@RequestMapping("/featureHtml")
	public String getFeatureHtml() {
		return "redirect:/views/webviews/feature.html";
	}
	
	@RequestMapping("/registerHtml")
	public String getRegisterHtml() {
		return "redirect:/views/webviews/register.html";
	}
	
	@RequestMapping("/communityHtml")
	public String getCommunityHtml() {
		return "redirect:/views/webviews/community.html";
	}
	
	@RequestMapping("/teamHtml")
	public String getTeamHtml() {
		return "redirect:/views/webviews/team.html";
	}
	
	@RequestMapping("/contactHtml")
	public String getContactHtml() {
		return "redirect:/views/webviews/contact.html";
	}
	
	@RequestMapping("/company/selectAccounterHtml")
	public String getSelectAccounterHtml() {
		return "redirect:/views/webviews/select_accounter.html";
	}
	
	@RequestMapping("/companyUser/staffInfoHtml")
	public String getCompanyStaffHtml() {
		return "redirect:/views/webviews/staff_info.html";
	}
	
	@RequestMapping("/accounter/accounterInfoHtml")
	public String getAccounterInfoHtml() {
		return "redirect:/views/webviews/accounter_info.html";
	}
	
	@RequestMapping("/company/companyInfoHtml")
	public String getCompanyInfoHtml() {
		return "redirect:/views/webviews/company_info.html";
	}
	
	@RequestMapping("/company/companyDetailRegisterHtml")
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