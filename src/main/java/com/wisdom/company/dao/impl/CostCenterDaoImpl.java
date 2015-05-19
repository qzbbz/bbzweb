package com.wisdom.company.dao.impl;

import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.wisdom.common.model.CostCenter;
import com.wisdom.company.dao.ICostCenterDao;
import com.wisdom.company.mapper.CostCenterMapper;

@Repository("costCenterDao")
public class CostCenterDaoImpl implements ICostCenterDao {

	private static final Logger logger = LoggerFactory
			.getLogger(CostCenterDaoImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public CostCenter getCostCenterByEncode(long encode) {
		logger.debug("encode : {}", encode);
		String sql = "select * from cost_center where encode = ?";
		CostCenter costCenter = null;
		try {
			costCenter = jdbcTemplate.queryForObject(sql,
				new Object[] { encode }, new CostCenterMapper());
		} catch(Exception e) {
			logger.debug("result is 0. exception : {}" + e.toString());
		}
		return costCenter;
	}

	@Override
	public long addCostCenter(CostCenter costCenter) {
		String sql = "insert into cost_center (encode, name, alias, description, create_time)"
				+ " values (?, ?, ?, ?, ?)";
		if(costCenter.getEncode() == null || costCenter.getName() == null) return 0;
		int affectedRows = jdbcTemplate.update(sql, 
				costCenter.getEncode(),
				costCenter.getName(),
				costCenter.getAlias() == null ? "" : costCenter.getAlias(),
				costCenter.getDescription() == null ? "" : costCenter.getDescription(),
				costCenter.getCreateTime() == null ? new Timestamp(System.currentTimeMillis()) : costCenter.getCreateTime());
		logger.debug("addCostCenter result : {}", affectedRows);
		return affectedRows;
	}

	@Override
	public boolean deleteCostCenterByEncode(long encode) {
		String sql = "delete from cost_center where encode = ?";
		int affectedRows = jdbcTemplate.update(sql, encode);
		logger.debug("deleteCostCenterByEncode result : {}", affectedRows);
		return affectedRows != 0;
	}

	@Override
	public boolean updateCostCenter(CostCenter costCenter) {
		String sql = "update cost_center set encode=?, name=?, alias=?, description=? where id=?";
		logger.debug("costCenter : {}", costCenter.toString());
		if(costCenter.getEncode() == null || costCenter.getName() == null) return false;
		int affectedRows = jdbcTemplate.update(sql,
				costCenter.getEncode(),
				costCenter.getName(),
				costCenter.getAlias() == null ? "" : costCenter.getAlias(),
				costCenter.getDescription() == null ? "" : costCenter.getDescription(),
				costCenter.getId());
		logger.debug("updateCostCenter result : {}", affectedRows);
		return affectedRows != 0;
	}

}
