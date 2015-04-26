package com.wisdom.weixin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ReportController {
	
	@RequestMapping("/assets_analyse")
	public ModelAndView assetsAnalyse(HttpServletRequest request) {
		return new ModelAndView("weixin_empty");
	}

	@RequestMapping("/sales_analyse")
	public ModelAndView salesAnalyse(HttpServletRequest request) {
		return new ModelAndView("weixin_empty");
	}

	@RequestMapping("/expenses_analyse")
	public ModelAndView expensesAnalyse(HttpServletRequest request) {
		return new ModelAndView("weixin_empty");
	}

	@RequestMapping("/more_reports")
	public ModelAndView moreReports(HttpServletRequest request) {
		return new ModelAndView("weixin_empty");
	}
	
}
