package com.wisdom.web.api;

public interface IGenerateCostCenterEncodeApi {
	
	public String generateCostCenterEncode(String parentCostCenterEncode);
	public String  genCostCenterAndUpdate(long companyId,long  deptId,int deptLevel,boolean subCompany);
	
}
