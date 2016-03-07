package com.wisdom.web.api.controller;



import java.sql.Timestamp;
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

import com.wisdom.common.model.Company;
import com.wisdom.common.model.Sales;
import com.wisdom.common.model.SalesComment;
import com.wisdom.common.model.User;
import com.wisdom.company.service.ICompanyService;
import com.wisdom.sales.service.ISalesService;
import com.wisdom.user.service.IUserModifyService;
import com.wisdom.user.service.IUserService;
import com.wisdom.web.utils.SessionConstant;
import com.wisdom.web.utils.UserPwdMD5Encrypt;

@Controller
public class SalesController {

	@Autowired ISalesService salesService;
	
	@Autowired IUserService userService;
	
	@Autowired ICompanyService companyService;
	
	@Autowired IUserModifyService userModifyService;

	
	private static final Logger logger = LoggerFactory
			.getLogger(SalesController.class);
	
	
	@RequestMapping("/sales/addSales")
	@ResponseBody
	public Map<String, String> addSales(HttpServletRequest request){
		//logger.debug("enter addPermission");
		Map<String, String> retMap = new HashMap<>();
		Sales sales = new Sales();
		sales.setUserName(request.getParameter("user_name"));
		sales.setUserCompany(request.getParameter("user_company"));
		sales.setUserPhone(request.getParameter("user_phone"));
		sales.setLatestComment(request.getParameter("comment"));
		sales.setAccountant(request.getParameter("accountant"));
		sales.setSallerAccount(request.getParameter("saller_account"));
		sales.setUpdatedTime(request.getParameter("updated_time"));
		sales.setAccountantId(request.getParameter("accountant_id"));
		sales.setSallerId(request.getParameter("saller_id"));
		Integer salesId = salesService.addSalesRecord(sales);
		if(salesId == -1){
			retMap.put("status", "nok");
		}else{
			//Add comments
			User user = new User();
			user.setUserId(sales.getUserPhone() + "@bangbangzhang.com");
			user.setTypeId(2);
			user.setCompanyId((long) -1);
			user.setAuditStatus(1);
			user.setCreateTime(new Timestamp(System.currentTimeMillis()));
			if (userService.addUser(user)
					&& userService.setUserPwdByUserId(
							UserPwdMD5Encrypt.getPasswordByMD5Encrypt("123456"),
							user.getUserId())) {
				Company company = new Company();
				company.setName(sales.getUserCompany());
				company.setMonthExpense("0-10K");
				company.setParentId((long) -1);
				company.setPerfectMoment("工作日上午9:00~10:00");
				company.setCreateTime(new Timestamp(System.currentTimeMillis()));
				long companyId = companyService.addCompany(company);
				logger.debug("add company id : {}", companyId);
				userModifyService.modifyUserCompanyIdByUserId(user.getUserId(), companyId);
				userService.addUserPhone(user.getUserId(), sales.getUserPhone(), 1);
			} else {
				retMap.put("status", "nok");
			}
		}
			
		return retMap;
	}
	
	@RequestMapping("/sales/getSales")
	@ResponseBody
	public Map<String, List<List<String>>> getSales(HttpServletRequest request){
		String userId = (String) request.getSession().getAttribute(SessionConstant.SESSION_USER_ID);
		User user = userService.getUserByUserId(userId);
		String level = user.getUserLevel();
		List<List<String>> retList = new ArrayList<>();
		List<Sales> sales = new ArrayList<>();
		if(level != null && level.equals("sales admin")){
			sales = salesService.getSalesRecords();
			
		}else{
			sales = salesService.getSalesRecordByUserId(userId);
		}

		for(Sales sale: sales){
			List<String> tmp = new ArrayList<>();
			tmp.add(sale.getSallerAccount());
			tmp.add(sale.getUserCompany());
			tmp.add(sale.getUserName());
			tmp.add(sale.getUserPhone());
			tmp.add(sale.getLatestComment());
			tmp.add(sale.getUpdatedTime());
			tmp.add(sale.getAccountant());
			tmp.add(sale.getStatus());
			tmp.add(sale.getId().toString());
			tmp.add("<input type='button' value='编辑' id='edit-" + sale.getId().toString() + "' class='edit-btn'/>");
			tmp.add("<input type='button' value='删除' id='delete-" + sale.getId().toString() + "' class='delete-btn'/>");
			
			retList.add(tmp);
		}
		
		Map<String, List<List<String>>> retMap = new HashMap<>();
		retMap.put("data", retList);
		return retMap;
	}
	

	@RequestMapping("/sales/updateSales")
	@ResponseBody
	public Map<String, String> updateSales(HttpServletRequest request){
		Map<String, String> retMap = new HashMap<>();
		String accountant = request.getParameter("accountant");
		String accountantId = request.getParameter("accountant_id");
		String status = request.getParameter("status");
		String comment = request.getParameter("comment");
		String updatedTime = request.getParameter("updated_time");
		Integer id = Integer.valueOf(request.getParameter("id"));
		Sales sales = salesService.getSalesRecordById(id);
		if(sales != null && !sales.getUpdatedTime().equals(updatedTime)) {
			salesService.updateSalesSendEmailStatus(id, 0);
		}
		if(salesService.updateSales(id, comment, accountant, updatedTime, status, accountantId)){
			retMap.put("status", "ok");
		}else{
			retMap.put("status", "nok");
		}
		return retMap;
		
	}
	
	@RequestMapping("/sales/getAllComments")
	@ResponseBody
	public List<Map<String, String> >getAllComments(HttpServletRequest request){
		List<Map<String, String>> retList = new ArrayList<>();
		Integer salesId = Integer.valueOf(request.getParameter("id"));
		List<SalesComment> comments = salesService.getSalesCommentsBySaleId(salesId);
		for(SalesComment comment: comments){
			Map<String, String> tmpMap = new HashMap<>();
			tmpMap.put("comment", comment.getComment());
			tmpMap.put("updated_time", comment.getUpdatedTime().toString());
			
			retList.add(tmpMap);
		}
		return retList;
		
	}
		
	@RequestMapping("/sales/deleteSalesRecord")
	@ResponseBody
	public Map<String, String> deleteSalesRecord(HttpServletRequest request){
		Map<String, String> retMap = new HashMap<>();
		Integer id = Integer.valueOf(request.getParameter("id"));
		if(salesService.deleteSalesRecordById(id)){
			retMap.put("status", "ok");
		}else{
			retMap.put("status", "nok");
		}
		return retMap;
		
	}
	
	
	
}
