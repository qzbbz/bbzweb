package com.wisdom.web.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wisdom.common.model.Company;
import com.wisdom.common.model.Dept;
import com.wisdom.common.utils.Result;
import com.wisdom.common.utils.ResultCode;
import com.wisdom.company.service.ICompanyService;
import com.wisdom.company.service.IDeptService;
import com.wisdom.user.service.IUserDeptService;
import com.wisdom.user.service.IUserService;
import com.wisdom.common.model.UserDept;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ArchSetController {
	private static final Logger logger = LoggerFactory
			.getLogger(ArchSetController.class); 
	@Autowired
	private ICompanyService companyService;

	@Autowired
	private IUserService userService;
	
	@Autowired
	private IDeptService deptService;
	
	@Autowired
	private IUserDeptService userDeptService;
	
	@ResponseBody
	@RequestMapping("/company/addDept")
	public Result addDept(HttpServletRequest request){
		Result result = new Result();
		String deptName = request.getParameter("deptName");
		String companyId = request.getParameter("companyId");
		String parentId = request.getParameter("curDeptId");
		String level = request.getParameter("level");
		
		if(StringUtils.isEmpty(deptName)){
			result.setResultCode(ResultCode.paramError.code);
			result.setMsg("部门名称不能为空！");
			return result;
		}
		
		if(StringUtils.isEmpty(parentId)){
			result.setResultCode(ResultCode.paramError.code);
			result.setMsg("父部门编号不能为空!");
			return result;
		}
		
		/*公司是否存在*/
		Company company = companyService.getCompanyByCompanyId(Long.parseLong(companyId));
		if(null == company){
			logger.error("company not existed! {}",companyId);
			result.setResultCode(ResultCode.paramError.code);
			return result;
		}
		
		Dept dept = new Dept();
		try {
			dept.setCompanyId(Long.parseLong(companyId));

			dept.setCostCenterEncode(0L);//TODO 
			dept.setLevel(Integer.parseInt(level) + 1); //添加子部门时，level + 1
			dept.setName(deptName);
			dept.setParentId(Long.parseLong(parentId));
		} catch (NumberFormatException e) {
			logger.error("convert parameter error");
			e.printStackTrace();
			result.setResultCode(ResultCode.paramError.code);
			return result;
		}
		
		long retId = deptService.addDeptAndGetKey(dept);
		result.getResultMap().put("deptId", retId);
		result.setMsg("增加部门信息成功");
		result.setResultCode(ResultCode.success.code);
		return result;
	}
	
	
	@ResponseBody
	@RequestMapping("/company/addSubCompany")
	public Result addSubCompany(HttpServletRequest request){
		Result result = new Result();
		String companyName = request.getParameter("companyName");
		String parentId = request.getParameter("curCompanyId");
		String monthExpense = request.getParameter("monExpense");
		String perfectMoment = request.getParameter("perfectMoment");
		
		if(StringUtils.isEmpty(companyName)){
			result.setResultCode(ResultCode.paramError.code);
			result.setMsg("公司名称不能为空！");
			return result;
		}
		
		if(StringUtils.isEmpty(parentId)){
			result.setResultCode(ResultCode.paramError.code);
			result.setMsg("公司编号不能为空!");
			return result;
		}
		
		/*TODO 父公司ID存在时，校验一下*/

		Company company = new Company();
		try{
			company.setName(companyName);
			company.setMonthExpense(0);
			company.setParentId(Long.parseLong(parentId));
			company.setPerfectMoment("");
		}catch(Exception e){
			result.setResultCode(ResultCode.paramError.code);
			return result;
		}
		long ret = companyService.addCompany(company);
		result.setMsg("增加公司成功！");
		result.setResultCode("0");
		result.getResultMap().put("companyId", ret);
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/company/modDeptInfo")
	public Result modDeptInfo(HttpServletRequest request){
		Result result = new Result();
		String deptName = request.getParameter("deptName");
		String deptId = request.getParameter("deptId");
		
		if(StringUtils.isEmpty(deptId)){
			result.setResultCode(ResultCode.paramError.code);
			result.setMsg("部门编号不能为空！");
			return result;
		}
		
		Dept dept = new Dept();
		try {
			dept.setId(Long.parseLong(deptId));
			dept.setName(deptName);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			result.setResultCode(ResultCode.paramError.code);
			return result;
		}
		
		boolean blRet = deptService.updateDeptInfoById(dept);
		if(blRet){
			result.setResultCode(ResultCode.success.code);
			return result;
		}else{
			result.setResultCode(ResultCode.systemError.code);
			return result;
		}
	}
	
	@Transactional
	@ResponseBody
	@RequestMapping("/company/delDept")
	public Result delDept(HttpServletRequest request){
		Result result = new Result();
		String deptName = request.getParameter("deptName");
		String companyId = request.getParameter("companyId");
		String deptId = request.getParameter("deptId");
		
		if(StringUtils.isEmpty(deptId)){
			result.setResultCode(ResultCode.paramError.code);
			result.setMsg("部门名称不能为空！");
			return result;
		}
		
		long id =0;
		long iComanyId=0;
		try {
			id = Long.parseLong(deptId);
			iComanyId = Long.parseLong(companyId);
		} catch (NumberFormatException e) {
			logger.error("parameter error:{} {}",companyId,deptId);
			result.setResultCode(ResultCode.paramError.code);
			result.setMsg("参数错误");
			e.printStackTrace();
			return result;
		}
		
		
		/*部门下还有子部门，是否可以删除*/
		long subNum = deptService.querySubDeptNumByIdAndCompanyId(iComanyId, id);
		if(subNum>0){
			logger.debug("has subdeptment:",subNum);
			result.setResultCode(ResultCode.paramError.code);
			result.setMsg("请先删除子部门!");
			return result;
		}
		
		
		/*部门下还有成员，直接删除*/
/*		long userNum = userDeptService.getUserNumByDeptId(Long.parseLong(deptId));
		if(userNum>0){
			result.setResultCode(ResultCode.paramError.code);
			result.setMsg("部门下还有用户，请先删除用户!");
			return result;
		}*/
		boolean blRet =false;
		List<UserDept> userDeptList = new ArrayList<UserDept>();
		userDeptList = userDeptService.getUserDeptListByDeptId(id);
		List<String> userList = new ArrayList<String>();
		if(null != userDeptList && userDeptList.size() != 0){
			//删除用户
			for(UserDept o:userDeptList){
				String userId = o.getUserId();
				blRet = userService.deleteUser(userId);
				if(!blRet){
					userList.add(userId);
				}
			}
			logger.error("delete User failed:",userList.toString());
			
			//删除user_dept
			blRet = userDeptService.delUserByDeptId(id);
			if(!blRet){
				logger.error("delUserByDeptId failed",id);
				result.setResultCode(ResultCode.systemError.code);
				return result;
			}
		}
		
		//删除部门		
		blRet = deptService.deleteDept(id);
		if(!blRet){
			logger.error("deleteDept failed!",id);
			result.setResultCode(ResultCode.systemError.code);
		}else{
			result.setResultCode(ResultCode.success.code);
		}
		return result;
	}
	
	@ResponseBody
	@Transactional
	@RequestMapping("/addDeptUser")
	public Result addDeptUser(HttpServletRequest request){
		Result result = new Result();
		String userId = request.getParameter("userId");
		String userName = request.getParameter("userName");
		String charger = request.getParameter("charger");
		String deptId = request.getParameter("deptId");
		String userCode = request.getParameter("userCode");
		String userLevel = request.getParameter("userLevel");
		String companyId = request.getParameter("companyId");
		String msgMail = request.getParameter("msgMail");
		
		if(StringUtils.isEmpty(userId) || StringUtils.isEmpty(userName) || StringUtils.isEmpty(charger) || StringUtils.isEmpty(deptId) ||
			StringUtils.isEmpty(userCode) || StringUtils.isEmpty(userLevel) ||StringUtils.isEmpty(companyId)){
			result.setResultCode(ResultCode.paramError.code);
			result.setMsg("参数错误!");
			return result;	
		}
		 
		int iCharger = -1;
		long iDeptId = -1;
		long iCompanyId = -1;
		
		try {
			iCharger = Integer.parseInt(charger);
			iDeptId = Long.parseLong(deptId);
			iCompanyId = Long.parseLong(companyId);
		} catch (NumberFormatException e) {
			logger.error("parameter convert error!");
			result.setResultCode(ResultCode.paramError.code);
			result.setMsg("参数类型错误!");
			return result;
		}
		boolean blRet = userDeptService.addDeptUser(userId,userName,iCharger,iDeptId,userCode,userLevel,iCompanyId,msgMail);
		if(!blRet){
			logger.error("addDeptUser error!");
			result.setResultCode(ResultCode.systemError.code);
			return result;
		}
		result.setResultCode(ResultCode.success.code);
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/modDeptUser")
	public Result modDeptUser(HttpServletRequest request){
		Result result = new Result();
		String userId = request.getParameter("userId");
		String userName = request.getParameter("userName");
		String typeId = request.getParameter("typeId");
		String userCode = request.getParameter("userCode");
		String userLevel = request.getParameter("userLevel");
		String msgMail = request.getParameter("msgMail");
		
		if(StringUtils.isEmpty(userId)){
			result.setResultCode(ResultCode.paramError.code);
			result.setMsg("用户ID不能为空!");
			return result;	
		}
		 
		int iTypeId = -1;
		
		try {
			iTypeId = Integer.parseInt(typeId);
		} catch (NumberFormatException e) {
			logger.error("parameter convert error!");
			result.setResultCode(ResultCode.paramError.code);
			result.setMsg("参数类型错误!");
			return result;
		}
		
		boolean blRet = userService.updateUserInfo(userId,userName,iTypeId,userCode,userLevel,msgMail);
		if(!blRet){
			logger.error("addDeptUser error!");
			result.setResultCode(ResultCode.systemError.code);
			return result;
		}
		result.setResultCode(ResultCode.success.code);
		return result;
	}
	
	@ResponseBody
	@RequestMapping("/delDeptUser")
	public Result delDeptUser(HttpServletRequest request){
		Result result = new Result();
		String userId = request.getParameter("userId");
		String deptId = request.getParameter("deptId");
		
		if(StringUtils.isEmpty(userId)){
			result.setResultCode(ResultCode.paramError.code);
			result.setMsg("用户ID不能为空!");
			return result;	
		}
		 
		long iDeptId = -1;
		
		try {
			iDeptId = Integer.parseInt(deptId);
		} catch (NumberFormatException e) {
			logger.error("parameter convert error!");
			result.setResultCode(ResultCode.paramError.code);
			result.setMsg("参数类型错误!");
			return result;
		}
		
		boolean blRet = userDeptService.delDeptUser(userId,iDeptId);
		if(!blRet){
			logger.error("addDeptUser error!");
			result.setResultCode(ResultCode.systemError.code);
			return result;
		}
		result.setResultCode(ResultCode.success.code);
		return result;
	}
	
	
}
