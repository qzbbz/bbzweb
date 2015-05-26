package com.wisdom.user.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.wisdom.common.model.User;
import com.wisdom.common.model.UserDept;
import com.wisdom.company.dao.IDeptDao;
import com.wisdom.invoice.dao.IUserInvoiceDao;
import com.wisdom.user.dao.IUserDeptDao;
import com.wisdom.user.dao.IUserInviteCodeDao;
import com.wisdom.user.dao.IUserOperationDao;
import com.wisdom.user.domain.UserInfo;
import com.wisdom.user.service.IUserDeptService;
import com.wisdom.user.service.IUserService;
import com.wisdom.web.api.controller.ArchSetController;

@Service("userDeptService")
public class UserDeptServiceImpl implements IUserDeptService {
	private static final Logger logger = LoggerFactory
			.getLogger(UserDeptServiceImpl.class); 

	@Autowired
	private IUserDeptDao userDeptDao;
	
	@Autowired
	private IUserOperationDao userOperationDao;
	
	@Autowired 
	private IDeptDao deptDao;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IUserInviteCodeDao userInviteCodeDao;
	
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

	@Override
	@Transactional
	public boolean addDeptUser(String userId,String userName,int iCharger,
			long iDeptId,String userCode,String userLevel,long iCompanyId,String msgEMail) {
		
		//先增加用户
		User user = new User();
		user.setUserId(userId);
		user.setUserName(userName);
		user.setUserLevel(userLevel);
		user.setUserEncode(userCode);
		user.setMsgEmail(msgEMail);
		user.setCompanyId(iCompanyId);
		user.setTypeId(0);//暂时为0；
		
		boolean blRet = userService.addUser(user);
		if(!blRet){
			logger.error("add user error!");
			return false;
		}
		
		//增加部门映射关系
		UserDept userDept = new UserDept();
		userDept.setDeptId(iDeptId);
		userDept.setStatus(iCharger);
		userDept.setUserId(userId);
		
		blRet = userDeptDao.addUserDeptRecord(userDept);
		if(!blRet){
			logger.error("addUserDeptRecord failed!");
		}
		
		//生成邀请码
		StringBuilder orig = new StringBuilder().append(iCompanyId).append(iDeptId).append(userId).append(userCode);
		long inviteCode = Math.abs(orig.toString().hashCode())%1000000;
		blRet = userInviteCodeDao.addUserInviteCode(userId, String.valueOf(inviteCode));
		if(!blRet){
			logger.error("addUserInviteCode error!",userId);
			return false;
		}
		return true;
	}
	
	@Override
	public boolean delDeptUser(String userId,long deptId){
		if(StringUtils.isEmpty(userId) || -1 == deptId){
			logger.error("input params error");
			return false;
		}
		User user = new User();
		user.setUserId(userId);
		if(!userOperationDao.deleteUser(user)){
			logger.error("delete User failed!");
			return false;
		}
		
		return userDeptDao.delUserDept(userId, deptId);
	}

	@Override
	public List<UserInfo> queryUser(String userId, long iDeptId,
			String userName, String userCode) {
		List<User> list= new ArrayList<User>();
		
		User user = new User();
		user.setUserId(userId);
		user.setUserName(userName);
		user.setUserEncode(userCode);
		
		list = userOperationDao.queryUser(user, null, null);
		if(null == list || !(list.size()>0)){
			return null;
		}
		List<UserInfo> userInfoList = new ArrayList<UserInfo>();
		for(User u : list){
			UserInfo userInfo = new UserInfo();
			userInfo.setUserId(user.getUserId());
			userInfo.setUserName(user.getUserName());
			userInfo.setUserCode(user.getUserEncode());
			userInfo.setUserLevel(user.getUserLevel());
			userInfo.setCompanyId(String.valueOf(user.getCompanyId()));
			userInfoList.add(userInfo);
		}
		return userInfoList;
	}
	
}