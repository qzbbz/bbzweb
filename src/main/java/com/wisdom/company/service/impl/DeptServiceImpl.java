package com.wisdom.company.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisdom.common.model.Dept;
import com.wisdom.company.dao.IDeptDao;
import com.wisdom.company.service.IDeptService;
import com.wisdom.web.api.controller.ArchSetController;

@Service("deptService")
public class DeptServiceImpl implements IDeptService {

	private static final Logger logger = LoggerFactory
			.getLogger(DeptServiceImpl.class); 
	@Autowired
	private IDeptDao deptDao;
	
	@Override
	public String getDeptNameById(long id) {
		Dept dept = deptDao.getDeptByDeptId(id);
		return dept == null ? "" : dept.getName();
	}

	@Override
	public long getParentDeptIdById(long id) {
		Dept dept = deptDao.getDeptByDeptId(id);
		return dept == null ? -1 : dept.getParentId();
	}

	@Override
	public String getCostCenterCodeById(long id) {
		Dept dept = deptDao.getDeptByDeptId(id);
		return dept == null ? "" : String.valueOf(dept.getCostCenterEncode());
	}

	@Override
	public List<Dept> getDeptListByCompanyId(long companyId) {
		return deptDao.getDeptListByCompanyId(companyId);
	}

	@Override
	public long addDeptAndGetKey(Dept dept) {
		return deptDao.addDeptAndGetKey(dept);
	}

	@Override
	public boolean updateDeptInfoById(Dept dept) {
		Long id = dept.getId();
		if(null == id || id <=0 ){
			logger.error("id error return {}",id);
			return false;
		}
		return deptDao.updateDeptInfoById(dept);
	}

	@Override
	public long querySubDeptNumByIdAndCompanyId(long companyId, long id) {
		return deptDao.querySubDeptNumByIdAndCompanyId(companyId, id);
	}

	@Override
	public boolean deleteDept(long deptId) {
		Dept dept = new Dept();
		dept.setId(deptId);
		return deptDao.deleteDept(dept);
	}

}
