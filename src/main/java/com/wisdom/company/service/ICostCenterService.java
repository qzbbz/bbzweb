package com.wisdom.company.service;

import com.wisdom.common.model.CostCenter;

public interface ICostCenterService {
	
	public CostCenter getCostCenterByEncode(long encode);
	
	public long addCostCenter(CostCenter costCenter);
	
	public boolean deleteCostCenterByEncode(long encode);
	
	public boolean updateCostCenter(CostCenter costCenter);
	
}
