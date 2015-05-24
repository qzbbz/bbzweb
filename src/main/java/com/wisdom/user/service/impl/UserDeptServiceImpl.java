package com.wisdom.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisdom.common.model.UserDept;
import com.wisdom.company.dao.IDeptDao;
import com.wisdom.user.dao.IUserDeptDao;
import com.wisdom.user.dao.IUserOperationDao;
import com.wisdom.user.service.IUserDeptService;

@Service("userDeptService")
public class UserDeptServiceImpl implements IUserDeptService {

	@Autowired
	private IUserDeptDao userDeptDao;
	
	@Autowired
	private IUserOperationDao userOperationDao;
	
	@Autowired IDeptDao deptDao;
	
	@Override
	public long getDeptIdByUserId(String userId) {
		return userDeptDao.getUserDeptByUserId(userId).getDeptId();
	}
	
	@Override
	public long getUserNumByDeptId(long deptId){
		return userDeptDao.getUserNumByDeptId(deptId);
	}

	@Override
	public List<UserDept> getUserDeptListByDeptId(long deptId) {
		return userDeptDao.getUserDeptListByDeptId(deptId);
	}

	@Override
	public boolean delUserByDeptId(long deptId) {
		return userDeptDao.delUserByDeptId(deptId) > 0;
	}
	
}