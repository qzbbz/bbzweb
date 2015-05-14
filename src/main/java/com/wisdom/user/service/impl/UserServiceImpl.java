package com.wisdom.user.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisdom.common.model.AmountLimit;
import com.wisdom.common.model.User;
import com.wisdom.common.model.UserDept;
import com.wisdom.common.model.UserOpenid;
import com.wisdom.common.model.UserPwd;
import com.wisdom.common.model.UserRole;
import com.wisdom.common.model.UserType;
import com.wisdom.company.service.IDeptService;
import com.wisdom.user.dao.IAmountLimitDao;
import com.wisdom.user.dao.IUserDeptDao;
import com.wisdom.user.dao.IUserOperationDao;
import com.wisdom.user.dao.IUserQueryDao;
import com.wisdom.user.service.IUserService;

@Service("userInfoService")
public class UserServiceImpl implements IUserService {

	private static final Logger logger = LoggerFactory
			.getLogger(UserServiceImpl.class);
	
	@Autowired
	private IUserQueryDao userQueryDao;
	
	@Autowired
	private IUserOperationDao userOperationDao;
	
	@Autowired
	private IUserDeptDao userDeptDao;
	
	@Autowired
	private IDeptService deptService;
	
	@Autowired
	private IAmountLimitDao amountLimitDao;
	
	@Override
	public String getUserPwdByUserId(String userId) {
		UserPwd userPwd = userQueryDao.getUserPwdByUserId(userId);
		return userPwd != null ? userPwd.getPwd() : "";
	}

	@Override
	public int getUserTypeIdByUserId(String userId) {
		UserType userType = userQueryDao.getUserTypeByUserId(userId);
		return userType != null ? userType.getId() : 0;
	}

	@Override
	public boolean userIdExist(String userId) {
		User user = userQueryDao.getUserByUserId(userId);
		return user != null ? true : false;
	}

	@Override
	public boolean addUser(User user) {
		return userOperationDao.addUser(user);
	}

	@Override
	public long getCompanyIdByUserId(String userId) {
		User user = userQueryDao.getUserByUserId(userId);
		return user != null ? user.getCompanyId() : 0;
	}

	@Override
	public String getUserIdByOpenId(String openId) {
		UserOpenid userOpenid = userQueryDao.getUserOpenidByOpenid(openId);
		return userOpenid != null ? userOpenid.getUserId() : "";
	}

	@Override
	public String getUserNameByUserId(String userId) {
		User user = userQueryDao.getUserByUserId(userId);
		return user != null && user.getUserName() != null ? user.getUserName() : "";
	}

	@Override
	public boolean setUserNameByUserId(String userName, String userId) {
		return userOperationDao.updateUserNameByUserId(userName, userId);
	}

	@Override
	public String getOpenIdByUserId(String userId) {
		UserOpenid userOpenid = userQueryDao.getUserOpenidByUserId(userId);
		return userOpenid != null ? userOpenid.getOpenid() : "";
	}

	@Override
	public boolean checkUserAuth(String userId, String appovalUser) {
		return true;
	}

	@Override
	public List<String> getApprovalUserList(String userId) {
		List<String> userIdList = new ArrayList<>();
		UserDept userDept = userDeptDao.getUserDeptByUserId(userId);
		if(userDept == null) return userIdList;
		long deptId = userDept.getDeptId();
		long deptParentId = deptService.getParentDeptIdById(deptId);
		if(deptParentId == 0) {
			userIdList.add(userId);
			return userIdList;
		}
		List<UserDept> userDeptList = userDeptDao.getUserDeptListByDeptId(deptParentId);
		if(userDeptList == null || userDeptList.size() <= 0) return userIdList;
		for(UserDept ud : userDeptList) {
			userIdList.add(ud.getUserId());
		}
		logger.debug("getApprovalUserList deptId : {}", deptId);
		logger.debug("getApprovalUserList deptParentId : {}", deptParentId);
		logger.debug("userIdList : {}", userIdList);
		return userIdList;
	}

	@Override
	public boolean ifNeedSuperApproval(String userId, String approvalId,
			double amount) {
		User user = userQueryDao.getUserByUserId(approvalId);
		if(user == null) return false;
		long companyId = user.getCompanyId();
		UserDept userDept = userDeptDao.getUserDeptByUserId(approvalId);
		if(userDept == null) return false;
		long deptId = userDept.getDeptId();
		UserRole userRole = userQueryDao.getUserRole(approvalId);
		if(userRole == null) return false;
		long roleId = userRole.getRoleId();
		AmountLimit limit = amountLimitDao.getAmountLimit(companyId, deptId, roleId);
		if(limit == null) return false;
		if((double)(limit.getLimit()) <= amount) return true;
		return false;
	}

	@Override
	public String getUserMsgEmailByUserId(String userId) {
		User user = userQueryDao.getUserByUserId(userId);
		return user == null || user.getMsgEmail() == null ? "" : user.getMsgEmail();
	}

	@Override
	public boolean updateUserMsgEmailByUserId(String msgEmail, String userId) {
		return userOperationDao.updateUserMsgEmail(msgEmail, userId);
	}

}

