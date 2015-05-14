package com.wisdom.invoice.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wisdom.common.utils.JsonUtils;
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
		String title = (String)request.getParameter("title");
		String strInvoice = (String)request.getParameter("invoiceId");
		String date = (String)request.getParameter("date");
		String desc = (String)request.getParameter("desc");
		String amount = (String)request.getParameter("amount");
		String expenseType = (String)request.getParameter("expenseType");
		
		log.debug("params:" + userId + "," + title + "," + strInvoice + "," + date + "," + desc + "," + amount + "," + expenseType);
		
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
		
		boolean blRet = singleInvoiceService.updateInvoiceInfo(invoiceId, desc, title, amountValue, expenseTypeId, 1, date);
		if(blRet) 
			result.setResultCode(ResultCode.success.code);
		else {
			result.setResultCode(ResultCode.paramError.code);
			log.error("update invoice error!invoiceId:" + invoiceId);
			result.setMsg("update invoice detail failed!");
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/action=submitBatchInvoice")
	public Result submitBatchInvoice(HttpServletRequest request){
		Result result = new Result();
		String userId = (String)request.getParameter("userId");
		String strInvoice = (String)request.getParameter("invoiceList");
		Map<String,Object> resultMap = new HashMap<String,Object>();
		
		if(StringUtils.isEmpty(userId) || StringUtils.isEmpty(strInvoice)){
			log.error("param empty error!");
			result.setResultCode(ResultCode.paramError.code);
			return result;
		}
		
		List<String> invoiceList = (List<String>)Arrays.asList(strInvoice.split(","));
		List failList = new ArrayList<Object>();
		for(String s:invoiceList){
			long invoiceId = Long.parseLong(s);
			Map retMap = new HashMap<String,Object>();
			retMap = invoiceService.submitUserInvoice(userId, invoiceId, null);
			if(null != retMap && ((Boolean)retMap.get("success")).booleanValue()){
				log.info("invoiceId:" + invoiceId +" submit success");
				result.setResultCode(ResultCode.success.code);
			}else{
				failList.add(retMap);
				log.error("invoiceId:" + invoiceId +" submit failed!" + "msg:" + retMap.get("msg"));
			}
		}
		if(failList.size() > 0){
			result.addResult("failList", failList);
		}
		return result;
	}
	
	/**
	 * body={"data":{["img":"","fileName":""],["img":"","fileName":""],["img":"","fileName":""]}
	 * @param request
	 * @return
	 */
	
	@ResponseBody
	@RequestMapping("action=uploadInvoice")
	public Result uploadInvoice(HttpServletRequest request){
		Result result = new Result();
		String userId = (String)request.getParameter("userId");
		String strBody = (String)request.getParameter("body");
		
		if(StringUtils.isEmpty(userId)){
			result.setResultCode(ResultCode.notLogin.code);
			return result;
		}
				
		if(StringUtils.isEmpty("body")){
			log.error("params body error");
			result.setResultCode(ResultCode.paramError.code);
			result.addResult("msg", "body param error!");
			return result;
		}
		
		List list = new ArrayList();
		try{
			Map obj = (Map)JsonUtils.fromJson(strBody, HashMap.class);
			list = (List)obj.get("data");
			if(null == list || list.size() <= 0){
				log.error("empty img list error");
				result.addResult("msg", "body param error!");
				return result;
			}
		}catch(Exception e){
			log.error("body parameter parse error!");
			result.addResult("msg", "body param error!");
			return result;
		}
		List nameList = new ArrayList();
		int num = 0;
		for(Map a : (List<Map<String,Object>>) list){
			String img = (String)a.get("img");
			Map map = invoiceService.createInvoiceProcess(userId, img, "0", "1", null);
			if(null != map && ((Boolean)map.get("success")).booleanValue()){
				num++;
			}else{
				nameList.add((String)a.get("fileName"));
			}
		}
		if(nameList.size()>0){
			result.addResult("failList", nameList);
		}
		result.addResult("succNum", num);
		return result;
	}
}
