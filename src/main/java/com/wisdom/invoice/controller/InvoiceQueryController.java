package com.wisdom.invoice.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wisdom.common.model.Invoice;
import com.wisdom.common.utils.Result;
import com.wisdom.common.utils.ResultCode;
import com.wisdom.invoice.domain.InvoiceInfoVo;
import com.wisdom.invoice.service.IInvoiceService;
import com.wisdom.invoice.service.ISingleInvoiceService;
import com.wisdom.invoice.service.impl.SingleInvoiceServiceImpl;

@Controller
@RequestMapping("/receipt")
public class InvoiceQueryController {
	private static final Log log = LogFactory.getLog(InvoiceQueryController.class);
	
	@Autowired
	private IInvoiceService invoiceService;
	@Autowired
	private ISingleInvoiceService singleInvoiceService;
	
	@RequestMapping("/action=searchInvoiceInfo")
	@ResponseBody
	public  Result getInvoiceInfoByInvoiceId(HttpServletRequest request){
		Result result = new Result();
		String strInvoiceId = (String)request.getParameter("invoiceId");
		if(StringUtils.isEmpty(strInvoiceId)){
			result.setResultCode(ResultCode.paramError.code);
			result.setMsg("发票号码不能为空!");
			return result;
		}
		
		long invoiceId;
		try{
			invoiceId = Long.parseLong(strInvoiceId);
		}catch (Exception e) {
			log.error("invoiceId not digit error!");
			result.setResultCode(ResultCode.paramError.code);
			return result;
		}
		log.debug("got invoiceId:"+ invoiceId);
		InvoiceInfoVo vo = invoiceService.getInvoiceInfo(invoiceId);
		result.addResult("invoiceInfo", vo);
		result.setResultCode("0");
		return result;
	}
	
	
	@RequestMapping("/action=getInvoiceList")
	@ResponseBody
	public  Result getInvoiceInfoByStatus(HttpServletRequest request){
		Result result = new Result();
		String status = (String)request.getParameter("status");
		String page = (String)request.getParameter("page");
		String pageSize = (String)request.getParameter("pageSize");
		String userId = (String)request.getParameter("username");
		
		if(StringUtils.isEmpty(status)||StringUtils.isEmpty(userId)){
			result.setResultCode(ResultCode.paramError.code);
			result.setMsg("参数空错误");
			return result;
		}
		
		List list = new ArrayList<Invoice>();
		if("1".equals(status)){//草稿状态
			list = singleInvoiceService.getUserInvoiceByStatus(userId, status);
		}else{//提交状态
			int iPage = 1;
			int iPageSize = 10;
			try{
				 iPage = Integer.parseInt(page);
				 iPageSize = Integer.parseInt(pageSize);
			}catch(Exception e){
				log.error("parse page pagesize error");
				iPage =1 ;
				iPageSize = 10;
			}
			list = singleInvoiceService.getUserInvoiceByStatusByPage(userId, status,iPage,iPageSize);
		}
		result.addResult("invoiceList", list);
		result.setResultCode("0");
		return result;
	}
	
	
}
