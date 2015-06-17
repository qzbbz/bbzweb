package com.wisdom.accounter.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisdom.accounter.dao.IAccounterCareerDao;
import com.wisdom.accounter.dao.IAccounterCertificateDao;
import com.wisdom.accounter.dao.IAccounterDao;
import com.wisdom.accounter.dao.IAccounterIndustryDao;
import com.wisdom.accounter.service.IAccounterService;
import com.wisdom.common.model.Accounter;
import com.wisdom.common.model.AccounterCareer;
import com.wisdom.common.model.AccounterCertificate;
import com.wisdom.common.model.AccounterIndustry;
import com.wisdom.common.model.CompanyBankSta;
import com.wisdom.common.model.CompanyBill;
import com.wisdom.common.model.CompanySalary;
import com.wisdom.common.model.Invoice;
import com.wisdom.common.model.User;
import com.wisdom.common.model.UserInvoice;
import com.wisdom.company.dao.ICompanyBankStaDao;
import com.wisdom.company.dao.ICompanyBillDao;
import com.wisdom.company.service.ICompanySalaryService;
import com.wisdom.company.service.ICompanyService;
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
		User user = userService.getUserByUserId(userId);
		if(user != null && user.getCompanyId() != -1) {
			long companyId = user.getCompanyId();
			String companyName = companyService.getCompanyName(companyId);
			List<CompanyBill> companyBillList = companyBillDao.getAllCompanyBill(companyId);
			if(companyBillList != null) {
				for(CompanyBill bill : companyBillList) {
					Map<String, String> map = new HashMap<>();
					map.put("date", bill.getCreateTime().toString().substring(0, 10));
					map.put("file", bill.getFileName());
					map.put("companyName", companyName);
					retMap.get("companyExpense").add(map);
				}
			}
			List<CompanyBankSta> companyBankStaList = companyBankStaDao.getAllCompanyBankSta(companyId);
			if(companyBankStaList != null ) {
				for(CompanyBankSta bankSta : companyBankStaList) {
					Map<String, String> map = new HashMap<>();
					map.put("date", bankSta.getCreateTime().toString().substring(0, 10));
					map.put("file", bankSta.getFileName());
					map.put("companyName", companyName);
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
							map.put("amount", String.valueOf(invoice.getAmount()));
							map.put("companyName", companyName);
							retMap.get("companyInvoice").add(map);
						}
					}
				}
			}
		}
		return retMap;
	}

	@Override
	public Map<String, List<Map<String, String>>> getCompanyExpenseByCompanyName(String userId,
			String companyName) {
		
		return null;
	}

	@Override
	public Map<String, String> accounterBelongToCompany(String userId) {
		Map<String, String> retMap = new HashMap<>();
		User user = userService.getUserByUserId(userId);
		if(user == null || user.getTypeId() != 1 || user.getCompanyId() == -1) {
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

}
