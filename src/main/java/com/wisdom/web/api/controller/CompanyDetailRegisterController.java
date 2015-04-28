package com.wisdom.web.api.controller;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wisdom.common.model.Company;
import com.wisdom.web.api.ICompanyDetailRegisterApi;
import com.wisdom.web.utils.ErrorCode;
import com.wisdom.web.utils.SessionConstant;

@Controller
public class CompanyDetailRegisterController {

	private static final Logger logger = LoggerFactory
			.getLogger(CompanyDetailRegisterController.class);

	@Autowired
	private ICompanyDetailRegisterApi companyDetailRegisterApi;

	@RequestMapping("/company/register")
	@ResponseBody
	public Map<Integer, String> companyRegister(HttpServletRequest request) {
		logger.debug("enter companyRegister");
		Map<Integer, String> retMap = new HashMap<>();
		String userId = (String) request.getSession().getAttribute(
				SessionConstant.SESSION_USER_ID);
		String companyName = request.getParameter("companyName");
		String monthExpense = request.getParameter("monthExpense");
		String perfectMoment = request.getParameter("perfectMoment");
		if (companyName != null && !companyName.isEmpty()
				&& monthExpense != null && !companyName.isEmpty()
				&& perfectMoment != null && !companyName.isEmpty()) {
			Company company = new Company();
			company.setName(companyName);
			company.setMonthExpense(Integer.valueOf(monthExpense));
			company.setParentId((long) -1);
			company.setPerfectMoment(perfectMoment);
			company.setCreateTime(new Timestamp(System.currentTimeMillis()));
			retMap = companyDetailRegisterApi.companyDetailRegister(userId,
					company);
			if (retMap.containsKey(ErrorCode.NO_ERROR_CODE)) {
				request.getSession().removeAttribute(
						SessionConstant.SESSION_COMPANY_NOT_FINISH_REGISTER);
			}
		} else {
			retMap.put(ErrorCode.COMPANY_REGISTER_INFO_EMPTY_ERROR_CODE,
					ErrorCode.COMPANY_REGISTER_INFO_EMPTY_ERROR_MESSAGE);
		}
		logger.debug("finish companyRegister");
		return retMap;
	}

}
