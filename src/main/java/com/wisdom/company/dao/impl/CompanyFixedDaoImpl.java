package com.wisdom.company.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.wisdom.common.model.CompanyBill;
import com.wisdom.common.model.CompanyFixedAssets;
import com.wisdom.company.dao.ICompanyFixedDao;
import com.wisdom.company.mapper.CompanyBillMapper;
import com.wisdom.company.mapper.CompanyFixedAssetsMapper;
@Repository("companyFixedDao")
public class CompanyFixedDaoImpl implements ICompanyFixedDao {
	
	//log
	private static final Logger logger = LoggerFactory
			.getLogger(CompanyFixedDaoImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	//Select CompanyFixedAssets For by CompanyFixedAssets And month
	@Override
	public List<CompanyFixedAssets> getCompanyFixedAssets(Long companyId, String month) {
		List<CompanyFixedAssets>list=new ArrayList<CompanyFixedAssets>();
		logger.debug("companyId : {}", companyId,"month:{}",month);
		String sql="select * from company_fixed_assets where company_id=? And month=?";
		try{
			
			list=jdbcTemplate.query(sql,
					new Object[] {companyId,  month},new RowMapperResultSetExtractor<CompanyFixedAssets>(
							new CompanyFixedAssetsMapper()));

		}catch(Exception e){
			logger.error(e.toString());
		}
		return list;
	}

}
