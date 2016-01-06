package com.wisdom.company.service.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisdom.common.model.Company;
import com.wisdom.common.model.CompanyInfo;
import com.wisdom.common.model.Dept;
import com.wisdom.common.model.Employment;
import com.wisdom.common.utils.Result;
import com.wisdom.common.utils.ResultCode;
import com.wisdom.company.dao.ICompanyDao;
import com.wisdom.company.dao.IDeptDao;
import com.wisdom.company.dao.IEmploymentDao;
import com.wisdom.company.service.ICompanyService;

@Service("companyService")
public class CompanyServiceImpl implements ICompanyService {

	@Autowired
	private ICompanyDao companyDao;
	
	@Autowired
	private IEmploymentDao employmentDao;
	
	@Autowired
	private IDeptDao deptDao;
	
	@Override
	public long addCompany(Company company) {
		//增加costCenter中的公司编码部分
		if(-1 == company.getParentId()){
			company.setCompanyCode("10");
		}else{
			List<Company> list = companyDao.getSubCompanyListByCompanyIdOrder(company.getParentId());
			if(null == list || list.size() == 0){
				company.setCompanyCode("11");
			}else{
				long compCode = Long.valueOf(list.get(0).getCompanyCode());
				company.setCompanyCode(String.valueOf(compCode + 1));
			}
		}
		//add end
		
		return companyDao.addCompany(company);
	}

	@Override
	public int accounterAmountBelongToCompany(long companyId) {
		List<Employment> list = employmentDao.getEmploymentByCompanyId(companyId);
		return list != null ? list.size() : 0;
	}

	@Override
	public boolean companySelectAccounter(long companyId, String userId) {
		Employment emp = new Employment();
		emp.setCompanyId(companyId);
		emp.setUserId(userId);
		emp.setCreateTime(new Timestamp(System.currentTimeMillis()));
		return employmentDao.addEmployment(emp);
	}

	@Override
	public String getCompanyName(long companyId) {
		Company company = companyDao.getCompanyByCompanyId(companyId);
		return company != null ? company.getName() : "";
	}

	@Override
	public boolean updateCompanyName(String companyName, long companyId) {
		return companyDao.updateCompanyName(companyName, companyId);
	}

	@Override
	public String getParentCompanyNameByCompanyId(long companyId) {
		Company company = companyDao.getCompanyByCompanyId(companyId);
		if(company == null) return "";
		company = companyDao.getCompanyByCompanyId(company.getParentId());
		if(company == null) return "";
		return company.getName() == null ? "" : company.getName();
	}

	@Override
	public List<Company> getSubCompanyListByCompanyId(long companyId) {
		return companyDao.getSubCompanyListByCompanyId(companyId);
	}
	
	@Override
	public Company getCompanyByCompanyId(Long id){
		return companyDao.getCompanyByCompanyId(id);
	}

	@Override
	public Result delCompany(long companyId) {
		Result result = new Result();
		if(-1 == companyId){
			result.setMsg("参数错误！");
			result.setResultCode(ResultCode.paramError.code);
			return result;
		}
		/*先检查是否还有子公司存在*/
		List<Company> list = getSubCompanyListByCompanyId(companyId);
		if(null != list && list.size() > 0){
			result.setMsg("请先删除子公司!");
			result.setResultCode(ResultCode.serviceError.code);
			return result;
		}
		/*检查是否还有部门存在*/
		List<Dept> deptList = deptDao.getDeptListByCompanyId(companyId);
		if(null != deptList && deptList.size() > 0){
			result.setMsg("请先删除公司下部门!");
			result.setResultCode(ResultCode.serviceError.code);
			return result;
		}
		
		Company company = new Company();
		company.setId(companyId);
		boolean blRet = companyDao.deleteCompany(company);
		if(!blRet){
			result.setMsg("删除公司操作失败!");
			result.setResultCode(ResultCode.systemError.code);
			return result;
		}
		
		result.setResultCode(ResultCode.success.code);
		return result;
	}

	@Override
	public List<Company> getAllCompany() {
		return companyDao.getAllCompany();
	}

	@Override
	public boolean updateCompanyTakeType(long companyId, String takeType) {
		return companyDao.updateCompanyTakeType(companyId, takeType);
	}

	@Override
	public boolean updateCompanyAccounter(long companyId, String accounterId) {
		return companyDao.updateCompanyAccounter(companyId, accounterId);
	}

	@Override
	public List<Company> getCompanyListByAccounterId(String accounterId) {
		return companyDao.getCompanyListByAccounterId(accounterId);
	}

	@Override
	public List<Company> getCompanyAndAccounter() {
		return companyDao.getCompanyAndAccounter();
	}

	@Override
	public boolean updateAccounterForCompany(long companyId, String accounterId) {
		return companyDao.updateAccounterForCompany(companyId, accounterId);
	}

	@Override
	public List<Company> getCompanyByName(String companyName) {
		return companyDao.getCompanyByName(companyName);
	}
	@Override
	public List<CompanyInfo> getCompanyInfoAndUserIDAndPhone() {
		return companyDao.getCompanyInfoAndUserIDAndPhone();
	}

	
	
}