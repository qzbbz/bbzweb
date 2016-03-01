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


import com.wisdom.common.model.Sales;
import com.wisdom.common.model.SalesComment;
import com.wisdom.sales.service.ISalesService;

@Controller
public class SalesController {

	@Autowired ISalesService salesService;
	

	
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
		Integer salesId = salesService.addSalesRecord(sales);
		if(salesId == -1){
			retMap.put("status", "nok");
		}else{
			//Add comments
		}
			
		return retMap;
	}
	
	@RequestMapping("/sales/getSales")
	@ResponseBody
	public Map<String, List<List<String>>> getSales(HttpServletRequest request){
		//logger.debug("enter addPermission");
		List<List<String>> retList = new ArrayList<>();
		List<Sales> sales = salesService.getSalesRecords();
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
		String status = request.getParameter("status");
		String comment = request.getParameter("comment");
		String updatedTime = request.getParameter("updated_time");
		Integer id = Integer.valueOf(request.getParameter("id"));
		if(salesService.updateSales(id, comment, accountant, updatedTime, status)){
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
		
	
	
	
}
