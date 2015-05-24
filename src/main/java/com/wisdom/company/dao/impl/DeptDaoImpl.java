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
import com.wisdom.common.model.Dept;
import com.wisdom.company.dao.IDeptDao;
import com.wisdom.company.mapper.CompanyBillMapper;
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
		String sql = "insert into dept (name, company_id, parent_id, cost_center_encode, level=?, create_time)"
				+ " values (?, ?, ?, ?, ?, ?)";
		if(dept.getName()== null || 
			dept.getCompanyId() == null || 
			dept.getCostCenterEncode() == null) {
			return false;
		}
		int affectedRows = jdbcTemplate.update(sql, 
				dept.getName(),
				dept.getCompanyId(),
				dept.getParentId() == null ? 0 : dept.getParentId(),
				dept.getCostCenterEncode(),
				dept.getLevel(),
				dept.getCreateTime() == null ? new Timestamp(System.currentTimeMillis()) : dept.getCreateTime());
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
		String sql = "update dept set name=?, company_id=?, parent_id=?, cost_center_encode=?, level=? where id=?";
		if(dept.getName()== null || 
				dept.getCompanyId() == null || 
				dept.getCostCenterEncode() == null) {
				return false;
		}
		int affectedRows = jdbcTemplate.update(sql,
				dept.getName(),
				dept.getCompanyId(),
				dept.getParentId() == null ? 0 : dept.getParentId(),
				dept.getCostCenterEncode(),
				dept.getLevel(),
				dept.getId());
		logger.debug("updateDept result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public List<Dept> getDeptListByCompanyId(long companyId) {
		List<Dept> list = null;
		try {
			String sql = "select * from dept where company_id=?";
			list = jdbcTemplate.query(sql, new Object[]{companyId}, 
					new RowMapperResultSetExtractor<Dept>(
							new DeptMapper()));
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return list;
	}

}
