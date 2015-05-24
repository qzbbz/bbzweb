package com.wisdom.company.service;

import java.util.List;

import com.wisdom.common.model.CostCenter;

public interface ICostCenterService {
	
	public CostCenter getCostCenterByEncode(long encode);
	
	public long addCostCenter(CostCenter costCenter);
	
	public boolean deleteCostCenterByEncode(long encode);
	
	public boolean updateCostCenter(CostCenter costCenter);
	
	public List<CostCenter> getAllCostCenter();
}
