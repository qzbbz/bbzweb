package com.wisdom.company.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisdom.company.dao.IDeptDao;
import com.wisdom.company.service.IDeptService;

@Service("deptService")
public class DeptServiceImpl implements IDeptService {

	@Autowired
	private IDeptDao deptDao;
	
	@Override
	public String getDeptNameById(long id) {
		return deptDao.getDeptByDeptId(id).getName();
	}

}
