package com.wisdom.company.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.wisdom.common.model.CompanyBill;
import com.wisdom.common.model.Dept;
import com.wisdom.common.model.Invoice;
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
		String sql = "insert into dept (name, company_id, parent_id, cost_center_encode, level, create_time)"
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
	public long addDeptAndGetKey(final Dept dept) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		try {
			int id = jdbcTemplate.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(
						Connection connection) throws SQLException {
					String sql = "insert into dept (name, company_id, parent_id, cost_center_encode, level, create_time)"
				+ " values (?, ?, ?, ?, ?, ?)";
					PreparedStatement ps = connection.prepareStatement(sql,
							Statement.RETURN_GENERATED_KEYS);
					ps.setString(1, dept.getName());
					ps.setLong(2, dept.getCompanyId());
					ps.setLong(3,dept.getParentId());
					ps.setDouble(4, dept.getCostCenterEncode());
					ps.setInt(5, dept.getLevel());
					ps.setTimestamp(6,dept.getCreateTime() == null ? 
									new Timestamp(System.currentTimeMillis()) : dept.getCreateTime() );
					return ps;
				}
			}, keyHolder);
			logger.debug("addInvoiceAndGetKey result : {}", keyHolder.getKey().longValue());
			return keyHolder.getKey().longValue();
		} catch (DataAccessException e) {
			logger.error("addInvoiceAndGetKey error." + e.getMessage());
			e.printStackTrace();
		}
		return -1;
	}
	
	@Override
	public boolean updateDept(Dept dept) {
		String sql = "update dept set name=?, company_id=?, parent_id=?,dept_code=?, cost_center_encode=?, level=? where id=?";
		if(dept.getName()== null || 
				dept.getCompanyId() == null || 
				dept.getCostCenterEncode() == null) {
				return false;
		}
		int affectedRows = jdbcTemplate.update(sql,
				dept.getName(),
				dept.getCompanyId(),
				dept.getParentId() == null ? 0 : dept.getParentId(),
				dept.getDeptCode(),
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
	
	@Override
	public boolean updateDeptInfoById(Dept dept){
		Long id = dept.getId();
		if(id==null||id<=0){
			return false;
		}
		
		String name = dept.getName();
		if(StringUtils.isEmpty(name)){
			logger.error("deptName can not be empty");
			return false;
		}
		
		StringBuilder sql = new StringBuilder("update dept ");
		sql.append(" set name=? ");
		sql.append("where id = ?");
		
		int affectedRows = jdbcTemplate.update(sql.toString(),
				dept.getName(),
				dept.getId());
		logger.debug("updateDept result : {}", affectedRows);
		return affectedRows != 0;
	}

	@SuppressWarnings("deprecation")
	@Override
	public long querySubDeptNumByIdAndCompanyId(long companyId, long id) {
		String sql = "select count(id) from dept where company_id=? and parent_id=?";
		try{
			int num = jdbcTemplate.queryForInt(sql,new Object[]{ companyId,id});
			return num;
		}catch(Exception e){
			logger.error("querySubDeptNumByIdAndCompanyId error:",e.getCause());
		}
		return 0;
	}

	@Override
	public List<Dept> querySubDeptList(long comanyId, long parentId) {
		if(comanyId == -1 || parentId == -1){
			logger.error("parameter error!");
			return null;
		}
		List<Dept> list = null;
		try {
			String sql = "select * from dept where company_id=? and parent_id=? order by dept_code desc";
			list = jdbcTemplate.query(sql, new Object[]{ comanyId, parentId}, new RowMapperResultSetExtractor<Dept>(new DeptMapper()));
		} catch (Exception e) {
			logger.error("querySubDeptList error!");
			logger.error(e.toString());
		}
		
		return list;
	}
	
	
}
