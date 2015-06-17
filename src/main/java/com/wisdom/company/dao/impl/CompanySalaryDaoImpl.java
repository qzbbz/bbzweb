package com.wisdom.company.dao.impl;

import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.wisdom.common.model.CompanyBill;
import com.wisdom.common.model.CompanySalary;
import com.wisdom.company.dao.ICompanySalaryDao;
import com.wisdom.company.mapper.CompanyBillMapper;
import com.wisdom.company.mapper.CompanySalaryMapper;

@Repository("companySalaryDao")
public class CompanySalaryDaoImpl implements ICompanySalaryDao {

	private static final Logger logger = LoggerFactory
			.getLogger(CompanySalaryDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public String getCompanySalaryFileByCompanyId(long companyId) {
		logger.debug("companyId : {}", companyId);
		String sql = "select * from company_salary where company_id = ?";
		CompanySalary companySalary = null;
		try {
			companySalary = jdbcTemplate.queryForObject(sql,
				new Object[] { companyId }, new CompanySalaryMapper());
		} catch(Exception e) {
			logger.debug("result is 0. exception : {}" + e.toString());
		}
		return companySalary == null || companySalary.getSalaryFile() == null ? "" : companySalary.getSalaryFile();
	}

	@Override
	public long addCompanySalary(CompanySalary companySalary) {
		String sql = "insert into company_salary (company_id, salary_file, create_time)"
				+ " values (?, ?, ?)";
		int affectedRows = jdbcTemplate.update(sql, 
				companySalary.getCompanyId() == null ? 0 : companySalary.getCompanyId(),
				companySalary.getSalaryFile() == null ? "" : companySalary.getSalaryFile(),
				companySalary.getCreateTime() == null ? new Timestamp(System.currentTimeMillis()) : companySalary.getCreateTime());
		logger.debug("addCompanySalary result : {}", affectedRows);
		return affectedRows;
	}

	@Override
	public boolean deleteCompanySalaryByCompanyId(long companyId) {
		String sql = "delete from company_salary where company_id = ?";
		int affectedRows = jdbcTemplate.update(sql, companyId);
		logger.debug("deleteCompanySalaryByCompanyId result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public List<CompanySalary> getAllCompanySalary(long companyId) {
		List<CompanySalary> list = null;
		try {
			String sql = "select * from company_salary where company_id=?";
			list = jdbcTemplate.query(sql, new Object[]{companyId}, 
					new RowMapperResultSetExtractor<CompanySalary>(
							new CompanySalaryMapper()));
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return list;
	}

}
