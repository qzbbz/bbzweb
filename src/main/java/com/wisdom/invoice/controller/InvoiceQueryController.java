package com.wisdom.invoice.controller;

import java.sql.Timestamp;
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
import com.wisdom.common.model.InvoiceInfo;
import com.wisdom.common.utils.Result;
import com.wisdom.common.utils.ResultCode;
import com.wisdom.invoice.domain.InvoiceInfoVo;
import com.wisdom.invoice.service.IInvoiceService;
import com.wisdom.invoice.service.ISingleInvoiceService;
import com.wisdom.invoice.service.IUserInvoiceService;
import com.wisdom.invoice.service.impl.SingleInvoiceServiceImpl;
import com.wisdom.invoice.service.impl.UserInvoiceServiceImpl;

@Controller
@RequestMapping("/receipt")
public class InvoiceQueryController {
	private static final Log log = LogFactory.getLog(InvoiceQueryController.class);
	
	@Autowired
	private IInvoiceService invoiceService;
	@Autowired
	private ISingleInvoiceService singleInvoiceService;
	@Autowired
	private IUserInvoiceService userInvoiceService;
	/**
	 * 根据发票号码搜索
	 * @param request
	 * @return
	 */
	@RequestMapping("/action=searchInvoiceInfo")
	@ResponseBody
	public  Result getInvoiceInfoByInvoiceId(HttpServletRequest request){
		Result result = new Result();
		String strInvoiceCode = (String)request.getParameter("invoiceCode");
		if(StringUtils.isEmpty(strInvoiceCode)){
			result.setResultCode(ResultCode.paramError.code);
			result.setMsg("发票号码不能为空!");
			return result;
		}
		
		/*String userId = (String)request.getParameter("userId");
		if(StringUtils.isEmpty(userId)){
			result.setResultCode(ResultCode.paramError.code);
			result.setMsg("用户名不能为空!");
			return result;
		}
		*/
		
		log.debug("got invoiceCode:"+ strInvoiceCode);
		InvoiceInfoVo vo = invoiceService.getInvoiceInfoByCode(strInvoiceCode);
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
	/**
	 * 获取我的收件箱的所有数据,支持分页
	 * @param request
	 * @return
	 */
	@RequestMapping("/action=myInBox")
	@ResponseBody
	public  Result getMyBoxInvoice(HttpServletRequest request){
		Result result = new Result();
		
		String userId = (String)request.getParameter("userId");
		if(StringUtils.isEmpty(userId)){
			result.setResultCode(ResultCode.paramError.code);
			result.setMsg("参数空错误");
			return result;
		}
		
		try {
			String strPage = request.getParameter("page");
			String strPageSize = (String)request.getParameter("pageSize");
			String submitter = (String)request.getParameter("submitter");
			String strAmount = request.getParameter("amount");
			String date = (String)request.getParameter("date");
			
			
			Integer page =null;
			if (!StringUtils.isEmpty(strPage)) {
				page = Integer.parseInt(strPage);
			}
			
			Integer pageSize = null;
			
			if (!StringUtils.isEmpty(strPageSize)) {
				pageSize = Integer.parseInt(strPageSize);
			}
			Double amount = null;
			if(!StringUtils.isEmpty(strAmount)){
				amount = Double.parseDouble(strAmount);
			}
			
			Timestamp time = null;
			if(!StringUtils.isEmpty(date)){
				time = Timestamp.valueOf(date);
			}
			List<InvoiceInfo> list = userInvoiceService.getInvoiceInfoByCondition(userId, date, submitter, amount, null, page, pageSize);
			result.getResultMap().put("invoiceList", list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return result;
		}
		
		return result;
	}
	
}
