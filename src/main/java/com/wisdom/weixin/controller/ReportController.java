package com.wisdom.weixin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ReportController {
	
	@RequestMapping("/assets_analyse")
	public ModelAndView assetsAnalyse(Model model, HttpServletRequest request) {
		return new ModelAndView("weixin_empty");
	}

	@RequestMapping("/sales_analyse")
	public ModelAndView salesAnalyse(Model model, HttpServletRequest request) {
		return new ModelAndView("weixin_empty");
	}

	@RequestMapping("/expenses_analyse")
	public ModelAndView expensesAnalyse(Model model, HttpServletRequest request) {
		return new ModelAndView("weixin_empty");
	}

	@RequestMapping("/more_reports")
	public ModelAndView moreReports(Model model, HttpServletRequest request) {
		return new ModelAndView("weixin_empty");
	}
	
}
