package com.wisdom.accounter.service.impl;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.wisdom.accounter.dao.IAccounterCareerDao;
import com.wisdom.accounter.dao.IAccounterCertificateDao;
import com.wisdom.accounter.dao.IAccounterDao;
import com.wisdom.accounter.dao.IAccounterIndustryDao;
import com.wisdom.accounter.service.IAccounterService;
import com.wisdom.common.model.Accounter;
import com.wisdom.common.model.AccounterCareer;
import com.wisdom.common.model.AccounterCertificate;
import com.wisdom.common.model.AccounterIndustry;
import com.wisdom.common.model.Company;
import com.wisdom.common.model.CompanyBankSta;
import com.wisdom.common.model.CompanyBill;
import com.wisdom.common.model.CompanySalary;
import com.wisdom.common.model.Invoice;
import com.wisdom.common.model.SalarySocialSecurity;
import com.wisdom.common.model.User;
import com.wisdom.common.model.UserInvoice;
import com.wisdom.company.dao.ICompanyBankStaDao;
import com.wisdom.company.dao.ICompanyBillDao;
import com.wisdom.company.dao.impl.CompanyBillDaoImpl;
import com.wisdom.company.service.ICompanySalaryService;
import com.wisdom.company.service.ICompanyService;
import com.wisdom.company.service.IExpenseTypeService;
import com.wisdom.company.service.ISalarySocialSecurityService;
import com.wisdom.invoice.service.IInvoiceService;
import com.wisdom.invoice.service.IUserInvoiceService;
import com.wisdom.user.service.IUserService;

@Service("accounterService")
public class AccounterServiceImpl implements IAccounterService {

	@Autowired
	private IAccounterCareerDao accounterCareerDao;

	@Autowired
	private IAccounterCertificateDao accounterCertificateDao;

	@Autowired
	private IAccounterIndustryDao accounterIndustryDao;

	@Autowired
	private IAccounterDao accounterDao;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private ICompanyBankStaDao companyBankStaDao;
	
	@Autowired
	private ICompanyBillDao companyBillDao;
	
	@Autowired
	private ICompanySalaryService companySalaryService;
	
	@Autowired
	private IUserInvoiceService userInvoiceService;
	
	@Autowired
	private IInvoiceService invoiceService;
	
	@Autowired
	private ICompanyService companyService;
	
	@Autowired
	private IExpenseTypeService expenseTypeService;
	
	@Autowired
	private ISalarySocialSecurityService salarySocialSecurityService;

	private static final Logger logger = LoggerFactory
			.getLogger(AccounterServiceImpl.class);
	
	@Override
	public Map<Long, String> getAllAccounterCareer() {
		Map<Long, String> careerMap = new HashMap<>();
		List<AccounterCareer> careerList = accounterCareerDao
				.getAllAccounterCareer();
		if (careerList != null && careerList.size() > 0) {
			for (AccounterCareer career : careerList) {
				careerMap.put(career.getId(), career.getName());
			}
		}
		return careerMap;
	}

	@Override
	public Map<Long, String> getAllAccounterCertificate() {
		Map<Long, String> certificateMap = new HashMap<>();
		List<AccounterCertificate> certificateList = accounterCertificateDao
				.getAllAccounterCertificate();
		if (certificateList != null && certificateList.size() > 0) {
			for (AccounterCertificate certificate : certificateList) {
				certificateMap.put(certificate.getId(), certificate.getName());
			}
		}
		return certificateMap;
	}

	@Override
	public Map<Long, String> getAllAccounterIndustry() {
		Map<Long, String> industryMap = new HashMap<>();
		List<AccounterIndustry> industryList = accounterIndustryDao
				.getAllAccounterIndustry();
		if (industryList != null && industryList.size() > 0) {
			for (AccounterIndustry industry : industryList) {
				industryMap.put(industry.getId(), industry.getName());
			}
		}
		return industryMap;
	}

	@Override
	public Map<String, String> getAccounterByUserId(String userId) {
		Map<String, String> accounterInfoMap = new HashMap<>();
		Accounter accounter = accounterDao.getAccounterByUserId(userId);
		if (accounter != null) {
			accounterInfoMap = accounterConvertToMap(accounter);
		}
		return accounterInfoMap;
	}

	@Override
	public boolean updateAccounter(Accounter accounter) {
		boolean updateSuccess = false;
		if(accounterDao.isAccounterExistByUserId(accounter.getUserId())) {
			updateSuccess = accounterDao.updateAccounter(accounter);
		} else {
			accounter.setCreateTime(new Timestamp(System.currentTimeMillis()));
			updateSuccess = accounterDao.addAccounter(accounter);
		}
		return updateSuccess;
	}

	@Override
	public List<Map<String, String>> getAllAccounter() {
		List<Map<String, String>> retList = new ArrayList<>();
		List<Accounter> list = accounterDao.getAllAccounter();
		if(list != null && list.size() > 0) {
			for(Accounter accounter : list)
				retList.add(accounterConvertToMap(accounter));
		}
		return retList;
	}
	
	private Map<String, String> accounterConvertToMap(Accounter accounter) {
		Map<String, String> map = new HashMap<>();
		map.put("user_id", accounter.getUserId());
		map.put("area", accounter.getArea());
		map.put("city", accounter.getCity());
		map.put("province", accounter.getProvince());
		map.put("image", accounter.getImage());
		map.put("certificate", accounter.getCertificate());
		map.put("industry", accounter.getIndustry());
		map.put("career", accounter.getCareer());
		String userName = userService.getUserNameByUserId(accounter.getUserId());
		if(userName != null) {
			map.put("userName", userName);
		}
		return map;
	}

	@Override
	public List<Map<String, String>> getAllAccounterByCondition(String province,
			String city, String area, String industry, String career) {
		List<Map<String, String>> retList = new ArrayList<>();
		List<Accounter> list = accounterDao.getAllAccounterByCondition(province, city, area, industry, career);
		if(list != null && list.size() > 0) {
			for(Accounter accounter : list)
				retList.add(accounterConvertToMap(accounter));
		}
		return retList;
	}

	@Override
	public boolean addAccounter(Accounter accounter) {
		return accounterDao.addAccounter(accounter);
	}

	@Override
	public Map<String, List<Map<String, String>>> getAllCompanyExpense(String userId) {
		Map<String, List<Map<String, String>>> retMap = new HashMap<>();
		retMap.put("companyExpense", new ArrayList<>());
		retMap.put("companyInvoice", new ArrayList<>());
		List<Company> companyList = companyService.getCompanyListByAccounterId(userId);
		if(companyList != null) {
			for(Company company : companyList) {
				long companyId = company.getId();
				String companyName = companyService.getCompanyName(companyId);
				List<CompanyBill> companyBillList = companyBillDao.getAllCompanyBill(companyId);
				if(companyBillList != null) {
					for(CompanyBill bill : companyBillList) {
						Map<String, String> map = new HashMap<>();
						map.put("date", bill.getCreateTime().toString().substring(0, 10));
						map.put("file", bill.getFileName());
						DecimalFormat format = new DecimalFormat("#0.000");
						map.put("amount", format.format(bill.getAmount() == null ? 0 : bill.getAmount()));
						map.put("type", bill.getType() == null || bill.getType().isEmpty() ? "未设定" : bill.getType());
						map.put("companyName", companyName);
						map.put("item_type", "公司发票");
						retMap.get("companyExpense").add(map);
					}
				}
				List<CompanyBankSta> companyBankStaList = companyBankStaDao.getAllCompanyBankSta(companyId);
				if(companyBankStaList != null ) {
					for(CompanyBankSta bankSta : companyBankStaList) {
						Map<String, String> map = new HashMap<>();
						map.put("date", bankSta.getCreateTime().toString().substring(0, 10));
						map.put("file", bankSta.getFileName());
						map.put("amount", "无");
						map.put("type", "无");
						map.put("companyName", companyName);
						map.put("item_type", "公司银行对账单");
						retMap.get("companyExpense").add(map);
					}
				}
				List<CompanySalary> companySalaryList = companySalaryService.getAllCompanySalary(companyId);
				if(companySalaryList != null) {
					for(CompanySalary companySalary : companySalaryList) {
						Map<String, String> map = new HashMap<>();
						map.put("date", companySalary.getCreateTime().toString().substring(0, 10));
						map.put("file", companySalary.getSalaryFile());
						map.put("companyName", companyName);
						map.put("amount", "无");
						map.put("type", "无");
						map.put("item_type", "公司工资单");
						retMap.get("companyExpense").add(map);
					}
				}
				List<User> userList = userService.getUserListByCompanyId(companyId);
				if(userList != null) {
					for(User companyUser : userList) {
						List<UserInvoice> userInvoiceList = userInvoiceService.getUserInvoiceByUserIdAndStatusAndApprovalStatus(companyUser.getUserId(), 1, 0);
						if(userInvoiceList != null) {
							for(UserInvoice userInvoice : userInvoiceList) {
								Map<String, String> map = new HashMap<>();
								map.put("date", userInvoice.getUpdateTime().toString().substring(0, 10));
								Invoice invoice = invoiceService.getInvoiceById(userInvoice.getInvoiceId());
								DecimalFormat format = new DecimalFormat("#0.000");
								map.put("amount", format.format(invoice.getAmount() == null ? 0 : invoice.getAmount()));
								map.put("companyName", companyName);
								int expenseTypeId = invoice.getExpenseTypeId();
								String typeName = expenseTypeService.getExpenseTypeNameById(expenseTypeId);
								map.put("type", typeName == null || typeName.isEmpty() ? "未设定" : typeName);
								map.put("item_type", "公司员工发票");
								retMap.get("companyInvoice").add(map);
							}
						}
					}
				}
			}
		}
		return retMap;
	}

	@Override
	public Map<String, List<Map<String, String>>> getAllCompanyExpenseByCondition(String userId,
			String conditionType, String conditionValue) {
		Map<String, List<Map<String, String>>> retMap = new HashMap<>();
		retMap.put("companyExpense", new ArrayList<>());
		retMap.put("companyInvoice", new ArrayList<>());
		if(conditionType == null || conditionType.isEmpty() || conditionValue == null || conditionValue.isEmpty()) return retMap;
		List<Company> companyList = companyService.getCompanyListByAccounterId(userId);
		if(companyList != null) {
			for(Company company : companyList) {
				long companyId = company.getId();
				String companyName = companyService.getCompanyName(companyId);
				if(conditionType.equals("1")) {
					if(companyName.indexOf(conditionValue) == -1) continue;
				}
				List<CompanyBill> companyBillList = companyBillDao.getAllCompanyBill(companyId);
				if(companyBillList != null) {
					if(conditionType.equals("3")) {
						if(("公司发票").indexOf(conditionValue) != -1) {
							for(CompanyBill bill : companyBillList) {
								Map<String, String> map = new HashMap<>();
								map.put("date", bill.getCreateTime().toString().substring(0, 10));
								map.put("file", bill.getFileName());
								DecimalFormat format = new DecimalFormat("#0.000");
								map.put("amount", format.format(bill.getAmount() == null ? 0 : bill.getAmount()));
								map.put("type", bill.getType() == null || bill.getType().isEmpty() ? "未设定" : bill.getType());
								map.put("companyName", companyName);
								map.put("item_type", "公司发票");
								retMap.get("companyExpense").add(map);
							}
						}
					} else {
						for(CompanyBill bill : companyBillList) {
							if(conditionType.equals("2")) {
								if(bill.getCreateTime().toString().substring(0, 10).indexOf(conditionValue) == -1) continue;
							}
							Map<String, String> map = new HashMap<>();
							map.put("date", bill.getCreateTime().toString().substring(0, 10));
							map.put("file", bill.getFileName());
							DecimalFormat format = new DecimalFormat("#0.000");
							map.put("amount", format.format(bill.getAmount() == null ? 0 : bill.getAmount()));
							map.put("type", bill.getType() == null || bill.getType().isEmpty() ? "未设定" : bill.getType());
							map.put("companyName", companyName);
							map.put("item_type", "公司发票");
							retMap.get("companyExpense").add(map);
						}
					}
				}
				List<CompanyBankSta> companyBankStaList = companyBankStaDao.getAllCompanyBankSta(companyId);
				if(companyBankStaList != null ) {
					if(conditionType.equals("3")) {
						if(("公司银行对账单").indexOf(conditionValue) != -1) {
							for(CompanyBankSta bankSta : companyBankStaList) {
								Map<String, String> map = new HashMap<>();
								map.put("date", bankSta.getCreateTime().toString().substring(0, 10));
								map.put("file", bankSta.getFileName());
								map.put("amount", "无");
								map.put("type", "无");
								map.put("companyName", companyName);
								map.put("item_type", "公司银行对账单");
								retMap.get("companyExpense").add(map);
							}
						}
					} else {
						for(CompanyBankSta bankSta : companyBankStaList) {
							if(conditionType.equals("2")) {
								if(bankSta.getCreateTime().toString().substring(0, 10).indexOf(conditionValue) == -1) continue;
							}
							Map<String, String> map = new HashMap<>();
							map.put("date", bankSta.getCreateTime().toString().substring(0, 10));
							map.put("file", bankSta.getFileName());
							map.put("amount", "无");
							map.put("type", "无");
							map.put("companyName", companyName);
							map.put("item_type", "公司银行对账单");
							retMap.get("companyExpense").add(map);
						}
					}
				}
				List<CompanySalary> companySalaryList = companySalaryService.getAllCompanySalary(companyId);
				if(companySalaryList != null) {
					if(conditionType.equals("3")) {
						if(("公司工资单").indexOf(conditionValue) != -1) {
							for(CompanySalary companySalary : companySalaryList) {
								Map<String, String> map = new HashMap<>();
								map.put("date", companySalary.getCreateTime().toString().substring(0, 10));
								map.put("file", companySalary.getSalaryFile());
								map.put("companyName", companyName);
								map.put("amount", "无");
								map.put("type", "无");
								map.put("item_type", "公司工资单");
								retMap.get("companyExpense").add(map);
							}
						}
					} else {
						for(CompanySalary companySalary : companySalaryList) {
							if(conditionType.equals("2")) {
								if(companySalary.getCreateTime().toString().substring(0, 10).indexOf(conditionValue) == -1) continue;
							}
							Map<String, String> map = new HashMap<>();
							map.put("date", companySalary.getCreateTime().toString().substring(0, 10));
							map.put("file", companySalary.getSalaryFile());
							map.put("companyName", companyName);
							map.put("amount", "无");
							map.put("type", "无");
							map.put("item_type", "公司工资单");
							retMap.get("companyExpense").add(map);
						}
					}
				}
				List<User> userList = userService.getUserListByCompanyId(companyId);
				if(userList != null) {
					if(conditionType.equals("3")) {
						if(("公司员工发票").indexOf(conditionValue) != -1) {
							for(User companyUser : userList) {
								List<UserInvoice> userInvoiceList = userInvoiceService.getUserInvoiceByUserIdAndStatusAndApprovalStatus(companyUser.getUserId(), 1, 0);
								if(userInvoiceList != null) {
									for(UserInvoice userInvoice : userInvoiceList) {
										Map<String, String> map = new HashMap<>();
										map.put("date", userInvoice.getUpdateTime().toString().substring(0, 10));
										Invoice invoice = invoiceService.getInvoiceById(userInvoice.getInvoiceId());
										DecimalFormat format = new DecimalFormat("#0.000");
										map.put("amount", format.format(invoice.getAmount() == null ? 0 : invoice.getAmount()));
										map.put("companyName", companyName);
										int expenseTypeId = invoice.getExpenseTypeId();
										String typeName = expenseTypeService.getExpenseTypeNameById(expenseTypeId);
										map.put("type", typeName == null || typeName.isEmpty() ? "未设定" : typeName);
										map.put("item_type", "公司员工发票");
										retMap.get("companyInvoice").add(map);
									}
								}
							}
						}
					} else {
						for(User companyUser : userList) {
							List<UserInvoice> userInvoiceList = userInvoiceService.getUserInvoiceByUserIdAndStatusAndApprovalStatus(companyUser.getUserId(), 1, 0);
							if(userInvoiceList != null) {
								for(UserInvoice userInvoice : userInvoiceList) {
									if(conditionType.equals("2")) {
										if(userInvoice.getUpdateTime().toString().substring(0, 10).indexOf(conditionValue) == -1) continue;
									}
									Map<String, String> map = new HashMap<>();
									map.put("date", userInvoice.getUpdateTime().toString().substring(0, 10));
									Invoice invoice = invoiceService.getInvoiceById(userInvoice.getInvoiceId());
									DecimalFormat format = new DecimalFormat("#0.000");
									map.put("amount", format.format(invoice.getAmount() == null ? 0 : invoice.getAmount()));
									map.put("companyName", companyName);
									int expenseTypeId = invoice.getExpenseTypeId();
									String typeName = expenseTypeService.getExpenseTypeNameById(expenseTypeId);
									map.put("type", typeName == null || typeName.isEmpty() ? "未设定" : typeName);
									map.put("item_type", "公司员工发票");
									retMap.get("companyInvoice").add(map);
								}
							}
						}
					}
				}
			}
		}
		return retMap;
	}

	@Override
	public Map<String, String> accounterBelongToCompany(String userId) {
		Map<String, String> retMap = new HashMap<>();
		List<Company> companyList = companyService.getCompanyListByAccounterId(userId);
		if(companyList == null) {
			retMap.put("hasBind", "false");
		} else {
			retMap.put("hasBind", "true");
		}
		return retMap;
	}

	@Override
	public Map<String, String> accounterHasFinishRegister(String userId) {
		Map<String, String> retMap = new HashMap<>();
		Accounter accounter = accounterDao.getAccounterByUserId(userId);
		if(accounter == null) {
			retMap.put("hasFinishRegister", "false");
		} else {
			retMap.put("hasFinishRegister", "true");
		}
		return retMap;
	}

	@Override
	public List<Map<String, String>> getAllAccounterCompany(String userId) {
		List<Map<String, String>> retList = new ArrayList<>();
		List<Company> companyList = companyService.getCompanyListByAccounterId(userId);
		if(companyList != null) {
			for(Company company : companyList) {
				List<SalarySocialSecurity> sssList= salarySocialSecurityService.getSSSByCompanyId(company.getId());
				if(sssList == null || sssList.size() == 0) continue;
				Map<String, String> map = new HashMap<>();
				map.put("companyName", company.getName());
				map.put("companyId", String.valueOf(company.getId()));
				retList.add(map);
			}
		}
		return retList;
	}

	@Override
	public Map<String, String> uploadCompanySalaryTemplate(String companyId,
			String realPath, MultipartFile file, String userId) {
		Map<String, String> retMap = new HashMap<>();
		try {
			long companyId_ = Long.valueOf(companyId);
			String fileName = getGernarateFileName(file, userId);
			FileUtils.copyInputStreamToFile(file.getInputStream(), new File(
					realPath, fileName));
			salarySocialSecurityService.setTemplate(companyId_, fileName);
			retMap.put("error_code", "0");
		} catch (Exception e) {
			logger.debug("upload template exception : {}", e.toString());
			retMap.put("error_code", "1");
			retMap.put("error_message", "上传工资计算模板失败，请稍后重试！");
		}
		return null;
	}
	
	private String getGernarateFileName(MultipartFile file, String userId) {
		Random rdm = new Random(System.currentTimeMillis());
		String extendName = file.getOriginalFilename().substring(
				file.getOriginalFilename().indexOf(".") + 1);
		return userId + System.currentTimeMillis() + Math.abs(rdm.nextInt())
				% 1000 + (extendName == null ? ".unknown" : "." + extendName);
	}

	@Override
	public List<Map<String, String>> getAllAccounterCompanyWithoutCondition(
			String userId) {
		List<Map<String, String>> retList = new ArrayList<>();
		List<Company> companyList = companyService.getCompanyListByAccounterId(userId);
		if(companyList != null) {
			for(Company company : companyList) {
				Map<String, String> map = new HashMap<>();
				map.put("companyName", company.getName());
				map.put("companyId", String.valueOf(company.getId()));
				retList.add(map);
			}
		}
		return retList;
	}

	@Override
	public Map<String, String> getTakeBillWay(String companyId) {
		Map<String, String> retMap = new HashMap<>();
		try {
			Company company = companyService.getCompanyByCompanyId(Long.valueOf(companyId));
			String type = company.getTakeType();
			retMap.put("way", type == null ? "" : type);
		} catch(Exception e) {
			logger.debug("Exception : {}", e.toString());
		}
		return retMap;
	}

}
