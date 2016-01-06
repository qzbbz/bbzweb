package com.wisdom.web.api.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wisdom.common.model.SheetIncomeDetail;
import com.wisdom.company.service.ISheetIncomeDetailService;
import com.wisdom.user.service.IUserService;
@Controller
public class SheetIncomeDetailController {
	private static final Logger logger = LoggerFactory
			.getLogger(SheetIncomeDetailController.class);
	@Autowired
	private ISheetIncomeDetailService sheetIncomeDetailService;
	@Autowired
	private IUserService userService;

	@RequestMapping("/company/getAllSheetIncomeDetail")
	@ResponseBody
	public List<SheetIncomeDetail> getAllSheetIncomeDetail(HttpServletRequest request) {
		logger.debug("enter getAllSheetIncomeDetail");
		String userId = (String) request.getSession().getAttribute("userId");
		long companyId = userService.getCompanyIdByUserId(userId);
		List<SheetIncomeDetail> list=new ArrayList<>();
		String dateNo=request.getParameter("type");
		String date=request.getParameter("date");
		String projectString=request.getParameter("project");
		String[] projects=projectString.split(";");
		if(Integer.parseInt(dateNo)==0){
			list=sheetIncomeDetailService.InvokingOperateSqlByYear(date, projects,companyId);
		}else if(Integer.parseInt(dateNo)==1){
			list =sheetIncomeDetailService.InvokingOperateSqlByMonth(date, projects,companyId);
		}
		logger.debug("finish getAllSheetIncomeDetail");
		return list;
	}

	
}
