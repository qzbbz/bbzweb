package com.wisdom.invoice.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wisdom.common.utils.Result;
import com.wisdom.common.utils.ResultCode;
import com.wisdom.invoice.domain.InvoiceInfoVo;
import com.wisdom.invoice.service.IInvoiceService;

@Controller
@RequestMapping("/receipt")
public class InvoiceQueryController {
	private static final Log log = LogFactory.getLog(InvoiceQueryController.class);
	
	@Autowired
	private IInvoiceService invoiceService;
	
	@RequestMapping("/action=getInvoiceInfo")
	@ResponseBody
	public  Result getInvoiceInfoByInvoiceId(HttpServletRequest request){
		Result result = new Result();
		String strInvoiceId = (String)request.getParameter("invoiceId");
		if(StringUtils.isEmpty(strInvoiceId)){
			result.setResultCode(ResultCode.paramError.code);
			result.setMsg("发票号码不能为空!");
			return result;
		}
		
		InvoiceInfoVo invoiceInfo = new InvoiceInfoVo();
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

}
