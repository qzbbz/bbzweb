package com.wisdom.company.dao;

import java.util.List;

import com.wisdom.common.model.CostCenter;

public interface ICostCenterDao {
	
	public CostCenter getCostCenterByEncode(long encode);
	
	public long addCostCenter(CostCenter costCenter);
	
	public boolean deleteCostCenterByEncode(long encode);
	
	public boolean updateCostCenter(CostCenter costCenter);
	
	public List<CostCenter> getAllCostCenter();
	
}
