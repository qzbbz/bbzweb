package com.wisdom.web.api.impl;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.wisdom.common.model.Company;
import com.wisdom.common.model.CompanyBankSta;
import com.wisdom.common.model.CompanyBill;
import com.wisdom.common.model.CompanyDetail;
import com.wisdom.common.model.CompanySalary;
import com.wisdom.common.model.CompanySales;
import com.wisdom.common.model.CostCenter;
import com.wisdom.common.model.Dept;
import com.wisdom.common.model.SalarySocialSecurity;
import com.wisdom.common.model.User;
import com.wisdom.company.service.ICompanyBankStaService;
import com.wisdom.company.service.ICompanyDetailService;
import com.wisdom.company.service.ICompanySalaryService;
import com.wisdom.company.service.ICompanySalesService;
import com.wisdom.company.service.ICompanyService;
import com.wisdom.company.service.ICostCenterService;
import com.wisdom.company.service.IDeptService;
import com.wisdom.company.service.ISalarySocialSecurityService;
import com.wisdom.user.service.IUserModifyService;
import com.wisdom.user.service.IUserService;
import com.wisdom.web.api.ICompanyApi;
import com.wisdom.web.utils.CompanyOrgStructure;
import com.wisdom.web.utils.UserPwdMD5Encrypt;

@Service("companyDetailRegisterApiService")
public class CompanyApiImpl implements ICompanyApi {

	private static final Logger logger = LoggerFactory
			.getLogger(CompanyApiImpl.class);

	@Autowired
	private ICompanyService companyService;

	@Autowired
	private IDeptService deptService;
	
	@Autowired
	private ICostCenterService costCenterService;

	@Autowired
	private ICompanyDetailService companyDetailService;

	@Autowired
	private IUserService userService;

	@Autowired
	private IUserModifyService userModifyService;

	@Autowired
	private ISalarySocialSecurityService salarySocialSecurityService;

	@Autowired
	private ICompanySalaryService companySalaryService;

	@Autowired
	private ICompanyBankStaService companyBankStaService;
	
	@Autowired
	private ICompanySalesService companySalesService;

	@Override
	public Map<String, String> companyDetailRegister(Map<String, String> params) {
		Map<String, String> retMap = new HashMap<>();
/* TODO add user's email and password into session*/
		String userPwd = params.get("userPwd");
		String userId = params.get("userEmail");
		logger.debug("params : {}", params.toString());
		if (userPwd == null || userPwd.isEmpty() || userId == null
				|| userId.isEmpty()) {
			retMap.put("error_code", "1");
			retMap.put("error_message", "系统暂时无法获取您的用户名和密码，请重登陆页面重新进入，完成注册！");
			return retMap;
		}

		String dbPwd = userService.getUserPwdByUserId(userId);
		String encPwd = UserPwdMD5Encrypt.getPasswordByMD5Encrypt(userPwd);
		if (dbPwd.isEmpty() || !encPwd.equals(dbPwd)) {
			retMap.put("error_code", "1");
			retMap.put("error_message", "系统暂时无法获取您的用户名和密码，请重登陆页面重新进入，完成注册！");
			return retMap;
		}

		String userCompanyName = params.get("userCompanyName");
		String userCompanyIncomes = params.get("userCompanyIncomes");
		String userCalledTime = params.get("userCalledTime");
		String userPhone = params.get("userPhone");
		String userName = params.get("userName");
		if (userCompanyName == null || userCompanyName.isEmpty()
				|| userCompanyIncomes == null || userCompanyIncomes.isEmpty()
				|| userCalledTime == null || userCalledTime.isEmpty()
				|| userPhone == null || userPhone.isEmpty() || userName == null
				|| userName.isEmpty()) {
			retMap.put("error_code", "2");
			retMap.put("error_message", "系统检测到您提交的信息存在缺失，请返回检查！");
			return retMap;
		}

		Company company = new Company();
		company.setName(userCompanyName);
		company.setMonthExpense(userCompanyIncomes);
		company.setParentId((long) -1);
		company.setPerfectMoment(userCalledTime);
		company.setCreateTime(new Timestamp(System.currentTimeMillis()));
		long companyId = companyService.addCompany(company);
		logger.debug("add company id : {}", companyId);
		userModifyService.modifyUserCompanyIdByUserId(userId, companyId);
		userService.addUserPhone(userId, userPhone, 1);
		retMap.put("error_code", "0");
		return retMap;
	}

	@Override
	public Map<String, String> companyInfoSettings(MultipartFile[] files,
			Map<String, String> params) {
		logger.debug("params : {}", params.toString());
		Map<String, String> retMap = new HashMap<>();
		String userId = params.get("userId");
		String realPath = params.get("realPath");
		Long companyId = userService.getCompanyIdByUserId(userId);
		if (!companyService.updateCompanyName(params.get("companyName"),
				companyId)) {
			retMap.put("error_code", "1");
			retMap.put("error_message", "更新公司名称出错，请稍后重试！");
			return retMap;
		}
		if (files.length != 3) {
			retMap.put("error_code", "2");
			retMap.put("error_message", "上传文件的数量不正确，请检查重试！");
			return retMap;
		}
		String path = "/files/company/";
		CompanyDetail companyDetail = new CompanyDetail();
		companyDetail.setCompanyId(companyId);
		companyDetail.setBankName(params.get("bankName"));
		companyDetail.setCorporation(params.get("corparation"));
		companyDetail.setOrgCode(params.get("orgCode"));
		companyDetail.setProvince(params.get("province"));
		companyDetail.setCity(params.get("city"));
		companyDetail.setArea(params.get("area"));
		companyDetail.setExtra(params.get("addExtra"));
		companyDetail.setTaxCode(params.get("taxCode"));
		companyDetail.setCreateTime(new Timestamp(System.currentTimeMillis()));
		companyDetail.setCompanyResFile(params.get("companyResFile"));
		companyDetail.setOrgCodeFile(params.get("orgCodeFile"));
		companyDetail.setTaxCodeFile(params.get("taxCodeFile"));
		try {
			String fileName = getGernarateFileName(files[0], userId);
			FileUtils.copyInputStreamToFile(files[0].getInputStream(),
					new File(realPath, fileName));
			companyDetail.setCompanyResFile(path + fileName);
			fileName = getGernarateFileName(files[0], userId);
			FileUtils.copyInputStreamToFile(files[1].getInputStream(),
					new File(realPath, fileName));
			companyDetail.setOrgCodeFile(path + fileName);
			fileName = getGernarateFileName(files[0], userId);
			FileUtils.copyInputStreamToFile(files[2].getInputStream(),
					new File(realPath, fileName));
			companyDetail.setTaxCodeFile(path + fileName);
			if (companyDetailService.getCompanyDetailByCompanyId(companyId) == null) {
				companyDetailService.addCompanyDetail(companyDetail);
			} else {
				companyDetailService.updateCompanyDetail(companyDetail);
			}
			retMap.put("error_code", "0");
		} catch (IOException e) {
			logger.debug("failed in uploading file, exception : {}",
					e.toString());
			retMap.put("error_code", "4");
			retMap.put("error_message", "上传文件失败，请稍后重试！");
		}
		return retMap;
	}

	private String getGernarateFileName(MultipartFile file, String userId) {
		Random rdm = new Random(System.currentTimeMillis());
		String extendName = file.getOriginalFilename().substring(
				file.getOriginalFilename().lastIndexOf(".") + 1);
		return userId + System.currentTimeMillis() + Math.abs(rdm.nextInt())
				% 1000 + (extendName == null ? ".unknown" : "." + extendName);
	}

	@Override
	public Map<String, String> getSSSInfo(String userId, String cityName,
			String type) {
		Map<String, String> retMap = new HashMap<>();
		long companyId = userService.getCompanyIdByUserId(userId);
		SalarySocialSecurity sss = salarySocialSecurityService
				.getSSSByCompanyIdAndCityNameAndRegType(companyId, cityName,
						Integer.valueOf(type));
		if (sss == null)
			sss = new SalarySocialSecurity();
		retMap.put("pensionCratio",
				sss.getPensionCratio() == null ? "0" : sss.getPensionCratio());
		retMap.put("pensionPratio",
				sss.getPensionPratio() == null ? "0" : sss.getPensionPratio());
		retMap.put("pensionBase",
				sss.getPensionBase() == null ? "0" : sss.getPensionBase());
		retMap.put("medicalCratio",
				sss.getMedicalCratio() == null ? "0" : sss.getMedicalCratio());
		retMap.put("medicalPratio",
				sss.getMedicalPratio() == null ? "0" : sss.getMedicalPratio());
		retMap.put("medicalBase",
				sss.getMedicalBase() == null ? "0" : sss.getMedicalBase());
		retMap.put("unemployCratio", sss.getUnemployCratio() == null ? "0"
				: sss.getUnemployCratio());
		retMap.put("unemployPratio", sss.getUnemployPratio() == null ? "0"
				: sss.getUnemployPratio());
		retMap.put("unemployBase",
				sss.getUnemployBase() == null ? "0" : sss.getUnemployBase());
		retMap.put("injuryCratio",
				sss.getInjuryCratio() == null ? "0" : sss.getInjuryCratio());
		retMap.put("injuryPratio",
				sss.getInjuryPratio() == null ? "0" : sss.getInjuryPratio());
		retMap.put("injuryBase",
				sss.getInjuryBase() == null ? "0" : sss.getInjuryBase());
		retMap.put("birthCratio",
				sss.getBirthCratio() == null ? "0" : sss.getBirthCratio());
		retMap.put("birthPratio",
				sss.getBirthPratio() == null ? "0" : sss.getBirthPratio());
		retMap.put("birthBase",
				sss.getBirthBase() == null ? "0" : sss.getBirthBase());
		retMap.put("accfundCratio",
				sss.getAccfundCratio() == null ? "0" : sss.getAccfundCratio());
		retMap.put("accfundPratio",
				sss.getAccfundPratio() == null ? "0" : sss.getAccfundPratio());
		retMap.put("accfundBase",
				sss.getAccfundBase() == null ? "0" : sss.getAccfundBase());
		retMap.put("salaryCratio",
				sss.getSalaryCratio() == null ? "0" : sss.getSalaryCratio());
		retMap.put("salaryPratio",
				sss.getSalaryPratio() == null ? "0" : sss.getSalaryPratio());
		retMap.put("salaryBase",
				sss.getSalaryBase() == null ? "0" : sss.getSalaryBase());
		retMap.put("bigmedicalCratio", sss.getBigmedicalCratio() == null ? "0"
				: sss.getBigmedicalCratio());
		retMap.put("bigmedicalPratio", sss.getBigmedicalPratio() == null ? "0"
				: sss.getBigmedicalPratio());
		retMap.put("bigmedicalBase", sss.getBigmedicalBase() == null ? "0"
				: sss.getBigmedicalBase());
		retMap.put("currentCity",
				sss.getCityName() == null ? "" : sss.getCityName());
		retMap.put(
				"currentType",
				sss.getRegistryType() == null ? "0" : String.valueOf(sss
						.getRegistryType()));
		return retMap;
	}

	@Override
	public Map<String, String> updateSSSInfo(Map<String, String> params) {
		Map<String, String> retMap = new HashMap<>();
		String userId = params.get("userId");
		String cityName = params.get("currentCity");
		String currentType = params.get("currentType");
		long companyId = userService.getCompanyIdByUserId(userId);
		SalarySocialSecurity sss = salarySocialSecurityService
				.getSSSByCompanyIdAndCityNameAndRegType(companyId, cityName,
						Integer.valueOf(currentType));
		boolean isExist = sss != null;
		if (!isExist)
			sss = new SalarySocialSecurity();
		sss.setPensionCratio(params.get("pensionCratio"));
		sss.setPensionPratio(params.get("pensionPratio"));
		sss.setPensionBase(params.get("pensionBase"));
		sss.setMedicalCratio(params.get("medicalCratio"));
		sss.setMedicalPratio(params.get("medicalPratio"));
		sss.setMedicalBase(params.get("medicalBase"));
		sss.setUnemployCratio(params.get("unemployCratio"));
		sss.setUnemployPratio(params.get("unemployPratio"));
		sss.setUnemployBase(params.get("unemployBase"));
		sss.setInjuryCratio(params.get("injuryCratio"));
		sss.setInjuryPratio(params.get("injuryPratio"));
		sss.setInjuryBase(params.get("injuryBase"));
		sss.setBirthCratio(params.get("birthCratio"));
		sss.setBirthPratio(params.get("birthPratio"));
		sss.setBirthBase(params.get("birthBase"));
		sss.setAccfundCratio(params.get("accfundCratio"));
		sss.setAccfundPratio(params.get("accfundPratio"));
		sss.setAccfundBase(params.get("accfundBase"));
		sss.setSalaryCratio(params.get("salaryCratio"));
		sss.setSalaryPratio(params.get("salaryPratio"));
		sss.setSalaryBase(params.get("salaryBase"));
		sss.setBigmedicalCratio(params.get("bigmedicalCratio"));
		sss.setBigmedicalPratio(params.get("bigmedicalPratio"));
		sss.setBigmedicalBase(params.get("bigmedicalBase"));
		sss.setCompanyId(companyId);
		sss.setRegistryType(Integer.valueOf(currentType));
		sss.setCityName(cityName);
		if (isExist) {
			salarySocialSecurityService.updateSalarySocialSecurity(sss);
		} else {
			salarySocialSecurityService.addSalarySocialSecurity(sss);
		}
		retMap.clear();
		retMap.put("error_code", "0");
		return retMap;
	}

	@Override
	public ResponseEntity<byte[]> downloadSalarySocialSecurityTemplate(
			String userId, String realPath) {
		ResponseEntity<byte[]> re = null;
		long companyId = userService.getCompanyIdByUserId(userId);
		List<SalarySocialSecurity> sssList = salarySocialSecurityService.getSSSByCompanyId(companyId);
		if (sssList == null || sssList.size() ==0)
			return null;
		File file = new File(realPath + "/" + sssList.get(0).getTemplate());
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentDispositionFormData("attachment", new String(sssList.get(0)
					.getTemplate().getBytes("UTF-8"), "iso-8859-1"));
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			re = new ResponseEntity<byte[]>(
					FileUtils.readFileToByteArray(file), headers,
					HttpStatus.CREATED);
		} catch (IOException e) {
			logger.debug("download exception : {}", e.toString());
		}
		return re;
	}

	@Override
	public Map<String, String> checkTemplateExist(String userId,
			String cityName, String type) {
		Map<String, String> retMap = new HashMap<>();
		long companyId = userService.getCompanyIdByUserId(userId);
		List<SalarySocialSecurity> sssList = salarySocialSecurityService.getSSSByCompanyId(companyId);
		if (sssList == null || sssList.size() == 0) {
			retMap.put("error_code", "1");
			retMap.put("error_message", "没有满足当前条件的模板，请尝试更换条件！");
		} else {
			retMap.put("error_code", "0");
		}
		return retMap;
	}

	@Override
	public Map<String, String> uploadCompanySalary(String userId,
			String realPath, MultipartFile file,String date) {
		Map<String, String> retMap = new HashMap<>();
		try {
			long companyId = userService.getCompanyIdByUserId(userId);
			String fileName = getGernarateFileName(file, userId);
			FileUtils.copyInputStreamToFile(file.getInputStream(), new File(
					realPath, fileName));
			CompanySalary cs = new CompanySalary();
			cs.setCompanyId(companyId);
			cs.setSalaryFile(fileName);
			cs.setSalaryDate(date);
			cs.setCreateTime(new Timestamp(System.currentTimeMillis()));
			companySalaryService.addCompanySalary(cs);
			retMap.put("error_code", "0");
		} catch (IOException e) {
			logger.debug("upload salary exception : {}", e.toString());
			retMap.put("error_code", "1");
			retMap.put("error_message", "上传工资单失败，请稍后重试！");
		}
		return null;
	}

	@Override
	public Map<String, String> uploadCompanyBankSta(MultipartFile file,
			Map<String, String> params) {
		Map<String, String> retMap = new HashMap<>();
		try {
			String userId = params.get("userId");
			long companyId = userService.getCompanyIdByUserId(userId);
			
			String orginFileName = file.getOriginalFilename();
				
			String fileName = getGernarateFileName(file, userId);
			FileUtils.copyInputStreamToFile(file.getInputStream(), new File(
					params.get("realPath"), fileName));
			
			CompanyBankSta cbs = new CompanyBankSta();
			cbs.setDate(params.get("date"));
			if(orginFileName.indexOf("test1") != -1) {
				cbs.setCompanyId(companyId);
				cbs.setIdeDate("2015-03-26");
				cbs.setIdeName("上海华振物流有限公司");
				cbs.setIdeAccount("1001242719300471748");
				cbs.setIdeBankName("中国工商银行");
				cbs.setFileName(fileName);
				cbs.setIdentifyStatus(1);
				cbs.setCreateTime(new Timestamp(System.currentTimeMillis()));
			} else if(orginFileName.indexOf("test2") != -1) {
				cbs.setCompanyId(companyId);
				cbs.setIdeDate("2015-03-15");
				cbs.setIdeName("上海华振物流有限公司");
				cbs.setIdeAccount("1001190719916303802");
				cbs.setIdeBankName("中国工商银行");
				cbs.setFileName(fileName);
				cbs.setIdentifyStatus(1);
				cbs.setCreateTime(new Timestamp(System.currentTimeMillis()));
			} else {
				cbs.setCompanyId(companyId);
				cbs.setFileName(fileName);
				cbs.setIdentifyStatus(0);
				cbs.setCreateTime(new Timestamp(System.currentTimeMillis()));
			}
			
			companyBankStaService.addCompanyBankSta(cbs);
			retMap.put("error_code", "0");
		} catch (IOException e) {
			logger.debug("uploadCompanyBankSta exception : {}", e.toString());
			retMap.put("error_code", "1");
			retMap.put("error_message", "上传对账单失败，请稍后重试！");
		}
		return retMap;
	}

	@Override
	public List<Map<String, String>> getCompanyBankStaByCondition(
			Map<String, String> params) {
		String userId = params.get("userId");
		if (userId == null || userId.isEmpty())
			return null;
		long companyId = userService.getCompanyIdByUserId(userId);
		if (companyId <= 0)
			return null;
		if (params.get("conditionType") == null
				|| params.get("conditionType").isEmpty()
				|| ("0").equals(params.get("conditionType"))
				|| params.get("conditionValue") == null
				|| params.get("conditionValue").isEmpty()) {
			List<CompanyBankSta> list = companyBankStaService
					.getAllCompanyBankSta(companyId);
			return createCompanyBankStaList(list);
		}
		if (("1").equals(params.get("conditionType"))
				&& params.get("conditionValue") != null) {
			List<CompanyBankSta> list = companyBankStaService
					.getAllCompanyBankStaByDate(companyId,
							params.get("conditionValue"));
			return createCompanyBankStaList(list);
		}
		if (("2").equals(params.get("conditionType"))
				&& params.get("conditionValue") != null) {
			List<CompanyBankSta> list = companyBankStaService
					.getAllCompanyBankStaByIdentifyStatus(companyId,
							Integer.valueOf(params.get("conditionValue")));
			return createCompanyBankStaList(list);
		}
		return null;
	}

	private List<Map<String, String>> createCompanyBankStaList(
			List<CompanyBankSta> list) {
		List<Map<String, String>> retList = new ArrayList<>();
		if (list == null)
			return null;
		for (CompanyBankSta cbs : list) {
			Map<String, String> map = new HashMap<>();
			map.put("date", cbs.getDate());
			map.put("id", String.valueOf(cbs.getId()));
			map.put("ide_name",
					cbs.getIdeName() == null ? "" : cbs.getIdeName());
			map.put("ide_account",
					cbs.getIdeAccount() == null ? "" : cbs.getIdeAccount());
			map.put("ide_bank_name",
					cbs.getIdeBankName() == null ? "" : cbs.getIdeBankName());
			map.put("file_name",
					cbs.getFileName() == null ? "" : cbs.getFileName());
			map.put("status",
					String.valueOf(cbs.getIdentifyStatus() == null ? 0 : cbs
							.getIdentifyStatus()));
			map.put("ide_date",
					cbs.getIdeDate() == null ? "" : cbs.getIdeDate());
			retList.add(map);
		}
		return retList.size() > 0 ? retList : null;
	}

	@Override
	public Map<String, String> getCompanyDetailInfo(String userId) {
		Map<String, String> retMap = new HashMap<>();
		long companyId = userService.getCompanyIdByUserId(userId);
		String companyName = companyService.getCompanyName(companyId);
		String parentCompanyName = companyService
				.getParentCompanyNameByCompanyId(companyId);
		CompanyDetail detail = companyDetailService
				.getCompanyDetailByCompanyId(companyId);
		if (detail == null)
			return retMap;
		retMap.put("company_name", companyName == null ? "" : companyName);
		retMap.put("company_id", String.valueOf(companyId));
		retMap.put("org_code",
				detail.getOrgCode() == null ? "" : detail.getOrgCode());
		retMap.put("corporation",
				detail.getCorporation() == null ? "" : detail.getCorporation());
		retMap.put("province",
				detail.getProvince());
		retMap.put("city",
				detail.getCity());
		retMap.put("area",
				detail.getArea());
		retMap.put("extra",
				detail.getExtra());
		retMap.put("companyResFile", detail.getCompanyResFile());
		retMap.put("orgCodeFile", detail.getOrgCodeFile());
		retMap.put("taxCodeFile", detail.getTaxCodeFile());
		retMap.put("bank_name",
				detail.getBankName() == null ? "" : detail.getBankName());
		retMap.put("tax_code",
				detail.getTaxCode() == null ? "" : detail.getTaxCode());
		retMap.put("parent_company_name", parentCompanyName);
		return retMap;
	}

	@Override
	public List<CompanyOrgStructure> getOrgStructureData(String userId) {
		long rootCompanyId = userService.getCompanyIdByUserId(userId);
		
		List<CompanyOrgStructure> cosList = new ArrayList<>();
		
		List<Dept> rootDeptList = deptService.getDeptListByCompanyId(rootCompanyId);
		if(rootDeptList != null && rootDeptList.size() != 0) {
			Collections.sort(rootDeptList, new Comparator<Dept>() {
	            public int compare(Dept arg0, Dept arg1) {
	                return arg0.getLevel().compareTo(arg1.getLevel());
	            }
	        });
			for(Dept dept : rootDeptList) {
				if (dept.getLevel() == 1) {
					CompanyOrgStructure cos = new CompanyOrgStructure();
					cos.setCompanyId(String.valueOf(dept.getCompanyId()));
					cos.setCostCenter(String.valueOf(dept.getCostCenterEncode()));
					cos.setId(String.valueOf(dept.getId()));
					cos.setText(dept.getName());
					cos.setLevel(dept.getLevel());
					cos.setTypeIsDept(true);
					cosList.add(cos);
				} else {
					addDeptToCos(dept, cosList);
				}
			}
		}
		
		List<Company> subCompanyList = companyService.getSubCompanyListByCompanyId(rootCompanyId);
		if(subCompanyList != null && subCompanyList.size() != 0) {
			for(Company company : subCompanyList) {
				CompanyOrgStructure cosTemp = new CompanyOrgStructure();
				cosTemp.setCompanyId(String.valueOf(company.getId()));
				cosTemp.setParentCompanyId(String.valueOf(company.getParentId()));
				cosTemp.setText(company.getName());
				cosTemp.setTypeIsSubCompany(true);
				cosList.add(cosTemp);
				List<Dept> subDeptList = deptService.getDeptListByCompanyId(company.getId());
				if(subDeptList != null && subDeptList.size() != 0) {
					Collections.sort(subDeptList, new Comparator<Dept>() {
			            public int compare(Dept arg0, Dept arg1) {
			                return arg0.getLevel().compareTo(arg1.getLevel());
			            }
			        });
					for(Dept dept : subDeptList) {
						if (dept.getLevel() == 1) {
							for(CompanyOrgStructure temp : cosList) {
								if(String.valueOf(dept.getCompanyId()).equals(temp.getCompanyId())) {
									List<CompanyOrgStructure> subTemp = temp.getChildren();
									CompanyOrgStructure cos = new CompanyOrgStructure();
									cos.setCompanyId(String.valueOf(dept.getCompanyId()));
									cos.setCostCenter(String.valueOf(dept.getCostCenterEncode()));
									cos.setId(String.valueOf(dept.getId()));
									cos.setText(dept.getName());
									cos.setLevel(dept.getLevel());
									cos.setTypeIsDept(true);
									subTemp.add(cos);
									break;
								}
							}
						} else {
							addDeptToCos(dept, cosList);
						}
					}
				}
			}
		}
		return cosList;
	}
	
	private void addDeptToCos(Dept dept,
			List<CompanyOrgStructure> cosList) {
		if(cosList == null || cosList.size() == 0) return;
		for (CompanyOrgStructure cos : cosList) {
			logger.debug("cos:{}", cos.toString());
			if (cos.getId() != null && Long.valueOf(cos.getId()) == dept.getParentId()) {
				List<CompanyOrgStructure> subCosList = cos.getChildren();
				if (subCosList == null)
					subCosList = new ArrayList<>();
				CompanyOrgStructure cosTemp = new CompanyOrgStructure();
				cosTemp.setCompanyId(String.valueOf(dept.getCompanyId()));
				cosTemp.setCostCenter(String.valueOf(dept.getCostCenterEncode()));
				cosTemp.setId(String.valueOf(dept.getId()));
				cosTemp.setText(dept.getName());
				cosTemp.setLevel(dept.getLevel());
				cosTemp.setTypeIsDept(true);
				subCosList.add(cosTemp);
				break;
			} else {
				addDeptToCos(dept, cos.getChildren());
			}
		}
	}

	@Override
	public List<Map<String, String>> getAllCostCenterCode() {
		List<CostCenter> costCenterList = costCenterService.getAllCostCenter();
		List<Map<String, String>> retList = new ArrayList<>();
		for(CostCenter costCenter : costCenterList) {
			Map<String, String> map = new HashMap<>();
			map.put("id", String.valueOf(costCenter.getId()));
			map.put("encode", String.valueOf(costCenter.getEncode()));
			map.put("name", costCenter.getName());
			map.put("alias", costCenter.getAlias());
			retList.add(map);
		}
		return retList;
	}

	@Override
	public List<Map<String, String>> getSalaryTemplateHistory(long companyId) {
		List<SalarySocialSecurity> sssList = salarySocialSecurityService.getSSSByCompanyId(companyId);
		List<Map<String, String>> retList = new ArrayList<>();
		String companyName = companyService.getCompanyName(companyId);
		for(SalarySocialSecurity salarySocialSecurity : sssList) {
			Map<String, String> map = new HashMap<>();
			map.put("date", salarySocialSecurity.getCreateTime().toString().substring(0, 10));
			map.put("companyName", companyName);
			map.put("cityName", salarySocialSecurity.getCityName());
			map.put("type", salarySocialSecurity.getRegistryType() == 0?"城镇户籍" : "农村户籍");
			map.put("file_name", salarySocialSecurity.getTemplate());
			retList.add(map);
		}
		return retList;
	}

	@Override
	public List<Map<String, String>> getSalaryHistory(long companyId) {
		List<CompanySalary> salaryList = companySalaryService.getAllCompanySalary(companyId);
		List<Map<String, String>> retList = new ArrayList<>();
		String companyName = companyService.getCompanyName(companyId);
		for(CompanySalary companySalary : salaryList) {
			Map<String, String> map = new HashMap<>();
			map.put("id", String.valueOf(companySalary.getId()));
			map.put("date", companySalary.getCreateTime().toString().substring(0, 10));
			map.put("companyName", companyName);
			map.put("fileName", companySalary.getSalaryFile());
			retList.add(map);
		}
		return retList;
	}

	@Override
	public boolean deleteCompanyBill(String idList, String realPath) {
		String[] ids = idList.split(",");
		for (String id : ids) {
			CompanyBankSta  cbs = companyBankStaService.getCompanyBankStaById(Long.valueOf(id));
			if(cbs == null) continue;
			File file = new File(realPath + "/" + cbs.getFileName());
			FileUtils.deleteQuietly(file);
			companyBankStaService.deleteCompanyBankStaByCompanyId(Long.valueOf(id));
		}
		return true;
	}

	@Override
	public Map<String, String> uploadCompanySales(MultipartFile file,
			Map<String, String> params) {
		Map<String, String> retMap = new HashMap<>();
		try {
			String userId = params.get("userId");
			String date=params.get("date");
			long companyId = userService.getCompanyIdByUserId(userId);
			
			String orginFileName = file.getOriginalFilename();
				
			String fileName = getGernarateFileName(file, userId);
			FileUtils.copyInputStreamToFile(file.getInputStream(), new File(
					params.get("realPath"), fileName));
			
			CompanySales cs = new CompanySales();
			cs.setCompanyId(companyId);
			cs.setFileName(fileName);
			cs.setSalesDate(date);
			cs.setCreateTime(new Timestamp(System.currentTimeMillis()));
			companySalesService.addCompanySales(cs);
			retMap.put("error_code", "0");
		} catch (IOException e) {
			logger.debug("uploadCompanyBankSta exception : {}", e.toString());
			retMap.put("error_code", "1");
			retMap.put("error_message", "上传销售清单失败，请稍后重试！");
		}
		return retMap;
	}

	@Override
	public List<Map<String, String>> getCompanySalesByCondition(
			Map<String, String> params) {
		String userId = params.get("userId");
		if (userId == null || userId.isEmpty())
			return null;
		long companyId = userService.getCompanyIdByUserId(userId);
		if (companyId <= 0)
			return null;
		String companyName = companyService.getCompanyName(companyId);
		if (params.get("conditionType") == null
				|| params.get("conditionType").isEmpty()
				|| ("0").equals(params.get("conditionType"))
				|| params.get("conditionValue") == null
				|| params.get("conditionValue").isEmpty()) {
			List<CompanySales> list = companySalesService
					.getAllCompanySales(companyId);
			return createCompanySalesList(list, companyName);
		}
		if (("1").equals(params.get("conditionType"))
				&& params.get("conditionValue") != null) {
			List<CompanySales> list = companySalesService
					.getAllCompanySalesByDate(companyId,
							params.get("conditionValue"));
			return createCompanySalesList(list, companyName);
		}
		return null;
	}
	
	private List<Map<String, String>> createCompanySalesList(
			List<CompanySales> list, String companyName) {
		List<Map<String, String>> retList = new ArrayList<>();
		if (list == null)
			return null;
		for (CompanySales cs : list) {
			Map<String, String> map = new HashMap<>();
			map.put("company_name", companyName);
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			map.put("date", formatter.format(cs.getCreateTime()));
			map.put("file_name", cs.getFileName());
			map.put("id", String.valueOf(cs.getId()));
			retList.add(map);
		}
		return retList.size() > 0 ? retList : null;
	}

	@Override
	public boolean deleteCompanySales(String idList, String realPath) {
		String[] ids = idList.split(",");
		for (String id : ids) {
			CompanySales cs = companySalesService.getCompanySalesById(Long.valueOf(id));
			if(cs == null) continue;
			File file = new File(realPath + "/" + cs.getFileName());
			FileUtils.deleteQuietly(file);
			companySalesService.deleteCompanySalesById(Long.valueOf(id));
		}
		return true;
	}

	@Override
	public boolean deleteCompanySalary(String idList, String realPath) {
		String[] ids = idList.split(",");
		for (String id : ids) {
			CompanySalary cs = companySalaryService.getCompanySalaryById(Long.valueOf(id));
			if(cs == null) continue;
			File file = new File(realPath + "/" + cs.getSalaryFile());
			FileUtils.deleteQuietly(file);
			companySalaryService.deleteCompanySalaryById(Long.valueOf(id));
		}
		return true;
	}

	@Override
	public boolean setTakeType(long companyId, String takeType) {
		return companyService.updateCompanyTakeType(companyId, takeType);
	}

	@Override
	public List<Map<String, String>> getAllUser(long companyId) {
		List<Map<String, String>> retList = new ArrayList<>();
		List<Company> subCompanyList = companyService.getSubCompanyListByCompanyId(companyId);
		List<User> userList = userService.getUserListByCompanyId(companyId);
		for(Company company : subCompanyList) {
			userList.addAll(userService.getUserListByCompanyId(company.getId()));
		}
		for(User user : userList) {
			if(user.getUserName() == null || user.getUserName().isEmpty()) continue;
			Map<String, String> userMap = new HashMap<>();
			userMap.put("userId", user.getUserId());
			userMap.put("userName", user.getUserName());
			retList.add(userMap);
		}
		return retList;
	}
}