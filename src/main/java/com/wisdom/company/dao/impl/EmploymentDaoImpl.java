package com.wisdom.company.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.wisdom.common.model.Employment;
import com.wisdom.company.dao.IEmploymentDao;
import com.wisdom.company.mapper.EmploymentMapper;

@Repository("employmentDao")
public class EmploymentDaoImpl implements IEmploymentDao {

	private static final Logger logger = LoggerFactory
			.getLogger(EmploymentDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Employment> getEmploymentByCompanyId(long companyId) {
		List<Employment> list = null;
		try {
			String sql = "select * from enployment where company_id =?";
			list = jdbcTemplate.query(sql, new Object[]{companyId},
					new RowMapperResultSetExtractor<Employment>(
							new EmploymentMapper()));
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return list;
	}

	@Override
	public boolean addEmployment(Employment emp) {
		String sql = "insert into enployment (company_id, user_id, create_time)"
				+ " values (?, ?, ?)";
		int affectedRows = jdbcTemplate.update(sql, emp.getCompanyId(),
				emp.getUserId(), emp.getCreateTime());
		logger.debug("addEmployment result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public boolean deleteEmployment(Employment emp) {
		String sql = "delete from enployment where company_id = ?";
		int affectedRows = jdbcTemplate.update(sql, emp.getCompanyId());
		logger.debug("deleteEmployment result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public boolean updateEmployment(Employment emp) {
		String sql = "update dept set company_id=? where user_id=?";
		int affectedRows = jdbcTemplate.update(sql, emp.getCompanyId(), emp.getUserId());
		logger.debug("updateEmployment result : {}", affectedRows);
		return affectedRows != 0;
	}

}
