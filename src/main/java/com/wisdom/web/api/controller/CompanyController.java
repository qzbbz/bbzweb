package com.wisdom.web.api.controller;

import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import com.alipay.utils.AlipayNotify;
import com.alipay.utils.AlipayService;
import com.wisdom.area.service.IAreaService;
import com.wisdom.common.model.Company;
import com.wisdom.common.model.CompanyDetail;
import com.wisdom.common.model.CompanyPay;
import com.wisdom.common.model.SheetBalance;
import com.wisdom.common.model.SheetCash;
import com.wisdom.common.model.SheetIncome;
import com.wisdom.common.model.SheetSalaryTax;
import com.wisdom.common.model.UserOpenid;
import com.wisdom.company.dao.ISheetBalanceDao;
import com.wisdom.company.dao.ISheetCashDao;
import com.wisdom.company.dao.ISheetIncomeDao;
import com.wisdom.company.dao.ISheetSalaryTaxDao;
import com.wisdom.company.service.ICompanyDetailService;
import com.wisdom.company.service.ICompanyPayService;
import com.wisdom.company.service.ICompanyService;
import com.wisdom.user.dao.IUserOpenIdDao;
import com.wisdom.user.service.IUserModifyService;
import com.wisdom.user.service.IUserService;
import com.wisdom.web.api.ICompanyApi;
import com.wisdom.web.utils.CompanyOrgStructure;
import com.wisdom.web.utils.ErrorCode;
import com.wisdom.web.utils.PdfProcess;
import com.wisdom.web.utils.SessionConstant;
import com.wisdom.recommender.service.IRecommendService;


@Controller
public class CompanyController {

	private static final Logger logger = LoggerFactory
			.getLogger(CompanyController.class);

	@Autowired
	private ICompanyService companyService;
	
	@Autowired
	private ICompanyDetailService companyDetailService;
	
	@Autowired
	private ICompanyPayService companyPayService;

	@Autowired
	private IUserService userService;
	
	@Autowired
	private IAreaService areaService;
	
	@Autowired
	private ICompanyApi companyApi;
	
	@Autowired
	private IUserModifyService userModifyService;
	
	@Autowired
	private ISheetBalanceDao sheetBalanceDao;
	
	@Autowired
	private ISheetCashDao sheetCashDao;
	
	@Autowired
	private ISheetIncomeDao sheetIncomeDao;
	
	@Autowired
	private ISheetSalaryTaxDao sheetSalaryTaxDao;
	
	@Autowired
	private IUserOpenIdDao userOpenIdDao;
	
	@Autowired
	private IRecommendService recommendService;
	
	@RequestMapping("/company/selectAccounter")
	@ResponseBody
	public Map<Integer, String> selectAccounter(HttpServletRequest request) {
		logger.info("enter selectAccounter");
		boolean selectSuccess = false;
		Map<Integer, String> retMap = new HashMap<>();
		String accounterUserId = request.getParameter("accounterId");
		String userId = (String) request.getSession().getAttribute("userId");
		long companyId = userService.getCompanyIdByUserId(userId);
		int accounterAmount = companyService
				.accounterAmountBelongToCompany(companyId);
		if (companyId > -1 && accounterAmount < 2) {
			selectSuccess = companyService.companySelectAccounter(companyId,
					accounterUserId);
		}
		if (selectSuccess) {
			retMap.put(ErrorCode.NO_ERROR_CODE, ErrorCode.NO_ERROR_MESSAGE);
		} else {
			retMap.put(ErrorCode.COMPANY_SELECT_ACCOUNTER_ERROR_CODE,
					ErrorCode.COMPANY_SELECT_ACCOUNTER_ERROR_MESSAGE);
		}
		logger.info("leave selectAccounter");
		return retMap;
	}
	
	@RequestMapping("/company/applyMailInvoice")
	@ResponseBody
	public Map<Integer, String> applyMailInvoice(HttpServletRequest request) {
		Map<Integer, String> retMap = new HashMap<>();
		String userId = (String) request.getSession().getAttribute("userId");
		String mailAddress = request.getParameter("address");
		long companyId = userService.getCompanyIdByUserId(userId);
		companyPayService.updateApplyInvoiceByCompanyId(companyId, 1, mailAddress);
		return retMap;
	}
	
	@RequestMapping("/company/selectOneAccounter")
	@ResponseBody
	public String selectOneAccounter(HttpServletRequest request) {
		logger.info("enter selectOneAccounter");
		String accounterUserId = request.getParameter("accounterId");
		String alipayMonth = request.getParameter("alipayMonth");
		String alipayAmount = request.getParameter("alipayAmount");
		String userId = (String) request.getSession().getAttribute("userId");
		long companyId = userService.getCompanyIdByUserId(userId);
		CompanyPay companyPay = companyPayService.getCompanyPayByCompanyId(companyId);
		UUID uuid = UUID.randomUUID();
		String orderNo = uuid.toString();
		if(companyPay == null) {
			CompanyPay newPay = new CompanyPay();
			newPay.setCompanyId(companyId);
			newPay.setOrderNo(orderNo);
			newPay.setPayAmount(Double.valueOf(alipayAmount));
			newPay.setServiceTime(Integer.valueOf(alipayMonth));
			newPay.setCreateTime(new Timestamp(System.currentTimeMillis()));
			companyPayService.addCompanyPay(newPay);
		} else {
			companyPayService.updateCompanyPayByCompanyId(companyId, Double.valueOf(alipayAmount), orderNo, Integer.valueOf(alipayMonth));
		}
		companyService.updateCompanyAccounter(companyId, accounterUserId);
		AlipayService alipayService = new AlipayService();
        //String resHtml = alipayService.buildAlipayRequest(Double.valueOf(alipayAmount), orderNo);
		String resHtml = alipayService.buildAlipayRequest(Double.valueOf("0.01"), orderNo);
		logger.info("leave selectOneAccounter");
		return resHtml;
	}
	
	@RequestMapping("/alipayFinish")
	public String alipayFinish(HttpServletRequest request) throws UnsupportedEncodingException {
		Map<String,String> params = new HashMap<String,String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}
		params.put("body", "感谢您购买元升财务咨询的服务!");
		params.put("subject", "元升财务咨询服务费用");
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		//商户订单号
		String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"utf-8");
		//支付宝交易号
		String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"utf-8");
		//交易状态
		String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"utf-8");
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
		//计算得出通知验证结果
		boolean verify_result = AlipayNotify.verify(params);
		logger.info("alipayFinish : out_trade_no" + out_trade_no);
		if(verify_result){//验证成功
			logger.info("alipayFinish : verify success");
			if(trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")){
				//判断该笔订单是否在商户网站中已经做过处理
				//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
				//如果有做过处理，不执行商户的业务程序
				logger.info("alipayFinish : TRADE_FINISHED || TRADE_SUCCESS");
				CompanyPay companyPay = companyPayService.getCompanyPayByOrderNo(out_trade_no);
				String contractFileName = "";
				try {
					Company company = companyService.getCompanyByCompanyId(companyPay.getCompanyId());
					UUID uuid = UUID.randomUUID();
					contractFileName = uuid.toString() + "_contract_" + String.valueOf(company.getId()) + ".pdf";
					Calendar c = Calendar.getInstance();    
				    SimpleDateFormat f = new SimpleDateFormat("yyyy年MM月dd日");    
				    String date = f.format(c.getTime());
				    String realPath = request.getSession().getServletContext()
							.getRealPath("/WEB-INF").substring(0);
				    String webRoot = realPath;
					realPath = realPath.substring(0, realPath.indexOf("/", 1)) + "/files/company/";
					CompanyDetail companyDetail = companyDetailService.getCompanyDetailByCompanyId(companyPay.getCompanyId());
					
					String code = companyDetail.getOrgCode();
					String address = companyDetail.getProvince() + companyDetail.getCity() + companyDetail.getArea() + companyDetail.getExtra();
					String owner = companyDetail.getCorporation();
					logger.info(company.getName());
					logger.info(code);
					logger.info(address);
					logger.info(owner);
					logger.info(String.valueOf(companyPay.getPayAmount()));
					logger.info(date);
					String email = recommendService.getCustomerEmailByCompanyId(companyPay.getCompanyId());
					recommendService.setCustomerPaid(email);
					PdfProcess.generateContractPdf(realPath + contractFileName, company.getName(), code, address, owner, String.valueOf(companyPay.getPayAmount()), date, webRoot);
					
				} catch(Exception e) {
					logger.error(e.toString());
				} finally {
					companyPayService.updateCompanyPayStatusAndTimeByOrderNo(out_trade_no, 1, new Timestamp(System.currentTimeMillis()), contractFileName);
				}
			}
			
			//该页面可做页面美工编辑
			//out.println("验证成功<br />");
			//——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
			
			
		}else{
			//该页面可做页面美工编辑
			//out.println("验证失败");
			logger.info("alipayFinish : verify failed");
		}
		return "redirect:/views/webviews/company/select_accounter.html";
	}
	
	@RequestMapping("/company/checkSelectAccounter")
	@ResponseBody
	public Map<String, String> checkSelectAccounter(HttpServletRequest request) {
		logger.info("enter checkSelectAccounter");
		Map<String, String> retMap = new HashMap<>();
		String userId = (String) request.getSession().getAttribute("userId");
		long companyId = userService.getCompanyIdByUserId(userId);
		Company company = companyService.getCompanyByCompanyId(companyId);
		if(company != null && company.getAccounterId() != null && !company.getAccounterId().isEmpty()) {
			CompanyPay companyPay = companyPayService.getCompanyPayByCompanyIdAndPayStatus(companyId, 1);
			if(companyPay != null) {
				String accounterId = company.getAccounterId();
				String accounterName = userService.getUserNameByUserId(accounterId);
				int serviceTime = companyPay.getServiceTime();
				String payTime = companyPay.getCreateTime().toString();
				retMap.put("selected", "true");
				retMap.put("info", "您已选择了会计师："+accounterName+",您购买的服务时间为:"+String.valueOf(serviceTime)+"个月,您付款的时间为："+payTime+"。");
				retMap.put("amount", String.valueOf(companyPay.getPayAmount()));
				retMap.put("companyName", company.getName());
			} else {
				retMap.put("selected", "false");
			}
		} else {
			retMap.put("selected", "false");
			if(company == null) {
				retMap.put("error", "companyinfo");
			}
		}
		logger.info("leave checkSelectAccounter");
		return retMap;
	}
	
	@RequestMapping("/comDetailRegister")
	@ResponseBody
	public Map<String, String> companyFinishRegister(HttpSession httpSession, HttpServletRequest request) {
		logger.debug("enter companyFinishRegister");
		Map<String, String> params = new HashMap<>();
		params.put("userEmail", (String) httpSession.getAttribute(SessionConstant.SESSION_USER_EMAIL));
		params.put("userPwd", (String) httpSession.getAttribute(SessionConstant.SESSION_USER_PASSWORD));
		params.put("userName", request.getParameter("userName"));
		params.put("userCompanyName", request.getParameter("userCompanyName"));
		params.put("userCompanyIncomes", request.getParameter("userCompanyIncomes"));
		params.put("userCalledTime", request.getParameter("userCalledTime"));
		params.put("userPhone", request.getParameter("userPhone"));
		Map<String, String> retMap = companyApi.companyDetailRegister(params);
		logger.debug("finish companyFinishRegister, retMap : {}", retMap);
		return retMap;
	}
	
	@RequestMapping("/company/orgInfoSettings")
	public String orgInfoSettings(@RequestParam("files") MultipartFile[] files , HttpServletRequest request) {
		Map<String, String> params = new HashMap<>();
		String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/files/company");
		realPath = realPath.substring(0, realPath.indexOf("/", 1)) + "/files/company";
		params.put("realPath", realPath);
		params.put("userId", (String) request.getSession().getAttribute("userId"));
		params.put("companyName", request.getParameter("companyName"));
		params.put("corparation", request.getParameter("corparation"));
		params.put("orgCode", request.getParameter("orgCode"));
		params.put("taxCode", request.getParameter("taxCode"));
		params.put("province", areaService.getProvinceNameByProvinceId(request.getParameter("province")));
		params.put("city", areaService.getCityNameByCityId(request.getParameter("city")));
		params.put("area", areaService.getAreaNameByAreaId(request.getParameter("area")));
		params.put("addExtra", request.getParameter("addExtra"));
		params.put("bankName", request.getParameter("bankName"));
		params.put("companyResFile", request.getParameter("companyResFile"));
		params.put("orgCodeFile", request.getParameter("orgCodeFile"));
		params.put("taxCodeFile", request.getParameter("taxCodeFile"));
		companyApi.companyInfoSettings(files, params);
		return "redirect:/views/webviews/company/organization_structure_settings.html";
	}
	
	@RequestMapping("/company/setSalarySocialSecurityInfo")
	public String setSalarySocialSecurityInfo(HttpServletRequest request) {
		Map<String, String> retMap = new HashMap<>();
		String userId = (String) request.getSession().getAttribute("userId");
		retMap.put("userId", userId);
		retMap.put("currentCity", request.getParameter("currentCity"));
		retMap.put("currentType", request.getParameter("currentType"));
		retMap.put("pensionCratio", request.getParameter("pensionCratio"));
		retMap.put("pensionPratio", request.getParameter("pensionPratio"));
		retMap.put("pensionBase", request.getParameter("pensionBase"));
		retMap.put("medicalCratio", request.getParameter("medicalCratio"));
		retMap.put("medicalPratio", request.getParameter("medicalPratio"));
		retMap.put("medicalBase", request.getParameter("medicalBase"));
		retMap.put("unemployCratio", request.getParameter("unemployCratio"));
		retMap.put("unemployPratio", request.getParameter("unemployPratio"));
		retMap.put("unemployBase", request.getParameter("unemployBase"));
		retMap.put("injuryCratio", request.getParameter("injuryCratio"));
		retMap.put("injuryPratio", request.getParameter("injuryPratio"));
		retMap.put("injuryBase", request.getParameter("injuryBase"));
		retMap.put("birthCratio", request.getParameter("birthCratio"));
		retMap.put("birthPratio", request.getParameter("birthPratio"));
		retMap.put("birthBase", request.getParameter("birthBase"));
		retMap.put("accfundCratio", request.getParameter("accfundCratio"));
		retMap.put("accfundPratio", request.getParameter("accfundPratio"));
		retMap.put("accfundBase", request.getParameter("accfundBase"));
		retMap.put("salaryCratio", request.getParameter("salaryCratio"));
		retMap.put("salaryPratio", request.getParameter("salaryPratio"));
		retMap.put("salaryBase", request.getParameter("salaryBase"));
		retMap.put("bigmedicalCratio", request.getParameter("bigmedicalCratio"));
		retMap.put("bigmedicalPratio", request.getParameter("bigmedicalPratio"));
		retMap.put("bigmedicalBase", request.getParameter("bigmedicalBase"));
		retMap = companyApi.updateSSSInfo(retMap);
		logger.debug("finish setSalarySocialSecurityInfo, retMap : {}", retMap);
		return "redirect:/views/webviews/company/salary_welfare_args_settings.html";
	}
	
	@RequestMapping("/company/getSalarySocialSecurityInfo")
	@ResponseBody
	public Map<String, String> getSalarySocialSecurityInfo(HttpServletRequest request) {
		Map<String, String> retMap = new HashMap<>();
		String userId = (String) request.getSession().getAttribute("userId");
		String cityName = request.getParameter("currentCity");
		String type = request.getParameter("currentType");
		retMap = companyApi.getSSSInfo(userId, cityName, type);
		logger.debug("finish getSalarySocialSecurityInfo, retMap : {}", retMap);
		return retMap;
	}
	
	@RequestMapping("/company/checkTemplateExist")
	@ResponseBody
	public Map<String, String> checkTemplateExist(HttpServletRequest request) {
		String userId = (String) request.getSession().getAttribute("userId");
		String cityName = request.getParameter("currentCity");
		String type = request.getParameter("currentType");
		return companyApi.checkTemplateExist(userId, cityName, type);
	}
	
	@RequestMapping("/company/uploadSalary")
	public String uploadSalary(@RequestParam("salaryFile") MultipartFile file, HttpServletRequest request) throws ParseException {
		logger.debug("get upload request");
		String date=request.getParameter("salary_date");
		
		String userId = (String) request.getSession().getAttribute("userId");
		String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/files/company");
		realPath = realPath.substring(0, realPath.indexOf("/", 1)) + "/files/company";
		companyApi.uploadCompanySalary(userId, realPath, file,date);
		return "redirect:/views/webviews/company/salary_welfare_upload.html";
	}
	
	/*@RequestMapping("/company/uploadBankSta")
	@ResponseBody
	public Map<String, String> uploadBankSta(@RequestParam("files") MultipartFile file, HttpServletRequest request) {
		String userId = (String) request.getSession().getAttribute("userId");
		String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/files/company");
		String date = request.getParameter("date");
		Map<String, String> params = new HashMap<>();
		params.put("userId", userId);
		params.put("realPath", realPath);
		params.put("date", date);
		return companyApi.uploadCompanyBankSta(file, params);
	}*/
	
	@RequestMapping("/company/uploadBankSta")
	@ResponseBody
	public String uploadBankSta(DefaultMultipartHttpServletRequest multipartRequest,
			HttpServletRequest request) {
		logger.debug("===>uploadBankSta");
		String userId = (String) request.getSession().getAttribute("userId");
		
		String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/files/company");
		realPath = realPath.substring(0, realPath.indexOf("/", 1)) + "/files/company";
		String date = request.getParameter("date");
		Map<String, String> params = new HashMap<>();
		params.put("userId", userId);
		params.put("realPath", realPath);
		params.put("date", date);
		if (multipartRequest != null) {
			Iterator<String> iterator = multipartRequest.getFileNames();
			while (iterator.hasNext()) {
				MultipartFile multifile = multipartRequest
						.getFile((String) iterator.next());
				companyApi.uploadCompanyBankSta(multifile, params);
			}
		}
		return new HashMap<String, String>().put("url", "");
	}
	
	@RequestMapping("/company/uploadCompanySales")
	@ResponseBody
	public String uploadCompanySales(DefaultMultipartHttpServletRequest multipartRequest,
			HttpServletRequest request) {
		logger.debug("===>uploadBankSta");
		String userId = (String) request.getSession().getAttribute("userId");
		String date=request.getParameter("sales_date");
		String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/files/company");
		realPath = realPath.substring(0, realPath.indexOf("/", 1)) + "/files/company";
		Map<String, String> params = new HashMap<>();
		params.put("userId", userId);
		params.put("realPath", realPath);
		params.put("date", date);
		if (multipartRequest != null) {
			Iterator<String> iterator = multipartRequest.getFileNames();
			while (iterator.hasNext()) {
				MultipartFile multifile = multipartRequest
						.getFile((String) iterator.next());
				companyApi.uploadCompanySales(multifile, params);
			}
		}
		return new HashMap<String, String>().put("url", "");
	}
	
	@RequestMapping("/company/deleteCompanyBankSta")
	@ResponseBody
	public Map<String, String> deleteCompanyBankSta(HttpServletRequest request) {
		Map<String, String> retMap = new HashMap<>();
		String idList = (String)request.getParameter("deleteIdList");
		String realPath = request.getSession().getServletContext()
				.getRealPath("/WEB-INF/files/company");
		realPath = realPath.substring(0, realPath.indexOf("/", 1)) + "/files/company";
		if(companyApi.deleteCompanyBill(idList, realPath)) {
			retMap.put("error_code", "0");
		} else {
			retMap.put("error_code", "1");
		}
		return retMap;
	}
	
	@RequestMapping("/company/getAllCompanyBankSta")
	@ResponseBody
	public List<Map<String, String>> getAllCompanyBankSta(HttpServletRequest request) {
		String userId = (String) request.getSession().getAttribute("userId");
		Map<String, String> params = new HashMap<>();
		params.put("conditionValue", request.getParameter("conditionValue"));
		params.put("conditionType", request.getParameter("conditionType"));
		params.put("userId", userId);
		return companyApi.getCompanyBankStaByCondition(params);
	}
	
	@RequestMapping("/company/getAllCompanySales")
	@ResponseBody
	public List<Map<String, String>> getAllCompanySales(HttpServletRequest request) {
		String userId = (String) request.getSession().getAttribute("userId");
		Map<String, String> params = new HashMap<>();
		params.put("conditionValue", request.getParameter("conditionValue"));
		params.put("conditionType", request.getParameter("conditionType"));
		params.put("userId", userId);
		return companyApi.getCompanySalesByCondition(params);
	}
	
	@RequestMapping("/company/downloadSalarySocialSecurityTemplate")
	public ResponseEntity<byte[]> downloadSalarySocialSecurityTemplate(HttpServletRequest request) {
		String userId = (String) request.getSession().getAttribute("userId");
		String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/files/company");
		realPath = realPath.substring(0, realPath.indexOf("/", 1)) + "/files/company";
		ResponseEntity<byte[]> re = companyApi.downloadSalarySocialSecurityTemplate(userId, realPath);
		return re;
	}
	
	@RequestMapping("/company/getOrgStructureData")
	@ResponseBody
	public List<CompanyOrgStructure> getOrgStructureData(HttpServletRequest request) {
		String userId = (String) request.getSession().getAttribute("userId");
		return companyApi.getOrgStructureData(userId);
	}
	
	@RequestMapping("/company/getCompanyDetailInfo")
	@ResponseBody
	public Map<String, String> getCompanyDetailInfo(HttpServletRequest request) {
		String userId = (String) request.getSession().getAttribute("userId");
		return companyApi.getCompanyDetailInfo(userId);
	}
	
	@RequestMapping("/company/getAllCostCenterEncode")
	@ResponseBody
	public List<Map<String, String>> getAllCostCenterEncode() {
		return companyApi.getAllCostCenterCode();
	}
	
	@RequestMapping("/company/getAllUser")
	@ResponseBody
	public List<Map<String, String>> getAllUser(HttpServletRequest request) {
		String userId = (String) request.getSession().getAttribute("userId");
		long companyId = userService.getCompanyIdByUserId(userId);
		return companyApi.getAllUser(companyId);
	}
	
	@RequestMapping("/company/getSalaryTemplateHistory")
	@ResponseBody
	public List<Map<String, String>> getSalaryTemplateHistory(HttpServletRequest request) {
		String userId = (String) request.getSession().getAttribute("userId");
		long companyId = userService.getCompanyIdByUserId(userId);
		return companyApi.getSalaryTemplateHistory(companyId);
	}
	
	@RequestMapping("/company/getSalaryHistory")
	@ResponseBody
	public List<Map<String, String>> getSalaryHistory(HttpServletRequest request) {
		String userId = (String) request.getSession().getAttribute("userId");
		long companyId = userService.getCompanyIdByUserId(userId);
		return companyApi.getSalaryHistory(companyId);
	}
	
	@RequestMapping("/company/deleteCompanySales")
	@ResponseBody
	public Map<String, String> deleteCompanySales(
			HttpServletRequest request) {
		Map<String, String> retMap = new HashMap<>();
		String idList = (String)request.getParameter("deleteIdList");
		String realPath = request.getSession().getServletContext()
				.getRealPath("/WEB-INF/files/company");
		realPath = realPath.substring(0, realPath.indexOf("/", 1)) + "/files/company";
		if(companyApi.deleteCompanySales(idList, realPath)) {
			retMap.put("error_code", "0");
		} else {
			retMap.put("error_code", "1");
		}
		return retMap;
	}
	
	@RequestMapping("/company/deleteCompanySalary")
	@ResponseBody
	public Map<String, String> deleteCompanySalary(
			HttpServletRequest request) {
		Map<String, String> retMap = new HashMap<>();
		String idList = (String)request.getParameter("deleteIdList");
		String realPath = request.getSession().getServletContext()
				.getRealPath("/WEB-INF/files/company");
		realPath = realPath.substring(0, realPath.indexOf("/", 1)) + "/files/company";
		if(companyApi.deleteCompanySalary(idList, realPath)) {
			retMap.put("error_code", "0");
		} else {
			retMap.put("error_code", "1");
		}
		return retMap;
	}
	
	@RequestMapping("/company/setTakeType")
	@ResponseBody
	public Map<String, String> setTakeType(
			HttpServletRequest request) {
		Map<String, String> retMap = new HashMap<>();
		String userId = (String) request.getSession().getAttribute("userId");
		long companyId = userService.getCompanyIdByUserId(userId);
		String takeType = (String)request.getParameter("type");
		if(companyApi.setTakeType(companyId, takeType)) {
			retMap.put("error_code", "0");
		} else {
			retMap.put("error_code", "1");
		}
		return retMap;
	}
	
	@RequestMapping("/company/getSheetBalance")
	@ResponseBody
	public Map<String, String> getSheetBalance(
			HttpServletRequest request) {
		Map<String, String> retMap = new HashMap<>();
		retMap.put("result_is_null", "true");
		String userId = (String) request.getSession().getAttribute("userId");
		long companyId = userService.getCompanyIdByUserId(userId);
		String date = (String)request.getParameter("date");
		SheetBalance sheetBalance = sheetBalanceDao.getSheetBalanceDateByCompanyIdAndDate(companyId, date);
		if(sheetBalance != null) {
			retMap.put("result_is_null", "false");
			DecimalFormat format = new DecimalFormat("#0.000");
			retMap.put("expiry_date", sheetBalance.getExpiryDate());
			retMap.put("preparedby", sheetBalance.getPreparedby());
			retMap.put("company_controller", sheetBalance.getCompanyController());
			retMap.put("finance_chief", sheetBalance.getFinanceChief());
			retMap.put("tabulator", sheetBalance.getTabulator());
			retMap.put("create_time", sheetBalance.getCreateTime());
			retMap.put("monetary_resources_ending_balance", format.format(sheetBalance.getMonetaryResourcesEndingBalance() == null ? 0 : sheetBalance.getMonetaryResourcesEndingBalance()));
			retMap.put("monetary_resources_beginning_balance", format.format(sheetBalance.getMonetaryResourcesBeginningBalance() == null ? 0 : sheetBalance.getMonetaryResourcesBeginningBalance()));
			retMap.put("short_term_investments_ending_balance", format.format(sheetBalance.getShortTermInvestmentsEndingBalance() == null ? 0 : sheetBalance.getShortTermInvestmentsEndingBalance()));
			retMap.put("short_term_investments_beginning_balance", format.format(sheetBalance.getShortTermInvestmentsBeginningBalance() == null ? 0 : sheetBalance.getShortTermInvestmentsBeginningBalance()));
			retMap.put("bill_receivable_ending_balance", format.format(sheetBalance.getBillReceivableEndingBalance() == null ? 0 : sheetBalance.getBillReceivableEndingBalance()));
			retMap.put("bill_receivable_beginning_balance", format.format(sheetBalance.getBillReceivableBeginningBalance() == null ? 0 : sheetBalance.getBillReceivableBeginningBalance()));
			retMap.put("accounts_receivable_ending_balance", format.format(sheetBalance.getAccountsReceivableEndingBalance() == null ? 0 : sheetBalance.getAccountsReceivableEndingBalance()));
			retMap.put("accounts_receivable_beginning_balance", format.format(sheetBalance.getAccountsReceivableBeginningBalance() == null ? 0 : sheetBalance.getAccountsReceivableBeginningBalance()));
			retMap.put("prepayment_ending_balance", format.format(sheetBalance.getPrepaymentEndingBalance() == null ? 0 : sheetBalance.getPrepaymentEndingBalance()));
			retMap.put("prepayment_beginning_balance", format.format(sheetBalance.getPrepaymentBeginningBalance() == null ? 0 : sheetBalance.getPrepaymentBeginningBalance()));
			retMap.put("dividends_receivable_ending_balance", format.format(sheetBalance.getDividendsReceivableEndingBalance() == null ? 0 : sheetBalance.getDividendsReceivableEndingBalance()));
			retMap.put("dividends_receivable_beginning_balance", format.format(sheetBalance.getDividendsReceivableBeginningBalance() == null ? 0 : sheetBalance.getDividendsReceivableBeginningBalance()));
			retMap.put("interest_receivable_ending_balance", format.format(sheetBalance.getInterestReceivableEndingBalance() == null ? 0 : sheetBalance.getInterestReceivableEndingBalance()));
			retMap.put("interest_receivable_beginning_balance", format.format(sheetBalance.getInterestReceivableBeginningBalance() == null ? 0 : sheetBalance.getInterestReceivableBeginningBalance()));
			retMap.put("other_receivables_ending_balance", format.format(sheetBalance.getOtherReceivablesEndingBalance() == null ? 0 : sheetBalance.getOtherReceivablesEndingBalance()));
			retMap.put("other_receivables_beginning_balance", format.format(sheetBalance.getOtherReceivablesBeginningBalance() == null ? 0 : sheetBalance.getOtherReceivablesBeginningBalance()));
			retMap.put("stock_ending_balance", format.format(sheetBalance.getStockEndingBalance() == null ? 0 : sheetBalance.getStockEndingBalance()));
			retMap.put("stock_beginning_balance", format.format(sheetBalance.getStockBeginningBalance() == null ? 0 : sheetBalance.getStockBeginningBalance()));
			retMap.put("stock_raw_materials_ending_balance", format.format(sheetBalance.getStockRawMaterialsEndingBalance() == null ? 0 : sheetBalance.getStockRawMaterialsEndingBalance()));
			retMap.put("stock_raw_materials_beginning_balance", format.format(sheetBalance.getStockRawMaterialsBeginningBalance() == null ? 0 : sheetBalance.getStockRawMaterialsBeginningBalance()));
			retMap.put("stock_product_ending_balance", format.format(sheetBalance.getStockProductEndingBalance() == null ? 0 : sheetBalance.getStockProductEndingBalance()));
			retMap.put("stock_product_beginning_balance", format.format(sheetBalance.getStockProductBeginningBalance() == null ? 0 : sheetBalance.getStockProductBeginningBalance()));
			retMap.put("stock_merchandise_inventory_ending_balance", format.format(sheetBalance.getStockMerchandiseInventoryEndingBalance() == null ? 0 : sheetBalance.getStockMerchandiseInventoryEndingBalance()));
			retMap.put("stock_merchandise_inventory_beginning_balance", format.format(sheetBalance.getStockMerchandiseInventoryBeginningBalance() == null ? 0 : sheetBalance.getStockMerchandiseInventoryBeginningBalance()));
			retMap.put("stock_revolving_materials_ending_balance", format.format(sheetBalance.getStockRevolvingMaterialsEndingBalance() == null ? 0 : sheetBalance.getStockRevolvingMaterialsEndingBalance()));
			retMap.put("stock_revolving_materials_beginning_balance", format.format(sheetBalance.getStockRevolvingMaterialsBeginningBalance() == null ? 0 : sheetBalance.getStockRevolvingMaterialsBeginningBalance()));
			retMap.put("other_current_assets_ending_balance", format.format(sheetBalance.getOtherCurrentAssetsEndingBalance() == null ? 0 : sheetBalance.getOtherCurrentAssetsEndingBalance()));
			retMap.put("other_current_assets_beginning_balance", format.format(sheetBalance.getOtherCurrentAssetsBeginningBalance() == null ? 0 : sheetBalance.getOtherCurrentAssetsBeginningBalance()));
			retMap.put("total_current_assets_ending_balance", format.format(sheetBalance.getTotalCurrentAssetsEndingBalance() == null ? 0 : sheetBalance.getTotalCurrentAssetsEndingBalance()));
			retMap.put("total_current_assets_beginning_balance", format.format(sheetBalance.getTotalCurrentAssetsBeginningBalance() == null ? 0 : sheetBalance.getTotalCurrentAssetsBeginningBalance()));
			retMap.put("long_term_investments_in_bonds_ending_balance", format.format(sheetBalance.getLongTermInvestmentsInBondsEndingBalance() == null ? 0 : sheetBalance.getLongTermInvestmentsInBondsEndingBalance()));
			retMap.put("long_term_investments_in_bonds_beginning_balance", format.format(sheetBalance.getLongTermInvestmentsInBondsBeginningBalance() == null ? 0 : sheetBalance.getLongTermInvestmentsInBondsBeginningBalance()));
			retMap.put("long_term_investment_on_stocks_ending_balance", format.format(sheetBalance.getLongTermInvestmentOnStocksEndingBalance() == null ? 0 : sheetBalance.getLongTermInvestmentOnStocksEndingBalance()));
			retMap.put("long_term_investment_on_stocks_beginning_balance", format.format(sheetBalance.getLongTermInvestmentOnStocksBeginningBalance() == null ? 0 : sheetBalance.getLongTermInvestmentOnStocksBeginningBalance()));
			retMap.put("fixed_assets_original_cost_ending_balance", format.format(sheetBalance.getFixedAssetsOriginalCostEndingBalance() == null ? 0 : sheetBalance.getFixedAssetsOriginalCostEndingBalance()));
			retMap.put("fixed_assets_original_cost_beginning_balance", format.format(sheetBalance.getFixedAssetsOriginalCostBeginningBalance() == null ? 0 : sheetBalance.getFixedAssetsOriginalCostBeginningBalance()));
			retMap.put("accumulated_depreciation_ending_balance", format.format(sheetBalance.getAccumulatedDepreciationEndingBalance() == null ? 0 : sheetBalance.getAccumulatedDepreciationEndingBalance()));
			retMap.put("accumulated_depreciation_beginning_balance", format.format(sheetBalance.getAccumulatedDepreciationBeginningBalance() == null ? 0 : sheetBalance.getAccumulatedDepreciationBeginningBalance()));
			retMap.put("book_value_of_fixed_assets_ending_balance", format.format(sheetBalance.getBookValueOfFixedAssetsEndingBalance() == null ? 0 : sheetBalance.getBookValueOfFixedAssetsEndingBalance()));
			retMap.put("book_value_of_fixed_assets_beginning_balance", format.format(sheetBalance.getBookValueOfFixedAssetsBeginningBalance() == null ? 0 : sheetBalance.getBookValueOfFixedAssetsBeginningBalance()));
			retMap.put("construction_in_process_ending_balance", format.format(sheetBalance.getConstructionInProcessEndingBalance() == null ? 0 : sheetBalance.getConstructionInProcessEndingBalance()));
			retMap.put("construction_in_process_beginning_balance", format.format(sheetBalance.getConstructionInProcessBeginningBalance() == null ? 0 : sheetBalance.getConstructionInProcessBeginningBalance()));
			retMap.put("construction_materials_ending_balance", format.format(sheetBalance.getConstructionMaterialsEndingBalance() == null ? 0 : sheetBalance.getConstructionMaterialsEndingBalance()));
			retMap.put("construction_materials_beginning_balance", format.format(sheetBalance.getConstructionMaterialsBeginningBalance() == null ? 0 : sheetBalance.getConstructionMaterialsBeginningBalance()));
			retMap.put("removal_of_fixed_assets_ending_balance", format.format(sheetBalance.getRemovalOfFixedAssetsEndingBalance() == null ? 0 : sheetBalance.getRemovalOfFixedAssetsEndingBalance()));
			retMap.put("removal_of_fixed_assets_beginning_balance", format.format(sheetBalance.getRemovalOfFixedAssetsBeginningBalance() == null ? 0 : sheetBalance.getRemovalOfFixedAssetsBeginningBalance()));
			retMap.put("biological_assets_ending_balance", format.format(sheetBalance.getBiologicalAssetsEndingBalance() == null ? 0 : sheetBalance.getBiologicalAssetsEndingBalance()));
			retMap.put("biological_assets_beginning_balance", format.format(sheetBalance.getBiologicalAssetsBeginningBalance() == null ? 0 : sheetBalance.getBiologicalAssetsBeginningBalance()));
			retMap.put("intangible_assets_ending_balance", format.format(sheetBalance.getIntangibleAssetsEndingBalance() == null ? 0 : sheetBalance.getIntangibleAssetsEndingBalance()));
			retMap.put("intangible_assets_beginning_balance", format.format(sheetBalance.getIntangibleAssetsBeginningBalance() == null ? 0 : sheetBalance.getIntangibleAssetsBeginningBalance()));
			retMap.put("development_expenditure_ending_balance", format.format(sheetBalance.getDevelopmentExpenditureEndingBalance() == null ? 0 : sheetBalance.getDevelopmentExpenditureEndingBalance()));
			retMap.put("development_expenditure_beginning_balance", format.format(sheetBalance.getDevelopmentExpenditureBeginningBalance() == null ? 0 : sheetBalance.getDevelopmentExpenditureBeginningBalance()));
			retMap.put("long_term_prepaid_expenses_ending_balance", format.format(sheetBalance.getLongTermPrepaidExpensesEndingBalance() == null ? 0 : sheetBalance.getLongTermPrepaidExpensesEndingBalance()));
			retMap.put("long_term_prepaid_expenses_beginning_balance", format.format(sheetBalance.getLongTermPrepaidExpensesBeginningBalance() == null ? 0 : sheetBalance.getLongTermPrepaidExpensesBeginningBalance()));
			retMap.put("other_non_current_assets_ending_balance", format.format(sheetBalance.getOtherNonCurrentAssetsEndingBalance() == null ? 0 : sheetBalance.getOtherNonCurrentAssetsEndingBalance()));
			retMap.put("other_non_current_assets_beginning_balance", format.format(sheetBalance.getOtherNonCurrentAssetsBeginningBalance() == null ? 0 : sheetBalance.getOtherNonCurrentAssetsBeginningBalance()));
			retMap.put("total_non_current_assets_ending_balance", format.format(sheetBalance.getTotalNonCurrentAssetsEndingBalance() == null ? 0 : sheetBalance.getTotalNonCurrentAssetsEndingBalance()));
			retMap.put("total_non_current_assets_beginning_balance", format.format(sheetBalance.getTotalNonCurrentAssetsBeginningBalance() == null ? 0 : sheetBalance.getTotalNonCurrentAssetsBeginningBalance()));
			retMap.put("total_assets_ending_balance", format.format(sheetBalance.getTotalAssetsEndingBalance() == null ? 0 : sheetBalance.getTotalAssetsEndingBalance()));
			retMap.put("total_assets_beginning_balance", format.format(sheetBalance.getTotalAssetsBeginningBalance() == null ? 0 : sheetBalance.getTotalAssetsBeginningBalance()));
			retMap.put("short_loan_ending_balance", format.format(sheetBalance.getShortLoanEndingBalance() == null ? 0 : sheetBalance.getShortLoanEndingBalance()));
			retMap.put("short_loan_beginning_balance", format.format(sheetBalance.getShortLoanBeginningBalance() == null ? 0 : sheetBalance.getShortLoanBeginningBalance()));
			retMap.put("notes_payable_ending_balance", format.format(sheetBalance.getNotesPayableEndingBalance() == null ? 0 : sheetBalance.getNotesPayableEndingBalance()));
			retMap.put("notes_payable_beginning_balance", format.format(sheetBalance.getNotesPayableBeginningBalance() == null ? 0 : sheetBalance.getNotesPayableBeginningBalance()));
			retMap.put("payable_account_ending_balance", format.format(sheetBalance.getPayableAccountEndingBalance() == null ? 0 : sheetBalance.getPayableAccountEndingBalance()));
			retMap.put("payable_account_beginning_balance", format.format(sheetBalance.getPayableAccountBeginningBalance() == null ? 0 : sheetBalance.getPayableAccountBeginningBalance()));
			retMap.put("deposit_received_ending_balance", format.format(sheetBalance.getDepositReceivedEndingBalance() == null ? 0 : sheetBalance.getDepositReceivedEndingBalance()));
			retMap.put("deposit_received_beginning_balance", format.format(sheetBalance.getDepositReceivedBeginningBalance() == null ? 0 : sheetBalance.getDepositReceivedBeginningBalance()));
			retMap.put("employee_benefits_payable_ending_balance", format.format(sheetBalance.getEmployeeBenefitsPayableEndingBalance() == null ? 0 : sheetBalance.getEmployeeBenefitsPayableEndingBalance()));
			retMap.put("employee_benefits_payable_beginning_balance", format.format(sheetBalance.getEmployeeBenefitsPayableBeginningBalance() == null ? 0 : sheetBalance.getEmployeeBenefitsPayableBeginningBalance()));
			retMap.put("taxes_payable_ending_balance", format.format(sheetBalance.getTaxesPayableEndingBalance() == null ? 0 : sheetBalance.getTaxesPayableEndingBalance()));
			retMap.put("taxes_payable_beginning_balance", format.format(sheetBalance.getTaxesPayableBeginningBalance() == null ? 0 : sheetBalance.getTaxesPayableBeginningBalance()));
			retMap.put("interest_payable_ending_balance", format.format(sheetBalance.getInterestPayableEndingBalance() == null ? 0 : sheetBalance.getInterestPayableEndingBalance()));
			retMap.put("interest_payable_beginning_balance", format.format(sheetBalance.getInterestPayableBeginningBalance() == null ? 0 : sheetBalance.getInterestPayableBeginningBalance()));
			retMap.put("profit_payable_ending_balance", format.format(sheetBalance.getProfitPayableEndingBalance() == null ? 0 : sheetBalance.getProfitPayableEndingBalance()));
			retMap.put("profit_payable_beginning_balance", format.format(sheetBalance.getProfitPayableBeginningBalance() == null ? 0 : sheetBalance.getProfitPayableBeginningBalance()));
			retMap.put("accounts_payable_others_ending_balance", format.format(sheetBalance.getAccountsPayableOthersEndingBalance() == null ? 0 : sheetBalance.getAccountsPayableOthersEndingBalance()));
			retMap.put("accounts_payable_others_beginning_balance", format.format(sheetBalance.getAccountsPayableOthersBeginningBalance() == null ? 0 : sheetBalance.getAccountsPayableOthersBeginningBalance()));
			retMap.put("other_current_liabilities_ending_balance", format.format(sheetBalance.getOtherCurrentLiabilitiesEndingBalance() == null ? 0 : sheetBalance.getOtherCurrentLiabilitiesEndingBalance()));
			retMap.put("other_current_liabilities_beginning_balance", format.format(sheetBalance.getOtherCurrentLiabilitiesBeginningBalance() == null ? 0 : sheetBalance.getOtherCurrentLiabilitiesBeginningBalance()));
			retMap.put("total_current_liabilities_ending_balance", format.format(sheetBalance.getTotalCurrentLiabilitiesEndingBalance() == null ? 0 : sheetBalance.getTotalCurrentLiabilitiesEndingBalance()));
			retMap.put("total_current_liabilities_beginning_balance", format.format(sheetBalance.getTotalCurrentLiabilitiesBeginningBalance() == null ? 0 : sheetBalance.getTotalCurrentLiabilitiesBeginningBalance()));
			retMap.put("long_loan_ending_balance", format.format(sheetBalance.getLongLoanEndingBalance() == null ? 0 : sheetBalance.getLongLoanEndingBalance()));
			retMap.put("long_loan_beginning_balance", format.format(sheetBalance.getLongLoanBeginningBalance() == null ? 0 : sheetBalance.getLongLoanBeginningBalance()));
			retMap.put("long_term_payables_ending_balance", format.format(sheetBalance.getLongTermPayablesEndingBalance() == null ? 0 : sheetBalance.getLongTermPayablesEndingBalance()));
			retMap.put("long_term_payables_beginning_balance", format.format(sheetBalance.getLongTermPayablesBeginningBalance() == null ? 0 : sheetBalance.getLongTermPayablesBeginningBalance()));
			retMap.put("deferred_income_ending_balance", format.format(sheetBalance.getDeferredIncomeEndingBalance() == null ? 0 : sheetBalance.getDeferredIncomeEndingBalance()));
			retMap.put("deferred_income_beginning_balance", format.format(sheetBalance.getDeferredIncomeBeginningBalance() == null ? 0 : sheetBalance.getDeferredIncomeBeginningBalance()));
			retMap.put("other_non_current_liabilities_ending_balance", format.format(sheetBalance.getOtherNonCurrentLiabilitiesEndingBalance() == null ? 0 : sheetBalance.getOtherNonCurrentLiabilitiesEndingBalance()));
			retMap.put("other_non_current_liabilities_beginning_balance", format.format(sheetBalance.getOtherNonCurrentLiabilitiesBeginningBalance() == null ? 0 : sheetBalance.getOtherNonCurrentLiabilitiesBeginningBalance()));
			retMap.put("total_non_current_liabilities_ending_balance", format.format(sheetBalance.getTotalNonCurrentLiabilitiesEndingBalance() == null ? 0 : sheetBalance.getTotalNonCurrentLiabilitiesEndingBalance()));
			retMap.put("total_non_current_liabilities_beginning_balance", format.format(sheetBalance.getTotalNonCurrentLiabilitiesBeginningBalance() == null ? 0 : sheetBalance.getTotalNonCurrentLiabilitiesBeginningBalance()));
			retMap.put("total_liabilities_ending_balance", format.format(sheetBalance.getTotalLiabilitiesEndingBalance() == null ? 0 : sheetBalance.getTotalLiabilitiesEndingBalance()));
			retMap.put("total_liabilities_beginning_balance", format.format(sheetBalance.getTotalLiabilitiesBeginningBalance() == null ? 0 : sheetBalance.getTotalLiabilitiesBeginningBalance()));
			retMap.put("paid_in_capital_ending_balance", format.format(sheetBalance.getPaidInCapitalEndingBalance() == null ? 0 : sheetBalance.getPaidInCapitalEndingBalance()));
			retMap.put("paid_in_capital_beginning_balance", format.format(sheetBalance.getPaidInCapitalBeginningBalance() == null ? 0 : sheetBalance.getPaidInCapitalBeginningBalance()));
			retMap.put("capital_reserve_ending_balance", format.format(sheetBalance.getCapitalReserveEndingBalance() == null ? 0 : sheetBalance.getCapitalReserveEndingBalance()));
			retMap.put("capital_reserve_beginning_balance", format.format(sheetBalance.getCapitalReserveBeginningBalance() == null ? 0 : sheetBalance.getCapitalReserveBeginningBalance()));
			retMap.put("earned_surplus_ending_balance", format.format(sheetBalance.getEarnedSurplusEndingBalance() == null ? 0 : sheetBalance.getEarnedSurplusEndingBalance()));
			retMap.put("earned_surplus_beginning_balance", format.format(sheetBalance.getEarnedSurplusBeginningBalance() == null ? 0 : sheetBalance.getEarnedSurplusBeginningBalance()));
			retMap.put("undistributed_profit_ending_balance", format.format(sheetBalance.getUndistributedProfitEndingBalance() == null ? 0 : sheetBalance.getUndistributedProfitEndingBalance()));
			retMap.put("undistributed_profit_beginning_balance", format.format(sheetBalance.getUndistributedProfitBeginningBalance() == null ? 0 : sheetBalance.getUndistributedProfitBeginningBalance()));
			retMap.put("total_equities_ending_balance", format.format(sheetBalance.getTotalEquitiesEndingBalance() == null ? 0 : sheetBalance.getTotalEquitiesEndingBalance()));
			retMap.put("total_equities_beginning_balance", format.format(sheetBalance.getTotalEquitiesBeginningBalance() == null ? 0 : sheetBalance.getTotalEquitiesBeginningBalance()));
			retMap.put("total_liabilities_and_owner_equity_ending_balance", format.format(sheetBalance.getTotalLiabilitiesAndOwnerEquityEndingBalance() == null ? 0 : sheetBalance.getTotalLiabilitiesAndOwnerEquityEndingBalance()));
			retMap.put("total_liabilities_and_owner_equity_beginning_balance", format.format(sheetBalance.getTotalLiabilitiesAndOwnerEquityBeginningBalance() == null ? 0 : sheetBalance.getTotalLiabilitiesAndOwnerEquityBeginningBalance()));
		}
		return retMap;
	}
	
	@RequestMapping("/company/getSheetCash")
	@ResponseBody
	public Map<String, String> getSheetCash(
			HttpServletRequest request) {
		Map<String, String> retMap = new HashMap<>();
		retMap.put("result_is_null", "true");
		String userId = (String) request.getSession().getAttribute("userId");
		long companyId = userService.getCompanyIdByUserId(userId);
		String date = (String)request.getParameter("date");
		SheetCash sheetCash = sheetCashDao.getSheetCashDateByCompanyIdAndDate(companyId, date);
		if(sheetCash != null) {
			retMap.put("result_is_null", "false");
			DecimalFormat format = new DecimalFormat("#0.000");
			retMap.put("preparedby", sheetCash.getPreparedby());
			retMap.put("company_controller", sheetCash.getCompanyController());
			retMap.put("finance_chief", sheetCash.getFinanceChief());
			retMap.put("tabulator", sheetCash.getTabulator());
			retMap.put("create_time", sheetCash.getCreateTime());
			retMap.put("sales_year", format.format(sheetCash.getSalesYear() == null ? 0 : sheetCash.getSalesYear()));
			retMap.put("sales_month", format.format(sheetCash.getSalesMonth() == null ? 0 : sheetCash.getSalesMonth()));
			retMap.put("receive_year", format.format(sheetCash.getReceiveYear() == null ? 0 : sheetCash.getReceiveYear()));
			retMap.put("receive_month", format.format(sheetCash.getReceiveMonth() == null ? 0 : sheetCash.getReceiveMonth()));
			retMap.put("buy_year", format.format(sheetCash.getBuyYear() == null ? 0 : sheetCash.getBuyYear()));
			retMap.put("buy_month", format.format(sheetCash.getBuyMonth() == null ? 0 : sheetCash.getBuyMonth()));
			retMap.put("salary_year", format.format(sheetCash.getSalaryYear() == null ? 0 : sheetCash.getSalaryYear()));
			retMap.put("salary_month", format.format(sheetCash.getSalaryMonth() == null ? 0 : sheetCash.getSalaryMonth()));
			retMap.put("tax_year", format.format(sheetCash.getTaxYear() == null ? 0 : sheetCash.getTaxYear()));
			retMap.put("tax_month", format.format(sheetCash.getTaxMonth() == null ? 0 : sheetCash.getTaxMonth()));
			retMap.put("other_pay_year", format.format(sheetCash.getOtherPayYear() == null ? 0 : sheetCash.getOtherPayYear()));
			retMap.put("other_pay_month", format.format(sheetCash.getOtherPayMonth() == null ? 0 : sheetCash.getOtherPayMonth()));
			retMap.put("operating_activities_year", format.format(sheetCash.getOperatingActivitiesYear() == null ? 0 : sheetCash.getOperatingActivitiesYear()));
			retMap.put("operating_activities_month", format.format(sheetCash.getOperatingActivitiesMonth() == null ? 0 : sheetCash.getOperatingActivitiesMonth()));
			retMap.put("take_back_year", format.format(sheetCash.getTakeBackYear() == null ? 0 : sheetCash.getTakeBackYear()));
			retMap.put("take_back_month", format.format(sheetCash.getTakeBackMonth() == null ? 0 : sheetCash.getTakeBackMonth()));
			retMap.put("equity_earnings_year", format.format(sheetCash.getEquityEarningsYear() == null ? 0 : sheetCash.getEquityEarningsYear()));
			retMap.put("equity_earnings_month", format.format(sheetCash.getEquityEarningsMonth() == null ? 0 : sheetCash.getEquityEarningsMonth()));
			retMap.put("handle_year", format.format(sheetCash.getHandleYear() == null ? 0 : sheetCash.getHandleYear()));
			retMap.put("handle_month", format.format(sheetCash.getHandleMonth() == null ? 0 : sheetCash.getHandleMonth()));
			retMap.put("investments_pay_year", format.format(sheetCash.getInvestmentsPayYear() == null ? 0 : sheetCash.getInvestmentsPayYear()));
			retMap.put("investments_pay_month", format.format(sheetCash.getInvestmentsPayMonth() == null ? 0 : sheetCash.getInvestmentsPayMonth()));
			retMap.put("coustruction_year", format.format(sheetCash.getCoustructionYear() == null ? 0 : sheetCash.getCoustructionYear()));
			retMap.put("coustruction_month", format.format(sheetCash.getCoustructionMonth() == null ? 0 : sheetCash.getCoustructionMonth()));
			retMap.put("activity_investment_year", format.format(sheetCash.getActivityInvestmentYear() == null ? 0 : sheetCash.getActivityInvestmentYear()));
			retMap.put("activity_investment_month", format.format(sheetCash.getActivityInvestmentMonth() == null ? 0 : sheetCash.getActivityInvestmentMonth()));
			retMap.put("loan_year", format.format(sheetCash.getLoanYear() == null ? 0 : sheetCash.getLoanYear()));
			retMap.put("loan_month", format.format(sheetCash.getLoanMonth() == null ? 0 : sheetCash.getLoanMonth()));
			retMap.put("investors_to_invest_year", format.format(sheetCash.getInvestorsToInvestYear() == null ? 0 : sheetCash.getInvestorsToInvestYear()));
			retMap.put("investors_to_invest_month", format.format(sheetCash.getInvestorsToInvestMonth() == null ? 0 : sheetCash.getInvestorsToInvestMonth()));
			retMap.put("payment_of_principal_year", format.format(sheetCash.getPaymentOfPrincipalYear() == null ? 0 : sheetCash.getPaymentOfPrincipalYear()));
			retMap.put("payment_of_principal_month", format.format(sheetCash.getPaymentOfPrincipalMonth() == null ? 0 : sheetCash.getPaymentOfPrincipalMonth()));
			retMap.put("paid_interest_year", format.format(sheetCash.getPaidInterestYear() == null ? 0 : sheetCash.getPaidInterestYear()));
			retMap.put("paid_interest_month", format.format(sheetCash.getPaidInterestMonth() == null ? 0 : sheetCash.getPaidInterestMonth()));
			retMap.put("distribute_profit_year", format.format(sheetCash.getDistributeProfitYear() == null ? 0 : sheetCash.getDistributeProfitYear()));
			retMap.put("distribute_profit_month", format.format(sheetCash.getDistributeProfitMonth() == null ? 0 : sheetCash.getDistributeProfitMonth()));
			retMap.put("financing_activity_year", format.format(sheetCash.getFinancingActivityYear() == null ? 0 : sheetCash.getFinancingActivityYear()));
			retMap.put("financing_activity_month", format.format(sheetCash.getFinancingActivityMonth() == null ? 0 : sheetCash.getFinancingActivityMonth()));
			retMap.put("net_increase_in_cash_year", format.format(sheetCash.getNetIncreaseInCashYear() == null ? 0 : sheetCash.getNetIncreaseInCashYear()));
			retMap.put("net_increase_in_cash_month", format.format(sheetCash.getNetIncreaseInCashMonth() == null ? 0 : sheetCash.getNetIncreaseInCashMonth()));
			retMap.put("cash_at_beginning_of_period_year", format.format(sheetCash.getCashAtBeginningOfPeriodYear() == null ? 0 : sheetCash.getCashAtBeginningOfPeriodYear()));
			retMap.put("cash_at_beginning_of_period_month", format.format(sheetCash.getCashAtBeginningOfPeriodMonth() == null ? 0 : sheetCash.getCashAtBeginningOfPeriodMonth()));
			retMap.put("ending_cash_balance_year", format.format(sheetCash.getEndingCashBalanceYear() == null ? 0 : sheetCash.getEndingCashBalanceYear()));
			retMap.put("ending_cash_balance_month", format.format(sheetCash.getEndingCashBalanceMonth() == null ? 0 : sheetCash.getEndingCashBalanceMonth()));
		}
		return retMap;
	}
	
	@RequestMapping("/company/getSheetIncome")
	@ResponseBody
	public Map<String, String> getSheetIncome(
			HttpServletRequest request) {
		Map<String, String> retMap = new HashMap<>();
		retMap.put("result_is_null", "true");
		String userId = (String) request.getSession().getAttribute("userId");
		long companyId = userService.getCompanyIdByUserId(userId);
		String date = (String)request.getParameter("date");
		SheetIncome sheetIncome = sheetIncomeDao.getSheetIncomeDateByCompanyIdAndDate(companyId, date);
		if(sheetIncome != null) {
			retMap.put("result_is_null", "false");
			DecimalFormat format = new DecimalFormat("#0.000");
			retMap.put("preparedby", sheetIncome.getPreparedby());
			retMap.put("company_controller", sheetIncome.getCompanyController());
			retMap.put("finance_chief", sheetIncome.getFinanceChief());
			retMap.put("tabulator", sheetIncome.getTabulator());
			retMap.put("create_time", sheetIncome.getCreateTime());
			retMap.put("operating_receipt_year", format.format(sheetIncome.getOperatingReceiptYear() == null ? 0 : sheetIncome.getOperatingReceiptYear()));
			retMap.put("operating_receipt_month", format.format(sheetIncome.getOperatingReceiptMonth() == null ? 0 : sheetIncome.getOperatingReceiptMonth()));
			retMap.put("cost_in_business_year", format.format(sheetIncome.getCostInBusinessYear() == null ? 0 : sheetIncome.getCostInBusinessYear()));
			retMap.put("cost_in_business_month", format.format(sheetIncome.getCostInBusinessMonth() == null ? 0 : sheetIncome.getCostInBusinessMonth()));
			retMap.put("business_tariff_and_annex_year", format.format(sheetIncome.getBusinessTariffAndAnnexYear() == null ? 0 : sheetIncome.getBusinessTariffAndAnnexYear()));
			retMap.put("business_tariff_and_annex_month", format.format(sheetIncome.getBusinessTariffAndAnnexMonth() == null ? 0 : sheetIncome.getBusinessTariffAndAnnexMonth()));
			retMap.put("excise_tax_year", format.format(sheetIncome.getExciseTaxYear() == null ? 0 : sheetIncome.getExciseTaxYear()));
			retMap.put("excise_tax_month", format.format(sheetIncome.getExciseTaxMonth() == null ? 0 : sheetIncome.getExciseTaxMonth()));
			retMap.put("sales_tax_year", format.format(sheetIncome.getSalesTaxYear() == null ? 0 : sheetIncome.getSalesTaxYear()));
			retMap.put("sales_tax_month", format.format(sheetIncome.getSalesTaxMonth() == null ? 0 : sheetIncome.getSalesTaxMonth()));
			retMap.put("urban_maintenance_and_construction_tax_year", format.format(sheetIncome.getUrbanMaintenanceAndConstructionTaxYear() == null ? 0 : sheetIncome.getUrbanMaintenanceAndConstructionTaxYear()));
			retMap.put("urban_maintenance_and_construction_month", format.format(sheetIncome.getUrbanMaintenanceAndConstructionMonth() == null ? 0 : sheetIncome.getUrbanMaintenanceAndConstructionMonth()));
			retMap.put("resource_tax_year", format.format(sheetIncome.getResourceTaxYear() == null ? 0 : sheetIncome.getResourceTaxYear()));
			retMap.put("resource_tax_month", format.format(sheetIncome.getResourceTaxMonth() == null ? 0 : sheetIncome.getResourceTaxMonth()));
			retMap.put("land_value_increment_tax_year", format.format(sheetIncome.getLandValueIncrementTaxYear() == null ? 0 : sheetIncome.getLandValueIncrementTaxYear()));
			retMap.put("land_value_increment_tax_month", format.format(sheetIncome.getLandValueIncrementTaxMonth() == null ? 0 : sheetIncome.getLandValueIncrementTaxMonth()));
			retMap.put("land_house_tax_year", format.format(sheetIncome.getLandHouseTaxYear() == null ? 0 : sheetIncome.getLandHouseTaxYear()));
			retMap.put("land_house_tax_month", format.format(sheetIncome.getLandHouseTaxMonth() == null ? 0 : sheetIncome.getLandHouseTaxMonth()));
			retMap.put("education_year", format.format(sheetIncome.getEducationYear() == null ? 0 : sheetIncome.getEducationYear()));
			retMap.put("education_month", format.format(sheetIncome.getEducationMonth() == null ? 0 : sheetIncome.getEducationMonth()));
			retMap.put("sales_year", format.format(sheetIncome.getSalesYear() == null ? 0 : sheetIncome.getSalesYear()));
			retMap.put("sales_month", format.format(sheetIncome.getSalesMonth() == null ? 0 : sheetIncome.getSalesMonth()));
			retMap.put("maintenance_year", format.format(sheetIncome.getMaintenanceYear() == null ? 0 : sheetIncome.getMaintenanceYear()));
			retMap.put("maintenance_month", format.format(sheetIncome.getMaintenanceMonth() == null ? 0 : sheetIncome.getMaintenanceMonth()));
			retMap.put("advertise_year", format.format(sheetIncome.getAdvertiseYear() == null ? 0 : sheetIncome.getAdvertiseYear()));
			retMap.put("advertise_month", format.format(sheetIncome.getAdvertiseMonth() == null ? 0 : sheetIncome.getAdvertiseMonth()));
			retMap.put("manage_year", format.format(sheetIncome.getManageYear() == null ? 0 : sheetIncome.getManageYear()));
			retMap.put("manage_month", format.format(sheetIncome.getManageMonth() == null ? 0 : sheetIncome.getManageMonth()));
			retMap.put("establishment_charges_year", format.format(sheetIncome.getEstablishmentChargesYear() == null ? 0 : sheetIncome.getEstablishmentChargesYear()));
			retMap.put("establishment_charges_month", format.format(sheetIncome.getEstablishmentChargesMonth() == null ? 0 : sheetIncome.getEstablishmentChargesMonth()));
			retMap.put("business_entertainment_year", format.format(sheetIncome.getBusinessEntertainmentYear() == null ? 0 : sheetIncome.getBusinessEntertainmentYear()));
			retMap.put("business_entertainment_month", format.format(sheetIncome.getBusinessEntertainmentMonth() == null ? 0 : sheetIncome.getBusinessEntertainmentMonth()));
			retMap.put("research_year", format.format(sheetIncome.getResearchYear() == null ? 0 : sheetIncome.getResearchYear()));
			retMap.put("research_month", format.format(sheetIncome.getResearchMonth() == null ? 0 : sheetIncome.getResearchMonth()));
			retMap.put("finance_year", format.format(sheetIncome.getFinanceYear() == null ? 0 : sheetIncome.getFinanceYear()));
			retMap.put("finance_month", format.format(sheetIncome.getFinanceMonth() == null ? 0 : sheetIncome.getFinanceMonth()));
			retMap.put("interest_year", format.format(sheetIncome.getInterestYear() == null ? 0 : sheetIncome.getInterestYear()));
			retMap.put("interest_month", format.format(sheetIncome.getInterestMonth() == null ? 0 : sheetIncome.getInterestMonth()));
			retMap.put("equity_earnings_year", format.format(sheetIncome.getEquityEarningsYear() == null ? 0 : sheetIncome.getEquityEarningsYear()));
			retMap.put("equity_earnings_month", format.format(sheetIncome.getEquityEarningsMonth() == null ? 0 : sheetIncome.getEquityEarningsMonth()));
			retMap.put("operating_profit_year", format.format(sheetIncome.getOperatingProfitYear() == null ? 0 : sheetIncome.getOperatingProfitYear()));
			retMap.put("operating_profit_month", format.format(sheetIncome.getOperatingProfitMonth() == null ? 0 : sheetIncome.getOperatingProfitMonth()));
			retMap.put("nonbusiness_income_year", format.format(sheetIncome.getNonbusinessIncomeYear() == null ? 0 : sheetIncome.getNonbusinessIncomeYear()));
			retMap.put("nonbusiness_income_month", format.format(sheetIncome.getNonbusinessIncomeMonth() == null ? 0 : sheetIncome.getNonbusinessIncomeMonth()));
			retMap.put("government_grants_year", format.format(sheetIncome.getGovernmentGrantsYear() == null ? 0 : sheetIncome.getGovernmentGrantsYear()));
			retMap.put("government_grants_month", format.format(sheetIncome.getGovernmentGrantsMonth() == null ? 0 : sheetIncome.getGovernmentGrantsMonth()));
			retMap.put("non_business_expenditure_year", format.format(sheetIncome.getNonBusinessExpenditureYear() == null ? 0 : sheetIncome.getNonBusinessExpenditureYear()));
			retMap.put("non_business_expenditure_month", format.format(sheetIncome.getNonBusinessExpenditureMonth() == null ? 0 : sheetIncome.getNonBusinessExpenditureMonth()));
			retMap.put("loss_on_bad_debt_year", format.format(sheetIncome.getLossOnBadDebtYear() == null ? 0 : sheetIncome.getLossOnBadDebtYear()));
			retMap.put("loss_on_bad_debt_month", format.format(sheetIncome.getLossOnBadDebtMonth() == null ? 0 : sheetIncome.getLossOnBadDebtMonth()));
			retMap.put("loss_on_long_term_bonds_year", format.format(sheetIncome.getLossOnLongTermBondsYear() == null ? 0 : sheetIncome.getLossOnLongTermBondsYear()));
			retMap.put("loss_on_long_term_bonds_month", format.format(sheetIncome.getLossOnLongTermBondsMonth() == null ? 0 : sheetIncome.getLossOnLongTermBondsMonth()));
			retMap.put("loss_on_long_term_stock_year", format.format(sheetIncome.getLossOnLongTermStockYear() == null ? 0 : sheetIncome.getLossOnLongTermStockYear()));
			retMap.put("loss_on_long_term_stock_month", format.format(sheetIncome.getLossOnLongTermStockMonth() == null ? 0 : sheetIncome.getLossOnLongTermStockMonth()));
			retMap.put("loss_on_long_term_natural_calamities_year", format.format(sheetIncome.getLossOnLongTermNaturalCalamitiesYear() == null ? 0 : sheetIncome.getLossOnLongTermNaturalCalamitiesYear()));
			retMap.put("loss_on_long_term_natural_calamities_month", format.format(sheetIncome.getLossOnLongTermNaturalCalamitiesMonth() == null ? 0 : sheetIncome.getLossOnLongTermNaturalCalamitiesMonth()));
			retMap.put("tax_delay_charge_year", format.format(sheetIncome.getTaxDelayChargeYear() == null ? 0 : sheetIncome.getTaxDelayChargeYear()));
			retMap.put("tax_delay_charge_month", format.format(sheetIncome.getTaxDelayChargeMonth() == null ? 0 : sheetIncome.getTaxDelayChargeMonth()));
			retMap.put("total_profit_year", format.format(sheetIncome.getTotalProfitYear() == null ? 0 : sheetIncome.getTotalProfitYear()));
			retMap.put("total_profit_month", format.format(sheetIncome.getTotalProfitMonth() == null ? 0 : sheetIncome.getTotalProfitMonth()));
			retMap.put("income_tax_expense_year", format.format(sheetIncome.getIncomeTaxExpenseYear() == null ? 0 : sheetIncome.getIncomeTaxExpenseYear()));
			retMap.put("income_tax_expense_month", format.format(sheetIncome.getIncomeTaxExpenseMonth() == null ? 0 : sheetIncome.getIncomeTaxExpenseMonth()));
			retMap.put("net_margin_year", format.format(sheetIncome.getNetMarginYear() == null ? 0 : sheetIncome.getNetMarginYear()));
			retMap.put("net_margin_month", format.format(sheetIncome.getNetMarginMonth() == null ? 0 : sheetIncome.getNetMarginMonth()));
		}
		return retMap;
	}
	
	@RequestMapping("/company/getSheetSalaryTax")
	@ResponseBody
	public Map<String, String> getSheetSalaryTax(
			HttpServletRequest request) {
		Map<String, String> retMap = new HashMap<>();
		retMap.put("result_is_null", "true");
		String userId = (String) request.getSession().getAttribute("userId");
		long companyId = userService.getCompanyIdByUserId(userId);
		String date = (String)request.getParameter("date");
		SheetSalaryTax sheetSalaryTax = sheetSalaryTaxDao.getSheetSalaryTaxDateByCompanyIdAndDate(companyId, date);
		if(sheetSalaryTax != null) {
			retMap.put("result_is_null", "false");
			DecimalFormat format = new DecimalFormat("#0.000");
			retMap.put("preparedby", sheetSalaryTax.getPreparedby());
			retMap.put("company_controller", sheetSalaryTax.getCompanyController());
			retMap.put("finance_chief", sheetSalaryTax.getFinanceChief());
			retMap.put("tabulator", sheetSalaryTax.getTabulator());
			retMap.put("create_time", sheetSalaryTax.getCreateTime());
			retMap.put("salery_end", format.format(sheetSalaryTax.getSaleryEnd() == null ? 0 : sheetSalaryTax.getSaleryEnd()));
			retMap.put("salery_begin", format.format(sheetSalaryTax.getSaleryBegin() == null ? 0 : sheetSalaryTax.getSaleryBegin()));
			retMap.put("bonus_end", format.format(sheetSalaryTax.getBonusEnd() == null ? 0 : sheetSalaryTax.getBonusEnd()));
			retMap.put("bonus_begin", format.format(sheetSalaryTax.getBonusBegin() == null ? 0 : sheetSalaryTax.getBonusBegin()));
			retMap.put("welfare_end", format.format(sheetSalaryTax.getWelfareEnd() == null ? 0 : sheetSalaryTax.getWelfareEnd()));
			retMap.put("welfare_begin", format.format(sheetSalaryTax.getWelfareBegin() == null ? 0 : sheetSalaryTax.getWelfareBegin()));
			retMap.put("insurance_end", format.format(sheetSalaryTax.getInsuranceEnd() == null ? 0 : sheetSalaryTax.getInsuranceEnd()));
			retMap.put("insurance_begin", format.format(sheetSalaryTax.getInsuranceBegin() == null ? 0 : sheetSalaryTax.getInsuranceBegin()));
			retMap.put("funds_end", format.format(sheetSalaryTax.getFundsEnd() == null ? 0 : sheetSalaryTax.getFundsEnd()));
			retMap.put("funds_begin", format.format(sheetSalaryTax.getFundsBegin() == null ? 0 : sheetSalaryTax.getFundsBegin()));
			retMap.put("sociaty_end", format.format(sheetSalaryTax.getSociatyEnd() == null ? 0 : sheetSalaryTax.getSociatyEnd()));
			retMap.put("sociaty_begin", format.format(sheetSalaryTax.getSociatyBegin() == null ? 0 : sheetSalaryTax.getSociatyBegin()));
			retMap.put("education_end", format.format(sheetSalaryTax.getEducationEnd() == null ? 0 : sheetSalaryTax.getEducationEnd()));
			retMap.put("education_begin", format.format(sheetSalaryTax.getEducationBegin() == null ? 0 : sheetSalaryTax.getEducationBegin()));
			retMap.put("no_cash_welfare_end", format.format(sheetSalaryTax.getNoCashWelfareEnd() == null ? 0 : sheetSalaryTax.getNoCashWelfareEnd()));
			retMap.put("no_cash_welfare_begin", format.format(sheetSalaryTax.getNoCashWelfareBegin() == null ? 0 : sheetSalaryTax.getNoCashWelfareBegin()));
			retMap.put("dismiss_end", format.format(sheetSalaryTax.getDismissEnd() == null ? 0 : sheetSalaryTax.getDismissEnd()));
			retMap.put("dismiss_begin", format.format(sheetSalaryTax.getDismissBegin() == null ? 0 : sheetSalaryTax.getDismissBegin()));
			retMap.put("other_end", format.format(sheetSalaryTax.getOtherEnd() == null ? 0 : sheetSalaryTax.getOtherEnd()));
			retMap.put("other_begin", format.format(sheetSalaryTax.getOtherBegin() == null ? 0 : sheetSalaryTax.getOtherBegin()));
			retMap.put("salary_total_end", format.format(sheetSalaryTax.getSalaryTotalEnd() == null ? 0 : sheetSalaryTax.getSalaryTotalEnd()));
			retMap.put("salary_total_begin", format.format(sheetSalaryTax.getSalaryTotalBegin() == null ? 0 : sheetSalaryTax.getSalaryTotalBegin()));
			retMap.put("value_added_tax_end", format.format(sheetSalaryTax.getValueAddedTaxEnd() == null ? 0 : sheetSalaryTax.getValueAddedTaxEnd()));
			retMap.put("value_added_tax_begin", format.format(sheetSalaryTax.getValueAddedTaxBegin() == null ? 0 : sheetSalaryTax.getValueAddedTaxBegin()));
			retMap.put("excise_tax_end", format.format(sheetSalaryTax.getExciseTaxEnd() == null ? 0 : sheetSalaryTax.getExciseTaxEnd()));
			retMap.put("excise_tax_begin", format.format(sheetSalaryTax.getExciseTaxBegin() == null ? 0 : sheetSalaryTax.getExciseTaxBegin()));
			retMap.put("business_tax_end", format.format(sheetSalaryTax.getBusinessTaxEnd() == null ? 0 : sheetSalaryTax.getBusinessTaxEnd()));
			retMap.put("business_tax_begin", format.format(sheetSalaryTax.getBusinessTaxBegin() == null ? 0 : sheetSalaryTax.getBusinessTaxBegin()));
			retMap.put("urban_maintenance_and_construction_tax_end", format.format(sheetSalaryTax.getUrbanMaintenanceAndConstructionTaxEnd() == null ? 0 : sheetSalaryTax.getUrbanMaintenanceAndConstructionTaxEnd()));
			retMap.put("urban_maintenance_and_construction_tax_begin", format.format(sheetSalaryTax.getUrbanMaintenanceAndConstructionTaxBegin() == null ? 0 : sheetSalaryTax.getUrbanMaintenanceAndConstructionTaxBegin()));
			retMap.put("business_income_taxes_end", format.format(sheetSalaryTax.getBusinessIncomeTaxesEnd() == null ? 0 : sheetSalaryTax.getBusinessIncomeTaxesEnd()));
			retMap.put("business_income_taxes_begin", format.format(sheetSalaryTax.getBusinessIncomeTaxesBegin() == null ? 0 : sheetSalaryTax.getBusinessIncomeTaxesBegin()));
			retMap.put("resource_tax_end", format.format(sheetSalaryTax.getResourceTaxEnd() == null ? 0 : sheetSalaryTax.getResourceTaxEnd()));
			retMap.put("resource_tax_begin", format.format(sheetSalaryTax.getResourceTaxBegin() == null ? 0 : sheetSalaryTax.getResourceTaxBegin()));
			retMap.put("land_value_increment_tax_end", format.format(sheetSalaryTax.getLandValueIncrementTaxEnd() == null ? 0 : sheetSalaryTax.getLandValueIncrementTaxEnd()));
			retMap.put("land_value_increment_tax_begin", format.format(sheetSalaryTax.getLandValueIncrementTaxBegin() == null ? 0 : sheetSalaryTax.getLandValueIncrementTaxBegin()));
			retMap.put("urban_land_use_tax_end", format.format(sheetSalaryTax.getUrbanLandUseTaxEnd() == null ? 0 : sheetSalaryTax.getUrbanLandUseTaxEnd()));
			retMap.put("urban_land_use_tax_begin", format.format(sheetSalaryTax.getUrbanLandUseTaxBegin() == null ? 0 : sheetSalaryTax.getUrbanLandUseTaxBegin()));
			retMap.put("building_taxes_end", format.format(sheetSalaryTax.getBuildingTaxesEnd() == null ? 0 : sheetSalaryTax.getBuildingTaxesEnd()));
			retMap.put("building_taxes_begin", format.format(sheetSalaryTax.getBuildingTaxesBegin() == null ? 0 : sheetSalaryTax.getBuildingTaxesBegin()));
			retMap.put("vehicle_and_vessel_tax_end", format.format(sheetSalaryTax.getVehicleAndVesselTaxEnd() == null ? 0 : sheetSalaryTax.getVehicleAndVesselTaxEnd()));
			retMap.put("vehicle_and_vessel_tax_begin", format.format(sheetSalaryTax.getVehicleAndVesselTaxBegin() == null ? 0 : sheetSalaryTax.getVehicleAndVesselTaxBegin()));
			retMap.put("attach_education_end", format.format(sheetSalaryTax.getAttachEducationEnd() == null ? 0 : sheetSalaryTax.getAttachEducationEnd()));
			retMap.put("attach_education_begin", format.format(sheetSalaryTax.getAttachEducationBegin() == null ? 0 : sheetSalaryTax.getAttachEducationBegin()));
			retMap.put("mineral_resources_end", format.format(sheetSalaryTax.getMineralResourcesEnd() == null ? 0 : sheetSalaryTax.getMineralResourcesEnd()));
			retMap.put("mineral_resources_begin", format.format(sheetSalaryTax.getMineralResourcesBegin() == null ? 0 : sheetSalaryTax.getMineralResourcesBegin()));
			retMap.put("sewage_charge_end", format.format(sheetSalaryTax.getSewageChargeEnd() == null ? 0 : sheetSalaryTax.getSewageChargeEnd()));
			retMap.put("sewage_charge_begin", format.format(sheetSalaryTax.getSewageChargeBegin() == null ? 0 : sheetSalaryTax.getSewageChargeBegin()));
			retMap.put("income_tax_for_individuals_end", format.format(sheetSalaryTax.getIncomeTaxForIndividualsEnd() == null ? 0 : sheetSalaryTax.getIncomeTaxForIndividualsEnd()));
			retMap.put("income_tax_for_individuals_begin", format.format(sheetSalaryTax.getIncomeTaxForIndividualsBegin() == null ? 0 : sheetSalaryTax.getIncomeTaxForIndividualsBegin()));
			retMap.put("tax_total_end", format.format(sheetSalaryTax.getTaxTotalEnd() == null ? 0 : sheetSalaryTax.getTaxTotalEnd()));
			retMap.put("tax_total_begin", format.format(sheetSalaryTax.getTaxTotalBegin() == null ? 0 : sheetSalaryTax.getTaxTotalBegin()));
		}
		return retMap;
	}
	
	@RequestMapping("/getNewestSheetBalance")
	@ResponseBody
	public Map<String, String> getNewestSheetBalance(
			HttpServletRequest request) {
		Map<String, String> retMap = new HashMap<>();
		retMap.put("result_is_null", "true");
		retMap.put("openid_exist", "true");
		String date = (String)request.getParameter("date");
		String userOpenId = (String)request.getParameter("userOpenId");
		logger.debug("getNewestSheetBalance  userOpenId : {}", userOpenId);
		UserOpenid uod = userOpenIdDao.getUserOpenidByOpenId(userOpenId);
		logger.debug("getNewestSheetBalance  uod : {}", uod);
		if(uod == null) {
			retMap.put("openid_exist", "false");
			return retMap;
		}
		long companyId = userService.getCompanyIdByUserId(uod.getUserId());
		logger.debug("getNewestSheetBalance  date : {}", date);
		logger.debug("getNewestSheetBalance  companyId : {}", companyId);
		SheetBalance sheetBalance = sheetBalanceDao.getSheetBalanceDateByCompanyIdAndDate(companyId, date);
		logger.debug("getNewestSheetBalance  sheetBalance : {}", sheetBalance);
		if(sheetBalance != null) {
			retMap.put("result_is_null", "false");
			DecimalFormat format = new DecimalFormat("#0.000");
			retMap.put("expiry_date", sheetBalance.getExpiryDate());
			retMap.put("preparedby", sheetBalance.getPreparedby());
			retMap.put("company_controller", sheetBalance.getCompanyController());
			retMap.put("finance_chief", sheetBalance.getFinanceChief());
			retMap.put("tabulator", sheetBalance.getTabulator());
			retMap.put("create_time", sheetBalance.getCreateTime());
			retMap.put("monetary_resources_ending_balance", format.format(sheetBalance.getMonetaryResourcesEndingBalance() == null ? 0 : sheetBalance.getMonetaryResourcesEndingBalance()));
			retMap.put("monetary_resources_beginning_balance", format.format(sheetBalance.getMonetaryResourcesBeginningBalance() == null ? 0 : sheetBalance.getMonetaryResourcesBeginningBalance()));
			retMap.put("short_term_investments_ending_balance", format.format(sheetBalance.getShortTermInvestmentsEndingBalance() == null ? 0 : sheetBalance.getShortTermInvestmentsEndingBalance()));
			retMap.put("short_term_investments_beginning_balance", format.format(sheetBalance.getShortTermInvestmentsBeginningBalance() == null ? 0 : sheetBalance.getShortTermInvestmentsBeginningBalance()));
			retMap.put("bill_receivable_ending_balance", format.format(sheetBalance.getBillReceivableEndingBalance() == null ? 0 : sheetBalance.getBillReceivableEndingBalance()));
			retMap.put("bill_receivable_beginning_balance", format.format(sheetBalance.getBillReceivableBeginningBalance() == null ? 0 : sheetBalance.getBillReceivableBeginningBalance()));
			retMap.put("accounts_receivable_ending_balance", format.format(sheetBalance.getAccountsReceivableEndingBalance() == null ? 0 : sheetBalance.getAccountsReceivableEndingBalance()));
			retMap.put("accounts_receivable_beginning_balance", format.format(sheetBalance.getAccountsReceivableBeginningBalance() == null ? 0 : sheetBalance.getAccountsReceivableBeginningBalance()));
			retMap.put("prepayment_ending_balance", format.format(sheetBalance.getPrepaymentEndingBalance() == null ? 0 : sheetBalance.getPrepaymentEndingBalance()));
			retMap.put("prepayment_beginning_balance", format.format(sheetBalance.getPrepaymentBeginningBalance() == null ? 0 : sheetBalance.getPrepaymentBeginningBalance()));
			retMap.put("dividends_receivable_ending_balance", format.format(sheetBalance.getDividendsReceivableEndingBalance() == null ? 0 : sheetBalance.getDividendsReceivableEndingBalance()));
			retMap.put("dividends_receivable_beginning_balance", format.format(sheetBalance.getDividendsReceivableBeginningBalance() == null ? 0 : sheetBalance.getDividendsReceivableBeginningBalance()));
			retMap.put("interest_receivable_ending_balance", format.format(sheetBalance.getInterestReceivableEndingBalance() == null ? 0 : sheetBalance.getInterestReceivableEndingBalance()));
			retMap.put("interest_receivable_beginning_balance", format.format(sheetBalance.getInterestReceivableBeginningBalance() == null ? 0 : sheetBalance.getInterestReceivableBeginningBalance()));
			retMap.put("other_receivables_ending_balance", format.format(sheetBalance.getOtherReceivablesEndingBalance() == null ? 0 : sheetBalance.getOtherReceivablesEndingBalance()));
			retMap.put("other_receivables_beginning_balance", format.format(sheetBalance.getOtherReceivablesBeginningBalance() == null ? 0 : sheetBalance.getOtherReceivablesBeginningBalance()));
			retMap.put("stock_ending_balance", format.format(sheetBalance.getStockEndingBalance() == null ? 0 : sheetBalance.getStockEndingBalance()));
			retMap.put("stock_beginning_balance", format.format(sheetBalance.getStockBeginningBalance() == null ? 0 : sheetBalance.getStockBeginningBalance()));
			retMap.put("stock_raw_materials_ending_balance", format.format(sheetBalance.getStockRawMaterialsEndingBalance() == null ? 0 : sheetBalance.getStockRawMaterialsEndingBalance()));
			retMap.put("stock_raw_materials_beginning_balance", format.format(sheetBalance.getStockRawMaterialsBeginningBalance() == null ? 0 : sheetBalance.getStockRawMaterialsBeginningBalance()));
			retMap.put("stock_product_ending_balance", format.format(sheetBalance.getStockProductEndingBalance() == null ? 0 : sheetBalance.getStockProductEndingBalance()));
			retMap.put("stock_product_beginning_balance", format.format(sheetBalance.getStockProductBeginningBalance() == null ? 0 : sheetBalance.getStockProductBeginningBalance()));
			retMap.put("stock_merchandise_inventory_ending_balance", format.format(sheetBalance.getStockMerchandiseInventoryEndingBalance() == null ? 0 : sheetBalance.getStockMerchandiseInventoryEndingBalance()));
			retMap.put("stock_merchandise_inventory_beginning_balance", format.format(sheetBalance.getStockMerchandiseInventoryBeginningBalance() == null ? 0 : sheetBalance.getStockMerchandiseInventoryBeginningBalance()));
			retMap.put("stock_revolving_materials_ending_balance", format.format(sheetBalance.getStockRevolvingMaterialsEndingBalance() == null ? 0 : sheetBalance.getStockRevolvingMaterialsEndingBalance()));
			retMap.put("stock_revolving_materials_beginning_balance", format.format(sheetBalance.getStockRevolvingMaterialsBeginningBalance() == null ? 0 : sheetBalance.getStockRevolvingMaterialsBeginningBalance()));
			retMap.put("other_current_assets_ending_balance", format.format(sheetBalance.getOtherCurrentAssetsEndingBalance() == null ? 0 : sheetBalance.getOtherCurrentAssetsEndingBalance()));
			retMap.put("other_current_assets_beginning_balance", format.format(sheetBalance.getOtherCurrentAssetsBeginningBalance() == null ? 0 : sheetBalance.getOtherCurrentAssetsBeginningBalance()));
			retMap.put("total_current_assets_ending_balance", format.format(sheetBalance.getTotalCurrentAssetsEndingBalance() == null ? 0 : sheetBalance.getTotalCurrentAssetsEndingBalance()));
			retMap.put("total_current_assets_beginning_balance", format.format(sheetBalance.getTotalCurrentAssetsBeginningBalance() == null ? 0 : sheetBalance.getTotalCurrentAssetsBeginningBalance()));
			retMap.put("long_term_investments_in_bonds_ending_balance", format.format(sheetBalance.getLongTermInvestmentsInBondsEndingBalance() == null ? 0 : sheetBalance.getLongTermInvestmentsInBondsEndingBalance()));
			retMap.put("long_term_investments_in_bonds_beginning_balance", format.format(sheetBalance.getLongTermInvestmentsInBondsBeginningBalance() == null ? 0 : sheetBalance.getLongTermInvestmentsInBondsBeginningBalance()));
			retMap.put("long_term_investment_on_stocks_ending_balance", format.format(sheetBalance.getLongTermInvestmentOnStocksEndingBalance() == null ? 0 : sheetBalance.getLongTermInvestmentOnStocksEndingBalance()));
			retMap.put("long_term_investment_on_stocks_beginning_balance", format.format(sheetBalance.getLongTermInvestmentOnStocksBeginningBalance() == null ? 0 : sheetBalance.getLongTermInvestmentOnStocksBeginningBalance()));
			retMap.put("fixed_assets_original_cost_ending_balance", format.format(sheetBalance.getFixedAssetsOriginalCostEndingBalance() == null ? 0 : sheetBalance.getFixedAssetsOriginalCostEndingBalance()));
			retMap.put("fixed_assets_original_cost_beginning_balance", format.format(sheetBalance.getFixedAssetsOriginalCostBeginningBalance() == null ? 0 : sheetBalance.getFixedAssetsOriginalCostBeginningBalance()));
			retMap.put("accumulated_depreciation_ending_balance", format.format(sheetBalance.getAccumulatedDepreciationEndingBalance() == null ? 0 : sheetBalance.getAccumulatedDepreciationEndingBalance()));
			retMap.put("accumulated_depreciation_beginning_balance", format.format(sheetBalance.getAccumulatedDepreciationBeginningBalance() == null ? 0 : sheetBalance.getAccumulatedDepreciationBeginningBalance()));
			retMap.put("book_value_of_fixed_assets_ending_balance", format.format(sheetBalance.getBookValueOfFixedAssetsEndingBalance() == null ? 0 : sheetBalance.getBookValueOfFixedAssetsEndingBalance()));
			retMap.put("book_value_of_fixed_assets_beginning_balance", format.format(sheetBalance.getBookValueOfFixedAssetsBeginningBalance() == null ? 0 : sheetBalance.getBookValueOfFixedAssetsBeginningBalance()));
			retMap.put("construction_in_process_ending_balance", format.format(sheetBalance.getConstructionInProcessEndingBalance() == null ? 0 : sheetBalance.getConstructionInProcessEndingBalance()));
			retMap.put("construction_in_process_beginning_balance", format.format(sheetBalance.getConstructionInProcessBeginningBalance() == null ? 0 : sheetBalance.getConstructionInProcessBeginningBalance()));
			retMap.put("construction_materials_ending_balance", format.format(sheetBalance.getConstructionMaterialsEndingBalance() == null ? 0 : sheetBalance.getConstructionMaterialsEndingBalance()));
			retMap.put("construction_materials_beginning_balance", format.format(sheetBalance.getConstructionMaterialsBeginningBalance() == null ? 0 : sheetBalance.getConstructionMaterialsBeginningBalance()));
			retMap.put("removal_of_fixed_assets_ending_balance", format.format(sheetBalance.getRemovalOfFixedAssetsEndingBalance() == null ? 0 : sheetBalance.getRemovalOfFixedAssetsEndingBalance()));
			retMap.put("removal_of_fixed_assets_beginning_balance", format.format(sheetBalance.getRemovalOfFixedAssetsBeginningBalance() == null ? 0 : sheetBalance.getRemovalOfFixedAssetsBeginningBalance()));
			retMap.put("biological_assets_ending_balance", format.format(sheetBalance.getBiologicalAssetsEndingBalance() == null ? 0 : sheetBalance.getBiologicalAssetsEndingBalance()));
			retMap.put("biological_assets_beginning_balance", format.format(sheetBalance.getBiologicalAssetsBeginningBalance() == null ? 0 : sheetBalance.getBiologicalAssetsBeginningBalance()));
			retMap.put("intangible_assets_ending_balance", format.format(sheetBalance.getIntangibleAssetsEndingBalance() == null ? 0 : sheetBalance.getIntangibleAssetsEndingBalance()));
			retMap.put("intangible_assets_beginning_balance", format.format(sheetBalance.getIntangibleAssetsBeginningBalance() == null ? 0 : sheetBalance.getIntangibleAssetsBeginningBalance()));
			retMap.put("development_expenditure_ending_balance", format.format(sheetBalance.getDevelopmentExpenditureEndingBalance() == null ? 0 : sheetBalance.getDevelopmentExpenditureEndingBalance()));
			retMap.put("development_expenditure_beginning_balance", format.format(sheetBalance.getDevelopmentExpenditureBeginningBalance() == null ? 0 : sheetBalance.getDevelopmentExpenditureBeginningBalance()));
			retMap.put("long_term_prepaid_expenses_ending_balance", format.format(sheetBalance.getLongTermPrepaidExpensesEndingBalance() == null ? 0 : sheetBalance.getLongTermPrepaidExpensesEndingBalance()));
			retMap.put("long_term_prepaid_expenses_beginning_balance", format.format(sheetBalance.getLongTermPrepaidExpensesBeginningBalance() == null ? 0 : sheetBalance.getLongTermPrepaidExpensesBeginningBalance()));
			retMap.put("other_non_current_assets_ending_balance", format.format(sheetBalance.getOtherNonCurrentAssetsEndingBalance() == null ? 0 : sheetBalance.getOtherNonCurrentAssetsEndingBalance()));
			retMap.put("other_non_current_assets_beginning_balance", format.format(sheetBalance.getOtherNonCurrentAssetsBeginningBalance() == null ? 0 : sheetBalance.getOtherNonCurrentAssetsBeginningBalance()));
			retMap.put("total_non_current_assets_ending_balance", format.format(sheetBalance.getTotalNonCurrentAssetsEndingBalance() == null ? 0 : sheetBalance.getTotalNonCurrentAssetsEndingBalance()));
			retMap.put("total_non_current_assets_beginning_balance", format.format(sheetBalance.getTotalNonCurrentAssetsBeginningBalance() == null ? 0 : sheetBalance.getTotalNonCurrentAssetsBeginningBalance()));
			retMap.put("total_assets_ending_balance", format.format(sheetBalance.getTotalAssetsEndingBalance() == null ? 0 : sheetBalance.getTotalAssetsEndingBalance()));
			retMap.put("total_assets_beginning_balance", format.format(sheetBalance.getTotalAssetsBeginningBalance() == null ? 0 : sheetBalance.getTotalAssetsBeginningBalance()));
			retMap.put("short_loan_ending_balance", format.format(sheetBalance.getShortLoanEndingBalance() == null ? 0 : sheetBalance.getShortLoanEndingBalance()));
			retMap.put("short_loan_beginning_balance", format.format(sheetBalance.getShortLoanBeginningBalance() == null ? 0 : sheetBalance.getShortLoanBeginningBalance()));
			retMap.put("notes_payable_ending_balance", format.format(sheetBalance.getNotesPayableEndingBalance() == null ? 0 : sheetBalance.getNotesPayableEndingBalance()));
			retMap.put("notes_payable_beginning_balance", format.format(sheetBalance.getNotesPayableBeginningBalance() == null ? 0 : sheetBalance.getNotesPayableBeginningBalance()));
			retMap.put("payable_account_ending_balance", format.format(sheetBalance.getPayableAccountEndingBalance() == null ? 0 : sheetBalance.getPayableAccountEndingBalance()));
			retMap.put("payable_account_beginning_balance", format.format(sheetBalance.getPayableAccountBeginningBalance() == null ? 0 : sheetBalance.getPayableAccountBeginningBalance()));
			retMap.put("deposit_received_ending_balance", format.format(sheetBalance.getDepositReceivedEndingBalance() == null ? 0 : sheetBalance.getDepositReceivedEndingBalance()));
			retMap.put("deposit_received_beginning_balance", format.format(sheetBalance.getDepositReceivedBeginningBalance() == null ? 0 : sheetBalance.getDepositReceivedBeginningBalance()));
			retMap.put("employee_benefits_payable_ending_balance", format.format(sheetBalance.getEmployeeBenefitsPayableEndingBalance() == null ? 0 : sheetBalance.getEmployeeBenefitsPayableEndingBalance()));
			retMap.put("employee_benefits_payable_beginning_balance", format.format(sheetBalance.getEmployeeBenefitsPayableBeginningBalance() == null ? 0 : sheetBalance.getEmployeeBenefitsPayableBeginningBalance()));
			retMap.put("taxes_payable_ending_balance", format.format(sheetBalance.getTaxesPayableEndingBalance() == null ? 0 : sheetBalance.getTaxesPayableEndingBalance()));
			retMap.put("taxes_payable_beginning_balance", format.format(sheetBalance.getTaxesPayableBeginningBalance() == null ? 0 : sheetBalance.getTaxesPayableBeginningBalance()));
			retMap.put("interest_payable_ending_balance", format.format(sheetBalance.getInterestPayableEndingBalance() == null ? 0 : sheetBalance.getInterestPayableEndingBalance()));
			retMap.put("interest_payable_beginning_balance", format.format(sheetBalance.getInterestPayableBeginningBalance() == null ? 0 : sheetBalance.getInterestPayableBeginningBalance()));
			retMap.put("profit_payable_ending_balance", format.format(sheetBalance.getProfitPayableEndingBalance() == null ? 0 : sheetBalance.getProfitPayableEndingBalance()));
			retMap.put("profit_payable_beginning_balance", format.format(sheetBalance.getProfitPayableBeginningBalance() == null ? 0 : sheetBalance.getProfitPayableBeginningBalance()));
			retMap.put("accounts_payable_others_ending_balance", format.format(sheetBalance.getAccountsPayableOthersEndingBalance() == null ? 0 : sheetBalance.getAccountsPayableOthersEndingBalance()));
			retMap.put("accounts_payable_others_beginning_balance", format.format(sheetBalance.getAccountsPayableOthersBeginningBalance() == null ? 0 : sheetBalance.getAccountsPayableOthersBeginningBalance()));
			retMap.put("other_current_liabilities_ending_balance", format.format(sheetBalance.getOtherCurrentLiabilitiesEndingBalance() == null ? 0 : sheetBalance.getOtherCurrentLiabilitiesEndingBalance()));
			retMap.put("other_current_liabilities_beginning_balance", format.format(sheetBalance.getOtherCurrentLiabilitiesBeginningBalance() == null ? 0 : sheetBalance.getOtherCurrentLiabilitiesBeginningBalance()));
			retMap.put("total_current_liabilities_ending_balance", format.format(sheetBalance.getTotalCurrentLiabilitiesEndingBalance() == null ? 0 : sheetBalance.getTotalCurrentLiabilitiesEndingBalance()));
			retMap.put("total_current_liabilities_beginning_balance", format.format(sheetBalance.getTotalCurrentLiabilitiesBeginningBalance() == null ? 0 : sheetBalance.getTotalCurrentLiabilitiesBeginningBalance()));
			retMap.put("long_loan_ending_balance", format.format(sheetBalance.getLongLoanEndingBalance() == null ? 0 : sheetBalance.getLongLoanEndingBalance()));
			retMap.put("long_loan_beginning_balance", format.format(sheetBalance.getLongLoanBeginningBalance() == null ? 0 : sheetBalance.getLongLoanBeginningBalance()));
			retMap.put("long_term_payables_ending_balance", format.format(sheetBalance.getLongTermPayablesEndingBalance() == null ? 0 : sheetBalance.getLongTermPayablesEndingBalance()));
			retMap.put("long_term_payables_beginning_balance", format.format(sheetBalance.getLongTermPayablesBeginningBalance() == null ? 0 : sheetBalance.getLongTermPayablesBeginningBalance()));
			retMap.put("deferred_income_ending_balance", format.format(sheetBalance.getDeferredIncomeEndingBalance() == null ? 0 : sheetBalance.getDeferredIncomeEndingBalance()));
			retMap.put("deferred_income_beginning_balance", format.format(sheetBalance.getDeferredIncomeBeginningBalance() == null ? 0 : sheetBalance.getDeferredIncomeBeginningBalance()));
			retMap.put("other_non_current_liabilities_ending_balance", format.format(sheetBalance.getOtherNonCurrentLiabilitiesEndingBalance() == null ? 0 : sheetBalance.getOtherNonCurrentLiabilitiesEndingBalance()));
			retMap.put("other_non_current_liabilities_beginning_balance", format.format(sheetBalance.getOtherNonCurrentLiabilitiesBeginningBalance() == null ? 0 : sheetBalance.getOtherNonCurrentLiabilitiesBeginningBalance()));
			retMap.put("total_non_current_liabilities_ending_balance", format.format(sheetBalance.getTotalNonCurrentLiabilitiesEndingBalance() == null ? 0 : sheetBalance.getTotalNonCurrentLiabilitiesEndingBalance()));
			retMap.put("total_non_current_liabilities_beginning_balance", format.format(sheetBalance.getTotalNonCurrentLiabilitiesBeginningBalance() == null ? 0 : sheetBalance.getTotalNonCurrentLiabilitiesBeginningBalance()));
			retMap.put("total_liabilities_ending_balance", format.format(sheetBalance.getTotalLiabilitiesEndingBalance() == null ? 0 : sheetBalance.getTotalLiabilitiesEndingBalance()));
			retMap.put("total_liabilities_beginning_balance", format.format(sheetBalance.getTotalLiabilitiesBeginningBalance() == null ? 0 : sheetBalance.getTotalLiabilitiesBeginningBalance()));
			retMap.put("paid_in_capital_ending_balance", format.format(sheetBalance.getPaidInCapitalEndingBalance() == null ? 0 : sheetBalance.getPaidInCapitalEndingBalance()));
			retMap.put("paid_in_capital_beginning_balance", format.format(sheetBalance.getPaidInCapitalBeginningBalance() == null ? 0 : sheetBalance.getPaidInCapitalBeginningBalance()));
			retMap.put("capital_reserve_ending_balance", format.format(sheetBalance.getCapitalReserveEndingBalance() == null ? 0 : sheetBalance.getCapitalReserveEndingBalance()));
			retMap.put("capital_reserve_beginning_balance", format.format(sheetBalance.getCapitalReserveBeginningBalance() == null ? 0 : sheetBalance.getCapitalReserveBeginningBalance()));
			retMap.put("earned_surplus_ending_balance", format.format(sheetBalance.getEarnedSurplusEndingBalance() == null ? 0 : sheetBalance.getEarnedSurplusEndingBalance()));
			retMap.put("earned_surplus_beginning_balance", format.format(sheetBalance.getEarnedSurplusBeginningBalance() == null ? 0 : sheetBalance.getEarnedSurplusBeginningBalance()));
			retMap.put("undistributed_profit_ending_balance", format.format(sheetBalance.getUndistributedProfitEndingBalance() == null ? 0 : sheetBalance.getUndistributedProfitEndingBalance()));
			retMap.put("undistributed_profit_beginning_balance", format.format(sheetBalance.getUndistributedProfitBeginningBalance() == null ? 0 : sheetBalance.getUndistributedProfitBeginningBalance()));
			retMap.put("total_equities_ending_balance", format.format(sheetBalance.getTotalEquitiesEndingBalance() == null ? 0 : sheetBalance.getTotalEquitiesEndingBalance()));
			retMap.put("total_equities_beginning_balance", format.format(sheetBalance.getTotalEquitiesBeginningBalance() == null ? 0 : sheetBalance.getTotalEquitiesBeginningBalance()));
			retMap.put("total_liabilities_and_owner_equity_ending_balance", format.format(sheetBalance.getTotalLiabilitiesAndOwnerEquityEndingBalance() == null ? 0 : sheetBalance.getTotalLiabilitiesAndOwnerEquityEndingBalance()));
			retMap.put("total_liabilities_and_owner_equity_beginning_balance", format.format(sheetBalance.getTotalLiabilitiesAndOwnerEquityBeginningBalance() == null ? 0 : sheetBalance.getTotalLiabilitiesAndOwnerEquityBeginningBalance()));
		}
		return retMap;
	}
	
	@RequestMapping("/getNewestSheetCash")
	@ResponseBody
	public Map<String, String> getNewestSheetCash(
			HttpServletRequest request) {
		Map<String, String> retMap = new HashMap<>();
		retMap.put("result_is_null", "true");
		retMap.put("openid_exist", "true");
		String date = (String)request.getParameter("date");
		String userOpenId = (String)request.getParameter("userOpenId");
		logger.debug("getNewestSheetCash  userOpenId : {}", userOpenId);
		UserOpenid uod = userOpenIdDao.getUserOpenidByOpenId(userOpenId);
		logger.debug("getNewestSheetCash  uod : {}", uod);
		if(uod == null) {
			retMap.put("openid_exist", "false");
			return retMap;
		}
		long companyId = userService.getCompanyIdByUserId(uod.getUserId());
		logger.debug("getNewestSheetCash  companyId : {}", companyId);
		SheetCash sheetCash = sheetCashDao.getSheetCashDateByCompanyIdAndDate(companyId, date);
		if(sheetCash != null) {
			retMap.put("result_is_null", "false");
			DecimalFormat format = new DecimalFormat("#0.000");
			retMap.put("preparedby", sheetCash.getPreparedby());
			retMap.put("company_controller", sheetCash.getCompanyController());
			retMap.put("finance_chief", sheetCash.getFinanceChief());
			retMap.put("tabulator", sheetCash.getTabulator());
			retMap.put("create_time", sheetCash.getCreateTime());
			retMap.put("sales_year", format.format(sheetCash.getSalesYear() == null ? 0 : sheetCash.getSalesYear()));
			retMap.put("sales_month", format.format(sheetCash.getSalesMonth() == null ? 0 : sheetCash.getSalesMonth()));
			retMap.put("receive_year", format.format(sheetCash.getReceiveYear() == null ? 0 : sheetCash.getReceiveYear()));
			retMap.put("receive_month", format.format(sheetCash.getReceiveMonth() == null ? 0 : sheetCash.getReceiveMonth()));
			retMap.put("buy_year", format.format(sheetCash.getBuyYear() == null ? 0 : sheetCash.getBuyYear()));
			retMap.put("buy_month", format.format(sheetCash.getBuyMonth() == null ? 0 : sheetCash.getBuyMonth()));
			retMap.put("salary_year", format.format(sheetCash.getSalaryYear() == null ? 0 : sheetCash.getSalaryYear()));
			retMap.put("salary_month", format.format(sheetCash.getSalaryMonth() == null ? 0 : sheetCash.getSalaryMonth()));
			retMap.put("tax_year", format.format(sheetCash.getTaxYear() == null ? 0 : sheetCash.getTaxYear()));
			retMap.put("tax_month", format.format(sheetCash.getTaxMonth() == null ? 0 : sheetCash.getTaxMonth()));
			retMap.put("other_pay_year", format.format(sheetCash.getOtherPayYear() == null ? 0 : sheetCash.getOtherPayYear()));
			retMap.put("other_pay_month", format.format(sheetCash.getOtherPayMonth() == null ? 0 : sheetCash.getOtherPayMonth()));
			retMap.put("operating_activities_year", format.format(sheetCash.getOperatingActivitiesYear() == null ? 0 : sheetCash.getOperatingActivitiesYear()));
			retMap.put("operating_activities_month", format.format(sheetCash.getOperatingActivitiesMonth() == null ? 0 : sheetCash.getOperatingActivitiesMonth()));
			retMap.put("take_back_year", format.format(sheetCash.getTakeBackYear() == null ? 0 : sheetCash.getTakeBackYear()));
			retMap.put("take_back_month", format.format(sheetCash.getTakeBackMonth() == null ? 0 : sheetCash.getTakeBackMonth()));
			retMap.put("equity_earnings_year", format.format(sheetCash.getEquityEarningsYear() == null ? 0 : sheetCash.getEquityEarningsYear()));
			retMap.put("equity_earnings_month", format.format(sheetCash.getEquityEarningsMonth() == null ? 0 : sheetCash.getEquityEarningsMonth()));
			retMap.put("handle_year", format.format(sheetCash.getHandleYear() == null ? 0 : sheetCash.getHandleYear()));
			retMap.put("handle_month", format.format(sheetCash.getHandleMonth() == null ? 0 : sheetCash.getHandleMonth()));
			retMap.put("investments_pay_year", format.format(sheetCash.getInvestmentsPayYear() == null ? 0 : sheetCash.getInvestmentsPayYear()));
			retMap.put("investments_pay_month", format.format(sheetCash.getInvestmentsPayMonth() == null ? 0 : sheetCash.getInvestmentsPayMonth()));
			retMap.put("coustruction_year", format.format(sheetCash.getCoustructionYear() == null ? 0 : sheetCash.getCoustructionYear()));
			retMap.put("coustruction_month", format.format(sheetCash.getCoustructionMonth() == null ? 0 : sheetCash.getCoustructionMonth()));
			retMap.put("activity_investment_year", format.format(sheetCash.getActivityInvestmentYear() == null ? 0 : sheetCash.getActivityInvestmentYear()));
			retMap.put("activity_investment_month", format.format(sheetCash.getActivityInvestmentMonth() == null ? 0 : sheetCash.getActivityInvestmentMonth()));
			retMap.put("loan_year", format.format(sheetCash.getLoanYear() == null ? 0 : sheetCash.getLoanYear()));
			retMap.put("loan_month", format.format(sheetCash.getLoanMonth() == null ? 0 : sheetCash.getLoanMonth()));
			retMap.put("investors_to_invest_year", format.format(sheetCash.getInvestorsToInvestYear() == null ? 0 : sheetCash.getInvestorsToInvestYear()));
			retMap.put("investors_to_invest_month", format.format(sheetCash.getInvestorsToInvestMonth() == null ? 0 : sheetCash.getInvestorsToInvestMonth()));
			retMap.put("payment_of_principal_year", format.format(sheetCash.getPaymentOfPrincipalYear() == null ? 0 : sheetCash.getPaymentOfPrincipalYear()));
			retMap.put("payment_of_principal_month", format.format(sheetCash.getPaymentOfPrincipalMonth() == null ? 0 : sheetCash.getPaymentOfPrincipalMonth()));
			retMap.put("paid_interest_year", format.format(sheetCash.getPaidInterestYear() == null ? 0 : sheetCash.getPaidInterestYear()));
			retMap.put("paid_interest_month", format.format(sheetCash.getPaidInterestMonth() == null ? 0 : sheetCash.getPaidInterestMonth()));
			retMap.put("distribute_profit_year", format.format(sheetCash.getDistributeProfitYear() == null ? 0 : sheetCash.getDistributeProfitYear()));
			retMap.put("distribute_profit_month", format.format(sheetCash.getDistributeProfitMonth() == null ? 0 : sheetCash.getDistributeProfitMonth()));
			retMap.put("financing_activity_year", format.format(sheetCash.getFinancingActivityYear() == null ? 0 : sheetCash.getFinancingActivityYear()));
			retMap.put("financing_activity_month", format.format(sheetCash.getFinancingActivityMonth() == null ? 0 : sheetCash.getFinancingActivityMonth()));
			retMap.put("net_increase_in_cash_year", format.format(sheetCash.getNetIncreaseInCashYear() == null ? 0 : sheetCash.getNetIncreaseInCashYear()));
			retMap.put("net_increase_in_cash_month", format.format(sheetCash.getNetIncreaseInCashMonth() == null ? 0 : sheetCash.getNetIncreaseInCashMonth()));
			retMap.put("cash_at_beginning_of_period_year", format.format(sheetCash.getCashAtBeginningOfPeriodYear() == null ? 0 : sheetCash.getCashAtBeginningOfPeriodYear()));
			retMap.put("cash_at_beginning_of_period_month", format.format(sheetCash.getCashAtBeginningOfPeriodMonth() == null ? 0 : sheetCash.getCashAtBeginningOfPeriodMonth()));
			retMap.put("ending_cash_balance_year", format.format(sheetCash.getEndingCashBalanceYear() == null ? 0 : sheetCash.getEndingCashBalanceYear()));
			retMap.put("ending_cash_balance_month", format.format(sheetCash.getEndingCashBalanceMonth() == null ? 0 : sheetCash.getEndingCashBalanceMonth()));
		}
		return retMap;
	}
	
	@RequestMapping("/getNewestSheetIncome")
	@ResponseBody
	public Map<String, String> getNewestSheetIncome(
			HttpServletRequest request) {
		Map<String, String> retMap = new HashMap<>();
		retMap.put("result_is_null", "true");
		retMap.put("openid_exist", "true");
		String date = (String)request.getParameter("date");
		String userOpenId = (String)request.getParameter("userOpenId");
		logger.debug("getNewestSheetIncome  userOpenId : {}", userOpenId);
		UserOpenid uod = userOpenIdDao.getUserOpenidByOpenId(userOpenId);
		logger.debug("getNewestSheetIncome  uod : {}", uod);
		if(uod == null) {
			retMap.put("openid_exist", "false");
			return retMap;
		}
		long companyId = userService.getCompanyIdByUserId(uod.getUserId());
		logger.debug("getNewestSheetIncome  companyId : {}", companyId);
		SheetIncome sheetIncome = sheetIncomeDao.getSheetIncomeDateByCompanyIdAndDate(companyId, date);
		if(sheetIncome != null) {
			retMap.put("result_is_null", "false");
			DecimalFormat format = new DecimalFormat("#0.000");
			retMap.put("preparedby", sheetIncome.getPreparedby());
			retMap.put("company_controller", sheetIncome.getCompanyController());
			retMap.put("finance_chief", sheetIncome.getFinanceChief());
			retMap.put("tabulator", sheetIncome.getTabulator());
			retMap.put("create_time", sheetIncome.getCreateTime());
			retMap.put("operating_receipt_year", format.format(sheetIncome.getOperatingReceiptYear() == null ? 0 : sheetIncome.getOperatingReceiptYear()));
			retMap.put("operating_receipt_month", format.format(sheetIncome.getOperatingReceiptMonth() == null ? 0 : sheetIncome.getOperatingReceiptMonth()));
			retMap.put("cost_in_business_year", format.format(sheetIncome.getCostInBusinessYear() == null ? 0 : sheetIncome.getCostInBusinessYear()));
			retMap.put("cost_in_business_month", format.format(sheetIncome.getCostInBusinessMonth() == null ? 0 : sheetIncome.getCostInBusinessMonth()));
			retMap.put("business_tariff_and_annex_year", format.format(sheetIncome.getBusinessTariffAndAnnexYear() == null ? 0 : sheetIncome.getBusinessTariffAndAnnexYear()));
			retMap.put("business_tariff_and_annex_month", format.format(sheetIncome.getBusinessTariffAndAnnexMonth() == null ? 0 : sheetIncome.getBusinessTariffAndAnnexMonth()));
			retMap.put("excise_tax_year", format.format(sheetIncome.getExciseTaxYear() == null ? 0 : sheetIncome.getExciseTaxYear()));
			retMap.put("excise_tax_month", format.format(sheetIncome.getExciseTaxMonth() == null ? 0 : sheetIncome.getExciseTaxMonth()));
			retMap.put("sales_tax_year", format.format(sheetIncome.getSalesTaxYear() == null ? 0 : sheetIncome.getSalesTaxYear()));
			retMap.put("sales_tax_month", format.format(sheetIncome.getSalesTaxMonth() == null ? 0 : sheetIncome.getSalesTaxMonth()));
			retMap.put("urban_maintenance_and_construction_tax_year", format.format(sheetIncome.getUrbanMaintenanceAndConstructionTaxYear() == null ? 0 : sheetIncome.getUrbanMaintenanceAndConstructionTaxYear()));
			retMap.put("urban_maintenance_and_construction_month", format.format(sheetIncome.getUrbanMaintenanceAndConstructionMonth() == null ? 0 : sheetIncome.getUrbanMaintenanceAndConstructionMonth()));
			retMap.put("resource_tax_year", format.format(sheetIncome.getResourceTaxYear() == null ? 0 : sheetIncome.getResourceTaxYear()));
			retMap.put("resource_tax_month", format.format(sheetIncome.getResourceTaxMonth() == null ? 0 : sheetIncome.getResourceTaxMonth()));
			retMap.put("land_value_increment_tax_year", format.format(sheetIncome.getLandValueIncrementTaxYear() == null ? 0 : sheetIncome.getLandValueIncrementTaxYear()));
			retMap.put("land_value_increment_tax_month", format.format(sheetIncome.getLandValueIncrementTaxMonth() == null ? 0 : sheetIncome.getLandValueIncrementTaxMonth()));
			retMap.put("land_house_tax_year", format.format(sheetIncome.getLandHouseTaxYear() == null ? 0 : sheetIncome.getLandHouseTaxYear()));
			retMap.put("land_house_tax_month", format.format(sheetIncome.getLandHouseTaxMonth() == null ? 0 : sheetIncome.getLandHouseTaxMonth()));
			retMap.put("education_year", format.format(sheetIncome.getEducationYear() == null ? 0 : sheetIncome.getEducationYear()));
			retMap.put("education_month", format.format(sheetIncome.getEducationMonth() == null ? 0 : sheetIncome.getEducationMonth()));
			retMap.put("sales_year", format.format(sheetIncome.getSalesYear() == null ? 0 : sheetIncome.getSalesYear()));
			retMap.put("sales_month", format.format(sheetIncome.getSalesMonth() == null ? 0 : sheetIncome.getSalesMonth()));
			retMap.put("maintenance_year", format.format(sheetIncome.getMaintenanceYear() == null ? 0 : sheetIncome.getMaintenanceYear()));
			retMap.put("maintenance_month", format.format(sheetIncome.getMaintenanceMonth() == null ? 0 : sheetIncome.getMaintenanceMonth()));
			retMap.put("advertise_year", format.format(sheetIncome.getAdvertiseYear() == null ? 0 : sheetIncome.getAdvertiseYear()));
			retMap.put("advertise_month", format.format(sheetIncome.getAdvertiseMonth() == null ? 0 : sheetIncome.getAdvertiseMonth()));
			retMap.put("manage_year", format.format(sheetIncome.getManageYear() == null ? 0 : sheetIncome.getManageYear()));
			retMap.put("manage_month", format.format(sheetIncome.getManageMonth() == null ? 0 : sheetIncome.getManageMonth()));
			retMap.put("establishment_charges_year", format.format(sheetIncome.getEstablishmentChargesYear() == null ? 0 : sheetIncome.getEstablishmentChargesYear()));
			retMap.put("establishment_charges_month", format.format(sheetIncome.getEstablishmentChargesMonth() == null ? 0 : sheetIncome.getEstablishmentChargesMonth()));
			retMap.put("business_entertainment_year", format.format(sheetIncome.getBusinessEntertainmentYear() == null ? 0 : sheetIncome.getBusinessEntertainmentYear()));
			retMap.put("business_entertainment_month", format.format(sheetIncome.getBusinessEntertainmentMonth() == null ? 0 : sheetIncome.getBusinessEntertainmentMonth()));
			retMap.put("research_year", format.format(sheetIncome.getResearchYear() == null ? 0 : sheetIncome.getResearchYear()));
			retMap.put("research_month", format.format(sheetIncome.getResearchMonth() == null ? 0 : sheetIncome.getResearchMonth()));
			retMap.put("finance_year", format.format(sheetIncome.getFinanceYear() == null ? 0 : sheetIncome.getFinanceYear()));
			retMap.put("finance_month", format.format(sheetIncome.getFinanceMonth() == null ? 0 : sheetIncome.getFinanceMonth()));
			retMap.put("interest_year", format.format(sheetIncome.getInterestYear() == null ? 0 : sheetIncome.getInterestYear()));
			retMap.put("interest_month", format.format(sheetIncome.getInterestMonth() == null ? 0 : sheetIncome.getInterestMonth()));
			retMap.put("equity_earnings_year", format.format(sheetIncome.getEquityEarningsYear() == null ? 0 : sheetIncome.getEquityEarningsYear()));
			retMap.put("equity_earnings_month", format.format(sheetIncome.getEquityEarningsMonth() == null ? 0 : sheetIncome.getEquityEarningsMonth()));
			retMap.put("operating_profit_year", format.format(sheetIncome.getOperatingProfitYear() == null ? 0 : sheetIncome.getOperatingProfitYear()));
			retMap.put("operating_profit_month", format.format(sheetIncome.getOperatingProfitMonth() == null ? 0 : sheetIncome.getOperatingProfitMonth()));
			retMap.put("nonbusiness_income_year", format.format(sheetIncome.getNonbusinessIncomeYear() == null ? 0 : sheetIncome.getNonbusinessIncomeYear()));
			retMap.put("nonbusiness_income_month", format.format(sheetIncome.getNonbusinessIncomeMonth() == null ? 0 : sheetIncome.getNonbusinessIncomeMonth()));
			retMap.put("government_grants_year", format.format(sheetIncome.getGovernmentGrantsYear() == null ? 0 : sheetIncome.getGovernmentGrantsYear()));
			retMap.put("government_grants_month", format.format(sheetIncome.getGovernmentGrantsMonth() == null ? 0 : sheetIncome.getGovernmentGrantsMonth()));
			retMap.put("non_business_expenditure_year", format.format(sheetIncome.getNonBusinessExpenditureYear() == null ? 0 : sheetIncome.getNonBusinessExpenditureYear()));
			retMap.put("non_business_expenditure_month", format.format(sheetIncome.getNonBusinessExpenditureMonth() == null ? 0 : sheetIncome.getNonBusinessExpenditureMonth()));
			retMap.put("loss_on_bad_debt_year", format.format(sheetIncome.getLossOnBadDebtYear() == null ? 0 : sheetIncome.getLossOnBadDebtYear()));
			retMap.put("loss_on_bad_debt_month", format.format(sheetIncome.getLossOnBadDebtMonth() == null ? 0 : sheetIncome.getLossOnBadDebtMonth()));
			retMap.put("loss_on_long_term_bonds_year", format.format(sheetIncome.getLossOnLongTermBondsYear() == null ? 0 : sheetIncome.getLossOnLongTermBondsYear()));
			retMap.put("loss_on_long_term_bonds_month", format.format(sheetIncome.getLossOnLongTermBondsMonth() == null ? 0 : sheetIncome.getLossOnLongTermBondsMonth()));
			retMap.put("loss_on_long_term_stock_year", format.format(sheetIncome.getLossOnLongTermStockYear() == null ? 0 : sheetIncome.getLossOnLongTermStockYear()));
			retMap.put("loss_on_long_term_stock_month", format.format(sheetIncome.getLossOnLongTermStockMonth() == null ? 0 : sheetIncome.getLossOnLongTermStockMonth()));
			retMap.put("loss_on_long_term_natural_calamities_year", format.format(sheetIncome.getLossOnLongTermNaturalCalamitiesYear() == null ? 0 : sheetIncome.getLossOnLongTermNaturalCalamitiesYear()));
			retMap.put("loss_on_long_term_natural_calamities_month", format.format(sheetIncome.getLossOnLongTermNaturalCalamitiesMonth() == null ? 0 : sheetIncome.getLossOnLongTermNaturalCalamitiesMonth()));
			retMap.put("tax_delay_charge_year", format.format(sheetIncome.getTaxDelayChargeYear() == null ? 0 : sheetIncome.getTaxDelayChargeYear()));
			retMap.put("tax_delay_charge_month", format.format(sheetIncome.getTaxDelayChargeMonth() == null ? 0 : sheetIncome.getTaxDelayChargeMonth()));
			retMap.put("total_profit_year", format.format(sheetIncome.getTotalProfitYear() == null ? 0 : sheetIncome.getTotalProfitYear()));
			retMap.put("total_profit_month", format.format(sheetIncome.getTotalProfitMonth() == null ? 0 : sheetIncome.getTotalProfitMonth()));
			retMap.put("income_tax_expense_year", format.format(sheetIncome.getIncomeTaxExpenseYear() == null ? 0 : sheetIncome.getIncomeTaxExpenseYear()));
			retMap.put("income_tax_expense_month", format.format(sheetIncome.getIncomeTaxExpenseMonth() == null ? 0 : sheetIncome.getIncomeTaxExpenseMonth()));
			retMap.put("net_margin_year", format.format(sheetIncome.getNetMarginYear() == null ? 0 : sheetIncome.getNetMarginYear()));
			retMap.put("net_margin_month", format.format(sheetIncome.getNetMarginMonth() == null ? 0 : sheetIncome.getNetMarginMonth()));
		}
		return retMap;
	}
	
	@RequestMapping("/getNewestSheetSalaryTax")
	@ResponseBody
	public Map<String, String> getNewestSheetSalaryTax(
			HttpServletRequest request) {
		Map<String, String> retMap = new HashMap<>();
		retMap.put("result_is_null", "true");
		retMap.put("openid_exist", "true");
		String date = (String)request.getParameter("date");
		String userOpenId = (String)request.getParameter("userOpenId");
		logger.debug("getNewestSheetSalaryTax  userOpenId : {}", userOpenId);
		UserOpenid uod = userOpenIdDao.getUserOpenidByOpenId(userOpenId);
		logger.debug("getNewestSheetSalaryTax  uod : {}", uod);
		if(uod == null) {
			retMap.put("openid_exist", "false");
			return retMap;
		}
		long companyId = userService.getCompanyIdByUserId(uod.getUserId());
		logger.debug("getNewestSheetSalaryTax  companyId : {}", companyId);
		SheetSalaryTax sheetSalaryTax = sheetSalaryTaxDao.getSheetSalaryTaxDateByCompanyIdAndDate(companyId, date);
		if(sheetSalaryTax != null) {
			retMap.put("result_is_null", "false");
			DecimalFormat format = new DecimalFormat("#0.000");
			retMap.put("preparedby", sheetSalaryTax.getPreparedby());
			retMap.put("company_controller", sheetSalaryTax.getCompanyController());
			retMap.put("finance_chief", sheetSalaryTax.getFinanceChief());
			retMap.put("tabulator", sheetSalaryTax.getTabulator());
			retMap.put("create_time", sheetSalaryTax.getCreateTime());
			retMap.put("salery_end", format.format(sheetSalaryTax.getSaleryEnd() == null ? 0 : sheetSalaryTax.getSaleryEnd()));
			retMap.put("salery_begin", format.format(sheetSalaryTax.getSaleryBegin() == null ? 0 : sheetSalaryTax.getSaleryBegin()));
			retMap.put("bonus_end", format.format(sheetSalaryTax.getBonusEnd() == null ? 0 : sheetSalaryTax.getBonusEnd()));
			retMap.put("bonus_begin", format.format(sheetSalaryTax.getBonusBegin() == null ? 0 : sheetSalaryTax.getBonusBegin()));
			retMap.put("welfare_end", format.format(sheetSalaryTax.getWelfareEnd() == null ? 0 : sheetSalaryTax.getWelfareEnd()));
			retMap.put("welfare_begin", format.format(sheetSalaryTax.getWelfareBegin() == null ? 0 : sheetSalaryTax.getWelfareBegin()));
			retMap.put("insurance_end", format.format(sheetSalaryTax.getInsuranceEnd() == null ? 0 : sheetSalaryTax.getInsuranceEnd()));
			retMap.put("insurance_begin", format.format(sheetSalaryTax.getInsuranceBegin() == null ? 0 : sheetSalaryTax.getInsuranceBegin()));
			retMap.put("funds_end", format.format(sheetSalaryTax.getFundsEnd() == null ? 0 : sheetSalaryTax.getFundsEnd()));
			retMap.put("funds_begin", format.format(sheetSalaryTax.getFundsBegin() == null ? 0 : sheetSalaryTax.getFundsBegin()));
			retMap.put("sociaty_end", format.format(sheetSalaryTax.getSociatyEnd() == null ? 0 : sheetSalaryTax.getSociatyEnd()));
			retMap.put("sociaty_begin", format.format(sheetSalaryTax.getSociatyBegin() == null ? 0 : sheetSalaryTax.getSociatyBegin()));
			retMap.put("education_end", format.format(sheetSalaryTax.getEducationEnd() == null ? 0 : sheetSalaryTax.getEducationEnd()));
			retMap.put("education_begin", format.format(sheetSalaryTax.getEducationBegin() == null ? 0 : sheetSalaryTax.getEducationBegin()));
			retMap.put("no_cash_welfare_end", format.format(sheetSalaryTax.getNoCashWelfareEnd() == null ? 0 : sheetSalaryTax.getNoCashWelfareEnd()));
			retMap.put("no_cash_welfare_begin", format.format(sheetSalaryTax.getNoCashWelfareBegin() == null ? 0 : sheetSalaryTax.getNoCashWelfareBegin()));
			retMap.put("dismiss_end", format.format(sheetSalaryTax.getDismissEnd() == null ? 0 : sheetSalaryTax.getDismissEnd()));
			retMap.put("dismiss_begin", format.format(sheetSalaryTax.getDismissBegin() == null ? 0 : sheetSalaryTax.getDismissBegin()));
			retMap.put("other_end", format.format(sheetSalaryTax.getOtherEnd() == null ? 0 : sheetSalaryTax.getOtherEnd()));
			retMap.put("other_begin", format.format(sheetSalaryTax.getOtherBegin() == null ? 0 : sheetSalaryTax.getOtherBegin()));
			retMap.put("salary_total_end", format.format(sheetSalaryTax.getSalaryTotalEnd() == null ? 0 : sheetSalaryTax.getSalaryTotalEnd()));
			retMap.put("salary_total_begin", format.format(sheetSalaryTax.getSalaryTotalBegin() == null ? 0 : sheetSalaryTax.getSalaryTotalBegin()));
			retMap.put("value_added_tax_end", format.format(sheetSalaryTax.getValueAddedTaxEnd() == null ? 0 : sheetSalaryTax.getValueAddedTaxEnd()));
			retMap.put("value_added_tax_begin", format.format(sheetSalaryTax.getValueAddedTaxBegin() == null ? 0 : sheetSalaryTax.getValueAddedTaxBegin()));
			retMap.put("excise_tax_end", format.format(sheetSalaryTax.getExciseTaxEnd() == null ? 0 : sheetSalaryTax.getExciseTaxEnd()));
			retMap.put("excise_tax_begin", format.format(sheetSalaryTax.getExciseTaxBegin() == null ? 0 : sheetSalaryTax.getExciseTaxBegin()));
			retMap.put("business_tax_end", format.format(sheetSalaryTax.getBusinessTaxEnd() == null ? 0 : sheetSalaryTax.getBusinessTaxEnd()));
			retMap.put("business_tax_begin", format.format(sheetSalaryTax.getBusinessTaxBegin() == null ? 0 : sheetSalaryTax.getBusinessTaxBegin()));
			retMap.put("urban_maintenance_and_construction_tax_end", format.format(sheetSalaryTax.getUrbanMaintenanceAndConstructionTaxEnd() == null ? 0 : sheetSalaryTax.getUrbanMaintenanceAndConstructionTaxEnd()));
			retMap.put("urban_maintenance_and_construction_tax_begin", format.format(sheetSalaryTax.getUrbanMaintenanceAndConstructionTaxBegin() == null ? 0 : sheetSalaryTax.getUrbanMaintenanceAndConstructionTaxBegin()));
			retMap.put("business_income_taxes_end", format.format(sheetSalaryTax.getBusinessIncomeTaxesEnd() == null ? 0 : sheetSalaryTax.getBusinessIncomeTaxesEnd()));
			retMap.put("business_income_taxes_begin", format.format(sheetSalaryTax.getBusinessIncomeTaxesBegin() == null ? 0 : sheetSalaryTax.getBusinessIncomeTaxesBegin()));
			retMap.put("resource_tax_end", format.format(sheetSalaryTax.getResourceTaxEnd() == null ? 0 : sheetSalaryTax.getResourceTaxEnd()));
			retMap.put("resource_tax_begin", format.format(sheetSalaryTax.getResourceTaxBegin() == null ? 0 : sheetSalaryTax.getResourceTaxBegin()));
			retMap.put("land_value_increment_tax_end", format.format(sheetSalaryTax.getLandValueIncrementTaxEnd() == null ? 0 : sheetSalaryTax.getLandValueIncrementTaxEnd()));
			retMap.put("land_value_increment_tax_begin", format.format(sheetSalaryTax.getLandValueIncrementTaxBegin() == null ? 0 : sheetSalaryTax.getLandValueIncrementTaxBegin()));
			retMap.put("urban_land_use_tax_end", format.format(sheetSalaryTax.getUrbanLandUseTaxEnd() == null ? 0 : sheetSalaryTax.getUrbanLandUseTaxEnd()));
			retMap.put("urban_land_use_tax_begin", format.format(sheetSalaryTax.getUrbanLandUseTaxBegin() == null ? 0 : sheetSalaryTax.getUrbanLandUseTaxBegin()));
			retMap.put("building_taxes_end", format.format(sheetSalaryTax.getBuildingTaxesEnd() == null ? 0 : sheetSalaryTax.getBuildingTaxesEnd()));
			retMap.put("building_taxes_begin", format.format(sheetSalaryTax.getBuildingTaxesBegin() == null ? 0 : sheetSalaryTax.getBuildingTaxesBegin()));
			retMap.put("vehicle_and_vessel_tax_end", format.format(sheetSalaryTax.getVehicleAndVesselTaxEnd() == null ? 0 : sheetSalaryTax.getVehicleAndVesselTaxEnd()));
			retMap.put("vehicle_and_vessel_tax_begin", format.format(sheetSalaryTax.getVehicleAndVesselTaxBegin() == null ? 0 : sheetSalaryTax.getVehicleAndVesselTaxBegin()));
			retMap.put("attach_education_end", format.format(sheetSalaryTax.getAttachEducationEnd() == null ? 0 : sheetSalaryTax.getAttachEducationEnd()));
			retMap.put("attach_education_begin", format.format(sheetSalaryTax.getAttachEducationBegin() == null ? 0 : sheetSalaryTax.getAttachEducationBegin()));
			retMap.put("mineral_resources_end", format.format(sheetSalaryTax.getMineralResourcesEnd() == null ? 0 : sheetSalaryTax.getMineralResourcesEnd()));
			retMap.put("mineral_resources_begin", format.format(sheetSalaryTax.getMineralResourcesBegin() == null ? 0 : sheetSalaryTax.getMineralResourcesBegin()));
			retMap.put("sewage_charge_end", format.format(sheetSalaryTax.getSewageChargeEnd() == null ? 0 : sheetSalaryTax.getSewageChargeEnd()));
			retMap.put("sewage_charge_begin", format.format(sheetSalaryTax.getSewageChargeBegin() == null ? 0 : sheetSalaryTax.getSewageChargeBegin()));
			retMap.put("income_tax_for_individuals_end", format.format(sheetSalaryTax.getIncomeTaxForIndividualsEnd() == null ? 0 : sheetSalaryTax.getIncomeTaxForIndividualsEnd()));
			retMap.put("income_tax_for_individuals_begin", format.format(sheetSalaryTax.getIncomeTaxForIndividualsBegin() == null ? 0 : sheetSalaryTax.getIncomeTaxForIndividualsBegin()));
			retMap.put("tax_total_end", format.format(sheetSalaryTax.getTaxTotalEnd() == null ? 0 : sheetSalaryTax.getTaxTotalEnd()));
			retMap.put("tax_total_begin", format.format(sheetSalaryTax.getTaxTotalBegin() == null ? 0 : sheetSalaryTax.getTaxTotalBegin()));
		}
		return retMap;
	}
}
