package com.wisdom.web.api.impl;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wisdom.common.model.Company;
import com.wisdom.common.model.Dept;
import com.wisdom.company.service.ICompanyService;
import com.wisdom.company.service.IDeptService;
import com.wisdom.web.api.IGenerateCostCenterEncodeApi;

@Service("generateCostCenterApiService")
public class GenerateCostCenterEncodeApiImpl implements IGenerateCostCenterEncodeApi {
	private static final Logger logger = LoggerFactory
			.getLogger(GenerateCostCenterEncodeApiImpl.class);

	@Autowired
	private ICompanyService companyService;
	@Autowired
	private IDeptService deptService;

	@Override
	public String generateCostCenterEncode(String parentCostCenterEncode) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 生成costCenterCode
	 * @return null 失败   String 返回生成的code
	 */
	@Override
	public String genCostCenterAndUpdate(long companyId,long  deptId){
		if(companyId ==-1 || deptId == -1 ){
			logger.error("parameter not valid error! companyId {},deptId {},",companyId,deptId);
			return null;
		}
		//默认部门初始化编码
		String curDeptCode = "01";
		String companyCode = "";
		
		DecimalFormat df = new DecimalFormat("00");
		DecimalFormat df4 = new DecimalFormat("0000");
		
		Company company = companyService.getCompanyByCompanyId(companyId);
		if(null == company){
			logger.error("get companyInfo failed!,companyId:" + companyId);
			return null;
		}else{
			companyCode = company.getCompanyCode();
		}
		
		
		//先查一下当前部门信息
		Dept curDept = deptService.getDeptById(deptId);
		if(null == curDept){
			logger.error("dept not existed");
			return null;
		}
		long parentId = curDept.getParentId();

		//查看当前公司，当前部门上一级部门下的最大的code编码
		List<Dept> deptList = deptService.querySubDeptList(companyId, parentId);
		Dept maxCodeDept = null;
		if(null == deptList || deptList.size() == 0){
			//第一个部门
			logger.error("获取部门信息错误！");
			return null;
		}else if(deptList.size() == 1 ){//当前部门是第一个部门
			curDeptCode = "01";
		}else{
			maxCodeDept = deptList.get(0);
			String maxCode = maxCodeDept.getDeptCode();
			int iMaxCode = Integer.valueOf(maxCode);
			if(iMaxCode <= 99){
				//最大部门编码+1
				curDeptCode = df.format(iMaxCode + 1);
			}else{
				//从list中找空缺
				int index = iMaxCode;
				for(Dept d : deptList){
					if(deptId == d.getParentId()) 
						continue;
					String tempCode = df.format(index);
					logger.debug("tempCode:{},deptCode:{}",tempCode,d.getDeptCode());
					if(!tempCode.equals(d.getDeptCode())){
						curDeptCode = tempCode;
						break;
					}
				}
			}
		}
		//查找当前dept的上层部门编码,拼装costCenterCode
		String costCenterCode = curDeptCode;
		Dept tmpDept = curDept;
		while(tmpDept.getParentId()!=0){
			
			Dept d = deptService.getDeptById(tmpDept.getParentId());
			if(d==null){
				logger.error("get parent dept info  error!",tmpDept.getParentId());
				return null;
			}
			costCenterCode = d.getDeptCode() + costCenterCode;
			tmpDept = d;
		}
		logger.debug("after while costCenterCode:" + costCenterCode);
		costCenterCode = companyCode + costCenterCode;
		
		//将生成的costCenterCode和deptCode更新到dept中
		curDept.setCostCenterEncode(Long.parseLong(costCenterCode));
		curDept.setDeptCode(curDeptCode);

		boolean blRet = deptService.updateDept(curDept);
		if(!blRet){
			logger.error("update dept costCenterCode error!");
			return null;
		}
		return costCenterCode;
	}
	
}
