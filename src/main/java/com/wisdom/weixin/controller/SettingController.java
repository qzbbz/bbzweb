package com.wisdom.weixin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SettingController {
	
	@RequestMapping("/settings")
	public ModelAndView settings(Model model, HttpServletRequest request) {
		return new ModelAndView("weixin_empty");
	}

	@RequestMapping("/bind_company")
	public ModelAndView bindCompany(Model model, HttpServletRequest request) {
		return new ModelAndView("bind_company");
	}

	@RequestMapping("/help")
	public ModelAndView help(Model model, HttpServletRequest request) {
		return new ModelAndView("weixin_empty");
	}
	
}
