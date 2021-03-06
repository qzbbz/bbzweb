package com.wisdom.user.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.wisdom.common.model.AmountLimit;
import com.wisdom.common.model.User;
import com.wisdom.common.model.UserDept;
import com.wisdom.common.model.UserInviteCode;
import com.wisdom.common.model.UserOpenid;
import com.wisdom.common.model.UserPhone;
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
		User user = userQueryDao.getUserByUserId(userId);
		return user != null ? user.getTypeId() : 0;
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
	public List<UserOpenid> getUserIdByOpenId(String openId) {
		List<UserOpenid> userOpenid = userQueryDao.getUserOpenidByOpenid(openId);
		return userOpenid;
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
		User user = userQueryDao.getUserByUserId(userId);
		if(user != null && user.getBillAuditUser() != null && 
				!user.getBillAuditUser().isEmpty() && !("0").equals(user.getBillAuditUser())) {
			userIdList.add(user.getBillAuditUser());
			return userIdList;
		}
		UserDept userDept = userDeptDao.getUserDeptByUserId(userId);
		if(userDept == null) return userIdList;
		List<UserDept> userDeptList = userDeptDao.getUserDeptListByDeptId(userDept.getDeptId());
		for(UserDept ud : userDeptList) {
			if(ud.getStatus() == 1) {
				userIdList.add(ud.getUserId());
			}
		}
		if(userIdList.size() != 0) {
			logger.debug("getApprovalUserList deptId : {}", userDept.getDeptId());
			logger.debug("userIdList : {}", userIdList);
			return userIdList;
		}
		
		recusiveGetApprovalUserList(userIdList, userDept.getDeptId());
		
		logger.debug("getApprovalUserList deptId : {}", userDept.getDeptId());
		logger.debug("userIdList : {}", userIdList);
		return userIdList;
	}

	private void recusiveGetApprovalUserList(List<String> userIdList, long deptId) {
		long dId = deptService.getParentDeptIdById(deptId);
		if(dId == 0) return;
		List<UserDept> userDeptList = userDeptDao.getUserDeptListByDeptId(dId);
		for(UserDept ud : userDeptList) {
			if(ud.getStatus() == 1) {
				userIdList.add(ud.getUserId());
			}
		}
		if(userIdList.size() != 0) {
			return;
		} else {
			recusiveGetApprovalUserList(userIdList, dId);
		}
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

	@Override
	public boolean updateUserRegStatusByUserId(int status, String userId) {
		return userOperationDao.updateUserRegStatus(status, userId);
	}

	@Override
	public boolean setUserPwdByUserId(String userPwd, String userId) {
		UserPwd uPwd = new UserPwd();
		uPwd.setPwd(userPwd);
		uPwd.setUserId(userId);
		return userOperationDao.addUserPwd(uPwd);
	}

	@Override
	public boolean addUserPhone(String userId, String userPhone, int phoneTypeId) {
		UserPhone up = userQueryDao.getUserPhoneByUserId(userId);
		boolean hasExisted = false;
		if(up != null) {
			hasExisted = true;
		} else {
			up = new UserPhone();
		}
		up.setPhone(userPhone);
		up.setUserId(userId);
		up.setType(phoneTypeId);
		up.setCreateTime(new Timestamp(System.currentTimeMillis()));
		return hasExisted ? userOperationDao.updateUserPhone(up) : userOperationDao.addUserPhone(up);
	}

	@Override
	public boolean deleteUser(String userId) {
		if(StringUtils.isEmpty(userId)){
			logger.error("userId empty error!");
			return false;
		}
		User user = new User();
		user.setUserId(userId);
		return userOperationDao.deleteUser(user);
	}

	@Override
	@Transactional
	public boolean updateUserInfo(String userId,String userName,int status, int typeId,
				String userCode,String userLevel,String msgMail) {
		
		if(StringUtils.isEmpty(userId)){
			logger.error("userId empty error!");
			return false;
		}
		
		if(StringUtils.isEmpty(userName) &&  StringUtils.isEmpty(userCode)&&StringUtils.isEmpty(userLevel)
				&& StringUtils.isEmpty(msgMail) && -1 == typeId){
			logger.error("no need to update!");
			return false;
		}
		
		User user= new User();
		user.setUserId(userId);
		user.setUserName(userName);
		user.setUserLevel(userLevel);
		user.setMsgEmail(msgMail);
		user.setUserEncode(userCode);
		user.setTypeId(typeId);
		
		if(!userDeptDao.updateUserDeptStatus(userId, status)) {
			return false;
		}
		
		return userOperationDao.updateUserInfo(user);
	}

	@Override
	public List<User> getUserListByCompanyId(long companyId) {
		return userQueryDao.getUserListByCompanyId(companyId);
	}

	@Override
	public User getUserByUserId(String userId) {
		return userQueryDao.getUserByUserId(userId);
	}

	@Override
	public UserInviteCode getUserInvitecodeByUserId(String userId) {
		return userQueryDao.getUserInvitecodeByUserId(userId);
	}

	@Override
	public List<User> getAccounterUserList() {
		return userQueryDao.getAccounterUserList();
	}
 
	@Override
	public boolean modifyUserPwdByUserId(String userPwd, String userId) {
		return userOperationDao.updateUserPwd(userId, userPwd);
	}

	@Override
	public int getUserAuditStatusByUserId(String userId) {
		User user = userQueryDao.getUserByUserId(userId);
		return user != null ? user.getAuditStatus() : 0;
	}

	@Override
	public boolean updateUserAuditStatusByUserId(int status, String userId) {
		return userOperationDao.updateUserAuditStatus(status, userId);
	}

	@Override
	public boolean setBillAuditUser(String userId, String auditUserId) {
		return userOperationDao.setBillAuditUser(userId, auditUserId);
	}

	@Override
	public String getBillAuditUser(String userId) {
		User user = userQueryDao.getUserByUserId(userId);
		if(user == null || user.getBillAuditUser() == null || user.getBillAuditUser().isEmpty()) return "";
		return user.getBillAuditUser(); 
	}

	@Override
	public UserPhone getUserPhone(String userId) {
		return userQueryDao.getUserPhoneByUserId(userId);
	}

	@Override
	public boolean setUserPhone(String userId, String userPhone) {
		return userOperationDao.setUserPhone(userId, userPhone);
	}

	@Override
	public User getCompanyAdminUserByCompanyId(long companyId) {
		return userQueryDao.getCompanyAdminUserByCompanyId(companyId);
	}

	@Override
	public String getAccounterUserIdByEmployeeUserId(String user_id) {
		return userQueryDao.getAccounterUserIdByEmployeeUserId(user_id);
	}
}

