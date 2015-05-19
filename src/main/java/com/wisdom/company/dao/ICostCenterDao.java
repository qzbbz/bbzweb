package com.wisdom.company.dao;

import com.wisdom.common.model.CostCenter;

public interface ICostCenterDao {
	
	public CostCenter getCostCenterByEncode(long encode);
	
	public long addCostCenter(CostCenter costCenter);
	
	public boolean deleteCostCenterByEncode(long encode);
	
	public boolean updateCostCenter(CostCenter costCenter);
	
}
