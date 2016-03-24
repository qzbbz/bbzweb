package com.wisdom.web.api;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.wisdom.web.utils.CompanyOrgStructure;

public interface ICompanyApi {

	public Map<String, String> companyDetailRegister(Map<String, String> params);
	
	public Map<String, String> companyInfoSettings(MultipartFile[] files, Map<String, String> params);
	
	public Map<String, String> getSSSInfo(String userId, String cityName, String type);
	
	public Map<String, String> updateSSSInfo(Map<String, String> params);
	
	public ResponseEntity<byte[]> downloadSalarySocialSecurityTemplate(String userId, String realPath);
	
	public Map<String, String> checkTemplateExist(String userId, String cityName, String type);
	
	public Map<String, String> uploadCompanySalary(String userId, String realPath, MultipartFile file,String date);
	
	public Map<String, String> uploadCompanyBankSta(MultipartFile file, Map<String, String> params);
	
	public Map<String, String> uploadCompanySales(MultipartFile file, Map<String, String> params);
	
	public List<Map<String, String>> getCompanyBankStaByCondition(Map<String, String> params);
	
	public List<Map<String, String>> getCompanySalesByCondition(Map<String, String> params);
	
	public Map<String, String> getCompanyDetailInfo(String userId);
	
	public List<CompanyOrgStructure> getOrgStructureData(String userId);
	
	public List<Map<String, String>> getAllCostCenterCode();
	
	public List<Map<String, String>> getAllUser(long companyId);
	
	public List<Map<String, String>> getSalaryTemplateHistory(long companyId);
	
	public List<Map<String, String>> getSalaryHistory(long companyId);
	
	public boolean deleteCompanySales(String idList, String realPath);
	
	public boolean deleteCompanyBill(String idList, String realPath);
	
	public boolean deleteCompanySalary(String idList, String realPath);
	
	public boolean setTakeType(long companyId, String takeType);
	// invoke exist file
	public ResponseEntity<byte[]> invokeExcelFile(String realPath, String excelName);
	// exportExcel control class
	public ResponseEntity<byte[]> exportExcel(Map<String, Object> map, String excelName);
	
	public Map<String, Object> getModelDetails(long companyId, String date);
		
    public Map<String, String> judgeDate(long companyId, String date);
    
    public Map<String, String> getCompanyWeixinInviteCode(String userId);
    
    public boolean setMailToAccounter(Map<String, String>params);
}