package com.wisdom.company.dao.impl;

import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.wisdom.common.model.CompanyDetail;
import com.wisdom.company.dao.ICompanyDetailDao;
import com.wisdom.company.mapper.CompanyDetailMapper;

@Repository("companyDetailDao")
public class CompanyDetailDaoImpl implements ICompanyDetailDao {

	private static final Logger logger = LoggerFactory
			.getLogger(CompanyDetailDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public CompanyDetail getCompanyDetailByCompanyId(long companyId) {
		logger.debug("companyId : {}", companyId);
		String sql = "select * from company_detail where company_id = ?";
		CompanyDetail companyDetail = null;
		try {
			companyDetail = jdbcTemplate.queryForObject(sql,
				new Object[] { companyId }, new CompanyDetailMapper());
		} catch(Exception e) {
			logger.debug("result is 0. exception : {}" + e.toString());
		}
		return companyDetail;
	}

	@Override
	public long addCompanyDetail(CompanyDetail companyDetail) {
		String sql = "insert into company_detail (company_id, company_res_file, corporation, reg_address, org_code, org_code_file, tax_code, tax_code_file, bank_name, create_time)"
				+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		int affectedRows = jdbcTemplate.update(sql, 
				companyDetail.getCompanyId() == null ? 0 : companyDetail.getCompanyId(),
				companyDetail.getCompanyResFile() == null ? "" : companyDetail.getCompanyResFile(),
				companyDetail.getCorporation() == null ? "" : companyDetail.getCorporation(),
				companyDetail.getRegAddress() == null ? "" : companyDetail.getRegAddress(),
				companyDetail.getOrgCode() == null ? "" : companyDetail.getOrgCode(),
				companyDetail.getOrgCodeFile() == null ? "" : companyDetail.getOrgCodeFile(),
				companyDetail.getTaxCode() == null ? "" : companyDetail.getTaxCode(),
				companyDetail.getTaxCodeFile() == null ? "" : companyDetail.getTaxCodeFile(),
				companyDetail.getBankName() == null ? "" : companyDetail.getBankName(),
				companyDetail.getCreateTime() == null ? new Timestamp(System.currentTimeMillis()) : companyDetail.getCreateTime());
		logger.debug("addCompanyDetail result : {}", affectedRows);
		return affectedRows;
	}

	@Override
	public boolean deleteCompanyDetailByCompanyId(long companyId) {
		String sql = "delete from company_detail where company_id = ?";
		int affectedRows = jdbcTemplate.update(sql, companyId);
		logger.debug("deleteCompanyDetailByCompanyId result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public boolean updateCompanyDetail(CompanyDetail companyDetail) {
		logger.debug("companyDetail : {}", companyDetail.toString());
		String sql = "update company_detail set company_res_file=?, corporation=?, reg_address=?, org_code=?, org_code_file=?, tax_code=?, tax_code_file=?, bank_name=? where company_id=?";
		int affectedRows = jdbcTemplate.update(sql,
				companyDetail.getCompanyResFile() == null ? "" : companyDetail.getCompanyResFile(),
				companyDetail.getCorporation() == null ? "" : companyDetail.getCorporation(),
				companyDetail.getRegAddress() == null ? "" : companyDetail.getRegAddress(),
				companyDetail.getOrgCode() == null ? "" : companyDetail.getOrgCode(),
				companyDetail.getOrgCodeFile() == null ? "" : companyDetail.getOrgCodeFile(),
				companyDetail.getTaxCode() == null ? "" : companyDetail.getTaxCode(),
				companyDetail.getTaxCodeFile() == null ? "" : companyDetail.getTaxCodeFile(),
				companyDetail.getBankName() == null ? "" : companyDetail.getBankName(),
				companyDetail.getCompanyId());
		logger.debug("updateCompanyDetail result : {}", affectedRows);
		return affectedRows != 0;
	}

}
