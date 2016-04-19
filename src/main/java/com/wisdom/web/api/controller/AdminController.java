package com.wisdom.web.api.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wisdom.accounter.service.IAccounterService;
import com.wisdom.common.model.Accounter;
import com.wisdom.common.model.Company;
import com.wisdom.common.model.CompanyAndPayModel;
import com.wisdom.common.model.CompanyBill;
import com.wisdom.common.model.CompanyDetail;
import com.wisdom.common.model.CompanyInfo;
import com.wisdom.common.model.CompanyPay;
import com.wisdom.common.model.SalarySocialSecurity;
import com.wisdom.common.model.User;
import com.wisdom.common.model.UserPhone;
import com.wisdom.company.service.ICompanyBillService;
import com.wisdom.company.service.ICompanyDetailService;
import com.wisdom.company.service.ICompanyPayService;
import com.wisdom.company.service.ICompanyService;
import com.wisdom.user.dao.IUserQueryDao;
import com.wisdom.user.service.IUserService;
import com.wisdom.web.api.ICompanyBillApi;

@Controller
public class AdminController {

	private static final Logger logger = LoggerFactory
			.getLogger(AdminController.class);

	@Autowired
	private ICompanyPayService companyPayService;
	
	@Autowired
	private ICompanyService companyService;
	
	@Autowired
	private ICompanyDetailService companyDetailService;
	
	@Autowired
	private IAccounterService accounterService;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IUserQueryDao userQueryDao;
	
	@RequestMapping("/admin/getAllRegisterCompanyInfo")
	@ResponseBody
	public List<Map<String, String>> getAllRegisterCompanyInfo(HttpServletRequest request) {
		List<Company> companyList = companyService.getAllCompany();
		List<Map<String, String>> retList = new ArrayList<>();
		if(companyList != null && companyList.size() != 0) {
			for(Company company : companyList) {
				if(company.getParentId() != -1) continue;
				logger.debug("companyId: {}", company.getId());
				User user =userQueryDao.getCompanyAdminUserByCompanyId(company.getId());
				if(user == null) continue;
				logger.debug("userId: {}", user.getUserId());
				UserPhone userPhone = userQueryDao.getUserPhoneByUserId(user.getUserId());
				String phone = (userPhone == null || userPhone.getPhone().isEmpty()) ? "未设定" : userPhone.getPhone(); 
				logger.debug("userPhone: {}", phone);
				Map<String, String> map = new HashMap<>();
				map.put("companyName", company.getName() == null ? "未设定" : company.getName());
				map.put("date", company.getCreateTime().toString().substring(0, 10));
				map.put("expense", String.valueOf(company.getMonthExpense()));
				map.put("callTime", company.getPerfectMoment());
				map.put("phone", phone);
				map.put("userId", user.getUserId());
				int auditStatus = user.getAuditStatus();
				if(auditStatus == 0) {
					map.put("auditStatus", "正在审核");
				} else if(auditStatus == 1) {
					map.put("auditStatus", "审核通过");
				} else if(auditStatus == 2) {
					map.put("auditStatus", "审核未通过");
				}
				retList.add(map);
			}
		}
		return retList;
	}
	@RequestMapping("/admin/getAllCompanyInfoAndUserIdAndPhone")
	@ResponseBody
	public List<Map<String, String>> getAllCompanyInfoAndUserIdAndPhone(HttpServletRequest request) {
		List<CompanyInfo> companyInfoList=companyService.getCompanyInfoAndUserIDAndPhone();
		List<Map<String, String>> retList = new ArrayList<>();
		if(companyInfoList != null && companyInfoList.size() != 0) {
			for(CompanyInfo companyInfo : companyInfoList) {
				//logger.debug("companyId: {}", companyInfo.getId());

				//logger.debug("userId: {}", companyInfo.getUserId());
				String phone = (companyInfo.getPhone() == null || companyInfo.getPhone().isEmpty() ? "未设定" : companyInfo.getPhone()); 
				//logger.debug("userPhone: {}", phone);
				Map<String, String> map = new HashMap<>();
				map.put("companyName", companyInfo.getName() == null ? "未设定" : companyInfo.getName());
				map.put("date", companyInfo.getCreateTime().toString().substring(0, 10));
				map.put("expense", String.valueOf(companyInfo.getMonthExpense()));
				map.put("callTime", companyInfo.getPerfectMoment());
				map.put("phone", phone);
				map.put("userId", companyInfo.getUserId());
				int auditStatus = companyInfo.getAuditStatus();

				if(auditStatus == 0) {
					map.put("auditStatus", "正在审核");
				} else if(auditStatus == 1) {
					map.put("auditStatus", "审核通过");
				} else if(auditStatus == 2) {
					map.put("auditStatus", "审核未通过");
				}
				retList.add(map);
			}
		}
		return retList;
	}
	@RequestMapping("/admin/auditUser")
	@ResponseBody
	public Map<String, String> auditUser(HttpServletRequest request) {
		String userId = request.getParameter("userId");
		String status = request.getParameter("auditStatus");
		Map<String, String> retMap = new HashMap<>();
		userService.updateUserAuditStatusByUserId(Integer.valueOf(status), userId);
		return retMap;
	}
	
	@RequestMapping("/admin/getAllRegisterCompanyInfoByName")
	@ResponseBody
	public List<Map<String, String>> getAllRegisterCompanyInfoByName(HttpServletRequest request) {
		List<Company> companyList = companyService.getAllCompany();
		String conditionValue = request.getParameter("conditionValue");
		List<Map<String, String>> retList = new ArrayList<>();
		if(companyList != null && companyList.size() != 0) {
			for(Company company : companyList) {
				if(company.getParentId() != -1) continue;
				if(company.getName() == null) continue;
				if(company.getName().indexOf(conditionValue) == -1) continue;
				Map<String, String> map = new HashMap<>();
				map.put("companyName", company.getName() == null ? "未设定" : company.getName());
				map.put("date", company.getCreateTime().toString().substring(0, 10));
				map.put("expense", String.valueOf(company.getMonthExpense()));
				map.put("callTime", company.getPerfectMoment());
				retList.add(map);
			}
		}
		return retList;
	}
	
	@RequestMapping("/admin/getAllCompanyDetailInfo")
	@ResponseBody
	public List<Map<String, String>> getAllCompanyDetailInfo(HttpServletRequest request) {
		List<CompanyDetail> companyDetailList = companyDetailService.getAllCompanyDetail();
		List<Map<String, String>> retList = new ArrayList<>();
		if(companyDetailList != null && companyDetailList.size() != 0) {
			for(CompanyDetail companyDetail : companyDetailList) {
				long companyId = companyDetail.getCompanyId();
				String companyName = companyService.getCompanyName(companyId);
				Map<String, String> map = new HashMap<>();
				map.put("companyId", String.valueOf(companyId));
				map.put("companyName", companyName);
				map.put("coporation", companyDetail.getCorporation());
				map.put("zhuzhiCode", companyDetail.getOrgCode());
				map.put("shuiwuCode", companyDetail.getTaxCode());
				map.put("bank", companyDetail.getBankName());
				map.put("address", companyDetail.getProvince()+companyDetail.getCity()+companyDetail.getArea()+companyDetail.getExtra());
				map.put("companyFile", companyDetail.getCompanyResFile());
				map.put("zhuZhiFile", companyDetail.getOrgCodeFile());
				map.put("shuiWuFile", companyDetail.getTaxCodeFile());
				retList.add(map);
			}
		}
		return retList;
	}
	
	@RequestMapping("/admin/getAllCompanyDetailInfoByName")
	@ResponseBody
	public List<Map<String, String>> getAllCompanyDetailInfoByName(HttpServletRequest request) {
		List<CompanyDetail> companyDetailList = companyDetailService.getAllCompanyDetail();
		List<Map<String, String>> retList = new ArrayList<>();
		String conditionValue = request.getParameter("conditionValue");
		if(companyDetailList != null && companyDetailList.size() != 0) {
			for(CompanyDetail companyDetail : companyDetailList) {
				long companyId = companyDetail.getCompanyId();
				String companyName = companyService.getCompanyName(companyId);
				if(companyName == null) continue;
				if(companyName.indexOf(conditionValue) == -1) continue;
				Map<String, String> map = new HashMap<>();
				map.put("companyName", companyName);
				map.put("companyId", String.valueOf(companyId));
				map.put("coporation", companyDetail.getCorporation());
				map.put("zhuzhiCode", companyDetail.getOrgCode());
				map.put("shuiwuCode", companyDetail.getTaxCode());
				map.put("bank", companyDetail.getBankName());
				map.put("address", companyDetail.getProvince()+companyDetail.getCity()+companyDetail.getArea()+companyDetail.getExtra());
				map.put("companyFile", companyDetail.getCompanyResFile());
				map.put("zhuZhiFile", companyDetail.getOrgCodeFile());
				map.put("shuiWuFile", companyDetail.getTaxCodeFile());
				retList.add(map);
			}
		}
		return retList;
	}
	
	@RequestMapping("/admin/getAllAccounterDetailInfo")
	@ResponseBody
	public List<Map<String, String>> getAllAccounterDetailInfo(HttpServletRequest request) {
		List<User> userList = userService.getAccounterUserList();
		List<Map<String, String>> retList = new ArrayList<>();
		if(userList != null && userList.size() != 0) {
			for(User user : userList) {
				
				Map<String, String> map = new HashMap<>();
				
				String date = user.getCreateTime().toString().substring(0,10);
				String userId = user.getUserId();
				String userName = user.getUserName() == null || user.getUserName().isEmpty() ? "未设定" : user.getUserName();
				UserPhone userPhone = userQueryDao.getUserPhoneByUserId(userId);
				String phone = userPhone == null || userPhone.getPhone() == null || userPhone.getPhone().isEmpty() ? "未设定" : userPhone.getPhone();
				map.put("date", date);
				map.put("userId", userId);
				map.put("userName", userName);
				map.put("phone", phone);
				
				int auditStatus = user.getAuditStatus();
				if(auditStatus == 0) {
					map.put("auditStatus", "正在审核");
				} else if(auditStatus == 1) {
					map.put("auditStatus", "审核通过");
				} else if(auditStatus == 2) {
					map.put("auditStatus", "审核未通过");
				}
				
				Map<String, String> accounterMap = accounterService.getAccounterByUserId(userId);
				if(accounterMap == null || accounterMap.size() == 0) {
					map.put("workTime", "未设定");
					map.put("workIndustry", "未设定");
					map.put("workCertificate", "未设定");
					map.put("workArea", "未设定");
					map.put("image", "none");
				} else {
					map.put("workTime", accounterMap.get("career"));
					map.put("workIndustry", accounterMap.get("industry"));
					map.put("workCertificate", accounterMap.get("certificate"));
					map.put("workArea", accounterMap.get("province")+accounterMap.get("city")+accounterMap.get("area"));
					map.put("image", accounterMap.get("image"));
				}
				retList.add(map);
			}
		}
		return retList;
	}
	
	@RequestMapping("/admin/downloadCompanyFile")
	public ResponseEntity<byte[]> downloadCompanyFile(HttpServletRequest request) {
		String userId = (String) request.getSession().getAttribute("userId");
		String cId = request.getParameter("companyId");
		long companyId = Long.valueOf(cId);
		//String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF");
		ResponseEntity<byte[]> re = null;
		CompanyDetail companyDetail = companyDetailService.getCompanyDetailByCompanyId(companyId);
		if (companyDetail == null)
			return null;
		String fileName = companyDetail.getCompanyResFile();
		fileName = fileName.substring(fileName.lastIndexOf("/")+1);
		File file = new File( "/home/files/company/",  fileName);
		try {
			HttpHeaders headers = new HttpHeaders();
			/*headers.set("Content-Disposition:", "attachment;filename=\"" + new String(
					fileName.getBytes("UTF-8"), "iso-8859-1") + "\"");  */
			headers.setContentDispositionFormData("attachment", new String(
					fileName.getBytes("UTF-8"), "iso-8859-1"));
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			re = new ResponseEntity<byte[]>(
					FileUtils.readFileToByteArray(file), headers,
					HttpStatus.OK);
		} catch (IOException e) {
			logger.debug("download exception : {}", e.toString());
		}
		return re;
	}
	
	@RequestMapping("/admin/downloadShuiWuFile")
	public ResponseEntity<byte[]> downloadShuiWuFile(HttpServletRequest request) {
		String userId = (String) request.getSession().getAttribute("userId");
		String cId = request.getParameter("companyId");
		long companyId = Long.valueOf(cId);
		//String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF");
		ResponseEntity<byte[]> re = null;
		CompanyDetail companyDetail = companyDetailService.getCompanyDetailByCompanyId(companyId);
		if (companyDetail == null)
			return null;
		String fileName = companyDetail.getTaxCodeFile();
		fileName = fileName.substring(fileName.lastIndexOf("/")+1);
		File file = new File( "/home/files/company/",  fileName);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentDispositionFormData("attachment", new String(
					fileName.getBytes("UTF-8"), "iso-8859-1"));
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			re = new ResponseEntity<byte[]>(
					FileUtils.readFileToByteArray(file), headers,
					HttpStatus.CREATED);
		} catch (IOException e) {
			logger.debug("download exception : {}", e.toString());
		}
		return re;
	}
	
	@RequestMapping("/admin/downloadZhuZhiFile")
	public ResponseEntity<byte[]> downloadZhuZhiFile(HttpServletRequest request) {
		String userId = (String) request.getSession().getAttribute("userId");
		String cId = request.getParameter("companyId");
		long companyId = Long.valueOf(cId);
		//String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF");
		ResponseEntity<byte[]> re = null;
		CompanyDetail companyDetail = companyDetailService.getCompanyDetailByCompanyId(companyId);
		if (companyDetail == null)
			return null;
		String fileName = companyDetail.getOrgCodeFile();
		fileName = fileName.substring(fileName.lastIndexOf("/")+1);
		File file = new File( "/home/files/company/",  fileName);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentDispositionFormData("attachment", new String(
					fileName.getBytes("UTF-8"), "iso-8859-1"));
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			re = new ResponseEntity<byte[]>(
					FileUtils.readFileToByteArray(file), headers,
					HttpStatus.CREATED);
		} catch (IOException e) {
			logger.debug("download exception : {}", e.toString());
		}
		return re;
	}

	// get company Info by company_id and user_type
	@RequestMapping("/admin/getCompanyAndAccounter")
	@ResponseBody
	public List<Company> getCompanyAndAccounter(HttpServletRequest request) {
			List <Company> companyList = new ArrayList<>();
			try{
				companyList = companyService.getCompanyAndAccounter();
				for(int i = 0;i < companyList.size();i ++){
					System.out.println(companyList.get(i).getAccounterId());
					if(companyList.get(i).getAccounterId()==null){
						companyList.get(i).setAccounterId("");;
					}
				}
			}catch(Exception e){
				logger.debug("getCompanyAndAccounter exception : {}", e.toString());
			}
		
		return companyList;
	}
	// get company Info by company_id and user_type
		@RequestMapping("/admin/getAccounterIdInfo")
		@ResponseBody
		public List<Accounter> getAccounterIdInfo(HttpServletRequest request) {
			List<Accounter> accounterList = new ArrayList<>();
			try{
				accounterList =accounterService.getAllAccounterList();
			}catch(Exception e){
				logger.debug("getAccounterIdInfo exception : {}", e.toString());
			}
			
			return accounterList;
		}
		
		// update company with accounter_id by company_id and user_type 
				@RequestMapping("/admin/updatCompanyAccounterIdInfo")
				@ResponseBody
				public Map<String, String> updatCompanyAccounterIdInfo(HttpServletRequest request) {
					Map<String, String> retMap = new HashMap<>();
					boolean updateBoolean = false;
					try{
						String companyId=request.getParameter("globalCompanyId");
						String accounterId=request.getParameter("selectValue");
						updateBoolean = companyService.updateAccounterForCompany(Long.parseLong(companyId), accounterId);
					}catch(Exception e){
						logger.debug("getAccounterIdInfo exception : {}", e.toString());
					}
					if(updateBoolean) {
						retMap.put("error_code", "0");
					} else {
						retMap.put("error_code", "1");
						retMap.put("error_message", "更新accounterId失败！");
					}
					return retMap;
				}	
				
				// get company by name
				@RequestMapping("/admin/getCompanyInfoByName")
				@ResponseBody
				public List<Company> getCompanyInfoByName(HttpServletRequest request) {
					List<Company>companyList = new ArrayList<>();
					try{
						String companyName=request.getParameter("company_name");
						companyList = companyService.getCompanyByName(companyName);
						for(int i = 0;i < companyList.size();i ++){
							System.out.println(companyList.get(i).getAccounterId());
							if(companyList.get(i).getAccounterId()==null){
								companyList.get(i).setAccounterId("");;
							}
						}
					}catch(Exception e){
						logger.debug("getAccounterIdInfo exception : {}", e.toString());
					}
					return companyList;
				}	
	
	@RequestMapping("/admin/getAllCompanyPayInfo")
	@ResponseBody
	public List<Map<String, String>> getAllCompanyPayInfo(HttpServletRequest request) {
		List<CompanyAndPayModel> companyAndPayModelList = companyPayService.getCompanyAndPayModel();
		List<Map<String, String>> retList = new ArrayList<>();
		if(companyAndPayModelList != null && companyAndPayModelList.size() != 0) {
			for(CompanyAndPayModel companyAndPayModel : companyAndPayModelList) {
				Map<String, String> itemMap = new HashMap<>();
				itemMap.put("company_id", String.valueOf(companyAndPayModel.getCompanyId()));
				itemMap.put("company_name", companyAndPayModel.getCompanyName());
				itemMap.put("company_service_time", companyAndPayModel.getServiceTime() == null ? "" : String.valueOf(companyAndPayModel.getServiceTime()));
				itemMap.put("company_service_amount", companyAndPayModel.getPayAmount() == null ? "" : String.valueOf(companyAndPayModel.getPayAmount()));
				itemMap.put("company_expired_time", companyAndPayModel.getExpiredTime() == null ? "" : String.valueOf(companyAndPayModel.getExpiredTime().toString().substring(0, 11)));
				retList.add(itemMap);
			}
		}
		return retList;
	}
	
	@RequestMapping("/admin/modifyCompanyPayInfo")
	@ResponseBody
	public Map<String, String> modifyCompanyPayInfo(HttpServletRequest request) {
		String serviceTime = request.getParameter("serviceTime");
		String serviceAmount = request.getParameter("serviceAmount");
		Long companyId = Long.valueOf(request.getParameter("companyId"));
		String serviceExpiredTime = request.getParameter("expiredTime");
		
		Map<String, String> retMap = new HashMap<>();
		CompanyPay cp = companyPayService.getCompanyPayByCompanyId(companyId);
		boolean success = false;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    Date parsedDate = null;
		try {
			parsedDate = dateFormat.parse(serviceExpiredTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(cp == null) {
			cp = new CompanyPay();
			cp.setCompanyId(companyId);
			cp.setPayAmount(Double.valueOf(serviceAmount));
			cp.setServiceTime(Integer.valueOf(serviceTime));
			if(parsedDate != null){
				Timestamp expiredTimestamp = new java.sql.Timestamp(parsedDate.getTime());
				cp.setExpiredTime(expiredTimestamp);
			}
			success = companyPayService.addCompanyPay(cp) != 0;
		} else {
			Timestamp expiredTimestamp = null;
			if(parsedDate != null){
				expiredTimestamp = new java.sql.Timestamp(parsedDate.getTime());
			}
			success = companyPayService.updateCompanyPayByCompanyId(companyId, 0, Double.valueOf(serviceAmount), "", Integer.valueOf(serviceTime), expiredTimestamp);
		}
		if(success) {
			retMap.put("result", "true");
		} else {
			retMap.put("result", "false");
		}
		return retMap;
	}
	
	@RequestMapping("/admin/getAllCompanyPayInfoByCompanyName")
	@ResponseBody
	public List<Map<String, String>> getAllCompanyPayInfoByCompanyName(HttpServletRequest request) {
		String companyName = request.getParameter("conditionValue");
		List<CompanyAndPayModel> companyAndPayModelList = companyPayService.getCompanyAndPayModelByCompanyName(companyName);
		List<Map<String, String>> retList = new ArrayList<>();
		if(companyAndPayModelList != null && companyAndPayModelList.size() != 0) {
			for(CompanyAndPayModel companyAndPayModel : companyAndPayModelList) {
				Map<String, String> itemMap = new HashMap<>();
				itemMap.put("company_id", String.valueOf(companyAndPayModel.getCompanyId()));
				itemMap.put("company_name", companyAndPayModel.getCompanyName());
				itemMap.put("company_service_time", companyAndPayModel.getServiceTime() == null ? "" : String.valueOf(companyAndPayModel.getServiceTime()));
				itemMap.put("company_service_amount", companyAndPayModel.getPayAmount() == null ? "" : String.valueOf(companyAndPayModel.getPayAmount()));
				itemMap.put("company_buy_time", companyAndPayModel.getCreateTime() == null ? "" : String.valueOf(companyAndPayModel.getCreateTime().toString().substring(0, 11)));
				retList.add(itemMap);
			}
		}
		return retList;
	}
}
