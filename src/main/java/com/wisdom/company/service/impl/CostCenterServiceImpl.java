package com.wisdom.company.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisdom.common.model.CostCenter;
import com.wisdom.company.dao.ICostCenterDao;
import com.wisdom.company.service.ICostCenterService;

@Service("costCenterService")
public class CostCenterServiceImpl implements ICostCenterService {

	@Autowired
	private ICostCenterDao costCenterDao;
	
	@Override
	public CostCenter getCostCenterByEncode(long encode) {
		return costCenterDao.getCostCenterByEncode(encode);
	}

	@Override
	public long addCostCenter(CostCenter costCenter) {
		return costCenterDao.addCostCenter(costCenter);
	}

	@Override
	public boolean deleteCostCenterByEncode(long encode) {
		return costCenterDao.deleteCostCenterByEncode(encode);
	}

	@Override
	public boolean updateCostCenter(CostCenter costCenter) {
		return costCenterDao.updateCostCenter(costCenter);
	}

}
