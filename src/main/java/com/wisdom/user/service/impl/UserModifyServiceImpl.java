package com.wisdom.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisdom.common.model.User;
import com.wisdom.user.dao.IUserOperationDao;
import com.wisdom.user.dao.IUserQueryDao;
import com.wisdom.user.service.IUserModifyService;

@Service("userModifyService")
public class UserModifyServiceImpl implements IUserModifyService {

	@Autowired
	private IUserOperationDao userOperationDao;
	
	@Autowired
	private IUserQueryDao userQueryDao;
	
	@Override
	public boolean modifyUserCompanyIdByUserId(String userId, long companyId) {
		boolean ret = false;
		User user = userQueryDao.getUserByUserId(userId);
		if(user != null) {
			user.setCompanyId(companyId);
			ret = userOperationDao.updateUser(user);
		}
		return ret;
	}

}
