package com.wisdom.company.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.wisdom.common.model.Dept;
import com.wisdom.company.dao.IDeptDao;
import com.wisdom.company.mapper.DeptMapper;

@Repository("deptDao")
public class DeptDaoImpl implements IDeptDao {

	private static final Logger logger = LoggerFactory
			.getLogger(DeptDaoImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Dept getDeptByDeptId(long id) {
		logger.debug("Dept id : {}", id);
		String sql = "select * from dept where id = ?";
		Dept dept = null;
		try {
			dept = jdbcTemplate.queryForObject(sql, new Object[] { id },
					new DeptMapper());
		} catch (Exception e) {
			logger.debug("result is 0.");
		}
		return dept;
	}

	@Override
	public boolean addDept(Dept dept) {
		String sql = "insert into dept (name, company_id, parent_id, create_time)"
				+ " values (?, ?, ?, ?)";
		int affectedRows = jdbcTemplate.update(sql, dept.getName(),
				dept.getCompanyId(), dept.getParentId(), dept.getCreateTime());
		logger.debug("addDept result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public boolean deleteDept(Dept dept) {
		String sql = "delete from dept where id = ?";
		int affectedRows = jdbcTemplate.update(sql, dept.getId());
		logger.debug("deleteDept result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public boolean updateDept(Dept dept) {
		String sql = "update dept set name=?, company_id=?, parent_id=? where id=?";
		int affectedRows = jdbcTemplate.update(sql, dept.getName(),
				dept.getCompanyId(), dept.getParentId(), dept.getId());
		logger.debug("updateDept result : {}", affectedRows);
		return affectedRows != 0;
	}

}
