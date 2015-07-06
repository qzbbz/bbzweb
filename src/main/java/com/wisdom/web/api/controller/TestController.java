package com.wisdom.web.api.controller;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wisdom.common.model.Company;
import com.wisdom.common.utils.Result;
import com.wisdom.common.utils.ResultCode;
import com.wisdom.company.service.ICompanyService;
import com.wisdom.web.api.IGenerateCostCenterEncodeApi;
@Controller
public class TestController {
	private static final Logger logger = LoggerFactory
			.getLogger(TestController.class);
	@Autowired
	private IGenerateCostCenterEncodeApi generateCostCenterApiService;
	
	@Autowired
	private ICompanyService companyService;
	
	
	@ResponseBody
	@RequestMapping("/company/costCenter")
	public Result addDept(HttpServletRequest request) {
		Result result = new Result();
		String companyId = request.getParameter("companyId");
		String deptId = request.getParameter("curDeptId");
		String level = request.getParameter("level");
		String deptCostEncode = request.getParameter("deptCostEncode");
		String parentCostCenterEncode = request.getParameter("parentCostCenterEncode");

		if (StringUtils.isEmpty(companyId)) {
			result.setResultCode(ResultCode.paramError.code);
			result.setMsg("companyId不能为空！");
			return result;
		}
		
		logger.debug("companyId:{},deptId:{}",companyId,deptId);
		deptCostEncode = generateCostCenterApiService.genCostCenterAndUpdate(Long.parseLong(companyId), 
															Long.parseLong(deptId));
		result.addResult("costCode", deptCostEncode);
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/company/testCompany")
	public Result addTestCompany(HttpServletRequest request) {
		Result result = new Result();
//		String companyId = request.getParameter("companyId");
		String name = request.getParameter("name");
		String parentId = request.getParameter("parentId");
		String deptCostEncode = request.getParameter("deptCostEncode");
		String parentCostCenterEncode = request.getParameter("parentCostCenterEncode");

		if (StringUtils.isEmpty(name)) {
			result.setResultCode(ResultCode.paramError.code);
			result.setMsg("companyId不能为空！");
			return result;
		}
		
		logger.debug("companyId:{},deptId:{}",name,parentId);
		Company company = new Company();
		company.setName(name);
		company.setMonthExpense("100");
		company.setPerfectMoment("");
		company.setCreateTime(new Timestamp(System.currentTimeMillis()));
		company.setParentId(Long.parseLong(parentId));
		
		
		companyService.addCompany(company);
		result.addResult("company", company);
		return result;
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
