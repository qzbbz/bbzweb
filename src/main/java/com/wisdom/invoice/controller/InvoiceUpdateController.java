package com.wisdom.invoice.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wisdom.common.utils.Result;
import com.wisdom.common.utils.ResultCode;
import com.wisdom.invoice.service.IInvoiceService;
import com.wisdom.invoice.service.ISingleInvoiceService;

@Controller
@RequestMapping("/receipt")
public class InvoiceUpdateController {
	private static final Log log = LogFactory.getLog(InvoiceUpdateController.class);
	
	@Autowired
	private IInvoiceService invoiceService;
	@Autowired
	private ISingleInvoiceService singleInvoiceService;
	
	@ResponseBody
	@RequestMapping("/action=updateDetail")
	public Result updateInvoiceDetail(HttpServletRequest request){
		Result result = new Result();
		String userId = (String)request.getParameter("userId");
		String strInvoice = (String)request.getParameter("invoiceId");
		String date = (String)request.getParameter("date");
		String desc = (String)request.getParameter("desc");
		String amount = (String)request.getParameter("amount");
		String expenseType = (String)request.getParameter("expenseType");
		
		log.debug("params:" + userId + "," + strInvoice + "," + date + "," + desc + "," + amount + "," + expenseType);
		
		long invoiceId;
		long expenseTypeId;
		double amountValue;
		try{
			invoiceId = Long.parseLong(strInvoice);
			expenseTypeId = Long.parseLong(expenseType);
			amountValue = Double.parseDouble(amount);
		}catch(Exception e){
			log.error("convert params error!");
			result.setResultCode(ResultCode.paramError.code);
			return result;
		}
		
		boolean blRet = singleInvoiceService.updateInvoiceInfo(invoiceId, desc, "", amountValue, expenseTypeId, 1, date);
		if(blRet) 
			result.setResultCode(ResultCode.success.code);
		else {
			result.setResultCode(ResultCode.paramError.code);
			log.error("update invoice error!invoiceId:" + invoiceId);
			result.setMsg("update invoice detail failed!");
		}
		return result;
	}
	
}
